package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.CookerStatusValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.context.lastmove.LastMoveValue;
import fr.inria.phoenix.diasuite.framework.datatype.kitchenstate.KitchenState;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;
import fr.inria.phoenix.scenario.kitchen.impl.context.Config;
import fr.inria.phoenix.scenario.kitchen.impl.context.KitchenTimer;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KitchenStatusValuePublishable onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer,
			DiscoverForTimerTriggeredFromTimer discover) {
		//VERIFICATION PERIODIQUE
		
		Float LastMoveSensor1 = discover.lastMove().getSensor1().floatValue();
		Float LastMoveSensor2 = discover.lastMove().getSensor2().floatValue();
		String IsDoorOpen = discover.contactSensors().anyOne().toString();
		boolean IsCookerSwitchOn = discover.cookerStatus().booleanValue();
		String TimerTrigger = timerTriggeredFromTimer.value();
		//TODO A configurer en fonction des temps d'alertes
	
		// Cuisinière allumée
		if(!TimerTrigger.isEmpty()){
			
			// Initialisation du timer
			if (Config.kitchenTimer == null){
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
			}
			// Personne dans cuisine
			else if(LastMoveSensor2 == 0){
				// TODO
			}
		}
		
		
		System.out.println("#DEBUG: SENSOR1"+LastMoveSensor1+" SENSOR2:"+LastMoveSensor2+" DOORSTATUS:"+IsDoorOpen+" COOKERSTATUS"+IsCookerSwitchOn);
		return new KitchenStatusValuePublishable(KitchenState.RUNTIMER, true);
	}

	@Override
	protected KitchenStatusValuePublishable onCookerStatus(
			CookerStatusValue cookerStatusValue,
			DiscoverForCookerStatus discover) {
		// PREMIER LANCEMENT :
		// FONCTINNEMENT NORMAL ET LANCEMENT DU TIMER
		
		
		
		return new KitchenStatusValuePublishable(KitchenState.OK, true);
	}


	
	

}
