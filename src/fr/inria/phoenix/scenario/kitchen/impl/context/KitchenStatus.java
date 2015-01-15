package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.CookerStatusValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.context.lastmove.LastMoveValue;
import fr.inria.phoenix.diasuite.framework.datatype.kitchenstate.KitchenState;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}
	
	// Compteur
	public int timer = 0; // Nombre en secondes
	public boolean outOfTheKitchen = false;
	public boolean notified = false;
	
	
	@Override
	protected KitchenStatusValuePublishable onLastMove(
			LastMoveValue lastMoveValue, DiscoverForLastMove discover) {
		
		timer += 5;
		DiaLog.info("[COMPTEUR] = " + timer);
		DiaLog.info("[KITCHENSTATUS] KitchenStatusValuePublishable");
		
		//VERIFICATION PERIODIQUE
		
		boolean LastMoveSensor1 = lastMoveValue.value().getSensor1().booleanValue();
		boolean LastMoveSensor2 = lastMoveValue.value().getSensor2().booleanValue();
		
		String IsDoorOpen = discover.contactSensors().anyOne().getState().getState();
		
		
		
		boolean IsCookerSwitchOn  = discover.cookerStatus().booleanValue();
		DiaLog.info("[KITCHENSTATUS] COOKERSTATUS: "+IsCookerSwitchOn);
		
		//TODO A configurer en fonction des temps d'alertes
		DiaLog.info("[KITCHENSTATUS] #DEBUG: SENSOR1 >"+ LastMoveSensor1);
		DiaLog.info("[KITCHENSTATUS] SENSOR2: >"+LastMoveSensor2);
		DiaLog.info("[KITCHENSTATUS] DOORSTATUS: "+IsDoorOpen);
		
		if (timer < Config.DEFAULT_TIMER && timer >= Config.DEFAULT_TIMER - Config.TIME_BEFORE_NOTIFICATION && !notified){
			//TODO Send message
			DiaLog.info("[KITCHENSTATUS] : Notifie l'utilisateur");
			notified = true;
			if (Boolean.parseBoolean(IsDoorOpen)){ // fermée
				return new KitchenStatusValuePublishable(KitchenState.WARN2, true);
			}
			else{	//Porte ouverte
				return new KitchenStatusValuePublishable(KitchenState.WARN1, true);
			}
			
		}
		if (timer >= Config.DEFAULT_TIMER){
			DiaLog.info("[KITCHENSTATUS] : Fin du timer ==> ALARME");
			if (Boolean.parseBoolean(IsDoorOpen)){ // fermée
				return new KitchenStatusValuePublishable(KitchenState.ALARM2, true);
			}
			else{	//Porte ouverte
				return new KitchenStatusValuePublishable(KitchenState.ALARM1, true);
			}
		}
		if (timer >= Config.DEFAULT_TIMER + Config.TIME_BEFORE_NOTIFICATION){
			DiaLog.info("[KITCHENSTATUS] : PAS DE REPONSE, ON STOPPE !");
			return new KitchenStatusValuePublishable(KitchenState.STOP, true);
		}
		
		
		
	
		// Cuisinière allumée
		if (IsCookerSwitchOn){	
			
			// La personne est devant la cuisinière
			if(LastMoveSensor1){
				// Minuteur défini ?
//				if (Config.timer_user != -1){
//					// TODO
//				}
//				else{
//					
//				}
				
				// On réinitialise le timer et 
				timer = 0;
				notified = false;
				
				DiaLog.info("[KITCHENSTATUS] La personne est devant la cuisinière");
				return new KitchenStatusValuePublishable(KitchenState.OK, true);
			}
			// Personne dans cuisine 
			else if(LastMoveSensor2){
				// TODO
				// Rien ne se passe ?
				notified = false;
				DiaLog.info("[KITCHENSTATUS] Dans la cuisine mais pas devant la cuisinière");
				return new KitchenStatusValuePublishable(KitchenState.OK, true);
			}
			// Personne hors de la cuisine
			else if( !LastMoveSensor1 && !LastMoveSensor2){
				
				outOfTheKitchen = true;
				DiaLog.info("[KITCHENSTATUS] La personne est sortie de la cuisine, on augmente alors la valeur décrémentée");
				DiaLog.info("[KITCHENSTATUS] Pour les tests, on considère que la cuisinière s'éteint a ce moment la (doit être changé)");
				return new KitchenStatusValuePublishable(KitchenState.OK, true);
			}
		
		}
		else{ // Not running
			return new KitchenStatusValuePublishable(KitchenState.STOP, true);
		}
		
		return new KitchenStatusValuePublishable(KitchenState.ERROR, true); 
	}

	@Override
	protected KitchenStatusValuePublishable onCookerStatus(
			CookerStatusValue cookerStatusValue,
			DiscoverForCookerStatus discover) {
		// PREMIER LANCEMENT :
		// FONCTINNEMENT NORMAL ET LANCEMENT DU TIMER
		if (cookerStatusValue.value()){
			DiaLog.info("[KITCHENSTATUS] Kitchen status : Timer à lancer");
			return new KitchenStatusValuePublishable(KitchenState.RUNTIMER, true);
		}
		
		return new KitchenStatusValuePublishable(KitchenState.STOP, true);
	}


	
	
	

}