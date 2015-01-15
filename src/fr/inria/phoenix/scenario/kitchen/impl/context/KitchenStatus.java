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

	@Override
	protected KitchenStatusValuePublishable onLastMove(
			LastMoveValue lastMoveValue, DiscoverForLastMove discover) {
		
		DiaLog.info("[KITCHENSTATUS] KitchenStatusValuePublishable");
		
		//VERIFICATION PERIODIQUE
		
		boolean LastMoveSensor1 = lastMoveValue.value().getSensor1().booleanValue();
		boolean LastMoveSensor2 = lastMoveValue.value().getSensor2().booleanValue();
		
		String IsDoorOpen = discover.contactSensors().anyOne().getState().getState();
		
		
		if (discover.cookerStatus() != null){
			boolean IsCookerSwitchOn  = discover.cookerStatus().booleanValue();
			DiaLog.info("[KITCHENSTATUS] COOKERSTATUS: "+IsCookerSwitchOn);
		}
		//TODO A configurer en fonction des temps d'alertes
		DiaLog.info("[KITCHENSTATUS] #DEBUG: SENSOR1 >"+ LastMoveSensor1);
		DiaLog.info("[KITCHENSTATUS] SENSOR2: >"+LastMoveSensor2);
		DiaLog.info("[KITCHENSTATUS] DOORSTATUS: "+IsDoorOpen);
		
				
	
		// Cuisinière allumée
			
			// Initialisation du timer
			if (Config.kitchenTimer == null){	// TODO : add
				DiaLog.info("New Timer");
				Config.kitchenTimer = new KitchenTimer();
			}
			
			// Personne devant la cuisinière
			if(LastMoveSensor1){
				// Minuteur défini ?
				if (Config.timer_user != -1){
					// TODO
				}
				else{
					if (Config.kitchenTimer != null)
						Config.kitchenTimer.stopTimer();
				}
				DiaLog.info("[KITCHENSTATUS] La personne est devant la cuisinière");
				return new KitchenStatusValuePublishable(KitchenState.OK, true);
			}
			// Personne dans cuisine 
			else if(LastMoveSensor2){
				// TODO
				// Rien ne se passe ?
				DiaLog.info("[KITCHENSTATUS] Dans la cuisine mais pas devant la cuisinière");
				return new KitchenStatusValuePublishable(KitchenState.OK, true);
			}
			// Personne hors de la cuisine
			else if( !LastMoveSensor1 && !LastMoveSensor2){ 
				Config.kitchenTimer.setInterval(1500);
				DiaLog.info("[KITCHENSTATUS] La personne est sortie de la cuisine, on augmente alors la valeur décrémentée");
				DiaLog.info("[KITCHENSTATUS] Pour les tests, on considère que la cuisinière s'éteint a ce moment la (doit être changé)");
				return new KitchenStatusValuePublishable(KitchenState.STOP, true);
			}
		
		
		return new KitchenStatusValuePublishable(KitchenState.OK, false);
	}

	@Override
	protected KitchenStatusValuePublishable onCookerStatus(
			CookerStatusValue cookerStatusValue,
			DiscoverForCookerStatus discover) {
		// PREMIER LANCEMENT :
		// FONCTINNEMENT NORMAL ET LANCEMENT DU TIMER
		DiaLog.info("[KITCHENSTATUS] Kitchen status : Timer à lancer");

		return new KitchenStatusValuePublishable(KitchenState.RUNTIMER, true);
	}


	
	
	

}