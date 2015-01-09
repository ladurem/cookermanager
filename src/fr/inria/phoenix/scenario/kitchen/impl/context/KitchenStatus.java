package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.CookerStatusValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.datatype.kitchenstate.KitchenState;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KitchenStatusValuePublishable onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer,
			DiscoverForTimerTriggeredFromTimer discover) {
		
		DiaLog.debug("KitchenStatusValuePublishable");
		
		//VERIFICATION PERIODIQUE
		
		Float LastMoveSensor1 = discover.lastMove().getSensor1().floatValue();
		Float LastMoveSensor2 = discover.lastMove().getSensor2().floatValue();
		String IsDoorOpen = discover.contactSensors().anyOne().getState().getState();
		boolean IsCookerSwitchOn = discover.cookerStatus().booleanValue();
		String TimerTrigger = timerTriggeredFromTimer.value();
		
		//TODO A configurer en fonction des temps d'alertes
		DiaLog.debug("Timer value : "+ TimerTrigger);
		DiaLog.debug("#DEBUG: SENSOR1 >"+LastMoveSensor1+" SENSOR2: >"+LastMoveSensor2+" DOORSTATUS:"+IsDoorOpen+" COOKERSTATUS"+IsCookerSwitchOn);
	
		// Cuisinière allumée
		if(!TimerTrigger.isEmpty()){
			
			// Initialisation du timer
			if (Config.kitchenTimer == null){	// TODO : add
				DiaLog.info("New Timer");
				Config.kitchenTimer = new KitchenTimer();
			}
			
			// Personne devant la cuisinière
			if(LastMoveSensor1 == 0){
				// Minuteur défini ?
				if (Config.timer_user != -1){
					// TODO
				}
				else{
					if (Config.kitchenTimer != null)
						Config.kitchenTimer.stopTimer();
				}
				DiaLog.info("Cas 1");
				return new KitchenStatusValuePublishable(KitchenState.OK, true);
			}
			// Personne dans cuisine 
			else if(LastMoveSensor2 == 0){
				// TODO
				// Rien ne se passe ?
				DiaLog.info("Cas 2");
			}
			// Personne hors de la cuisine
			else if(LastMoveSensor1 != 0 && LastMoveSensor2 !=0){
				Config.kitchenTimer.setInterval(1500);
				DiaLog.info("Cas 3");
				return new KitchenStatusValuePublishable(KitchenState.STOP, true);
			}
		}
		
		return new KitchenStatusValuePublishable(KitchenState.OK, true);
	}

	@Override
	protected KitchenStatusValuePublishable onCookerStatus(
			CookerStatusValue cookerStatusValue,
			DiscoverForCookerStatus discover) {
		// PREMIER LANCEMENT :
		// FONCTINNEMENT NORMAL ET LANCEMENT DU TIMER
		DiaLog.debug("Kitchen status : Timer à lancer");
		
		return new KitchenStatusValuePublishable(KitchenState.RUNTIMER, true);
	}


	
	

}
