package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.CookerStatusValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.context.lastmove.LastMoveValue;
import fr.inria.phoenix.scenario.kitchen.impl.context.Config;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KitchenStatusValuePublishable onLastMove(
			LastMoveValue lastMoveValue, DiscoverForLastMove discover) {
		Float LastMoveSensor1 = discover.lastMove().getSensor1().floatValue();
		Float LastMoveSensor2 = discover.lastMove().getSensor2().floatValue();
		String IsDoorOpen = discover.contactSensors().anyOne().toString();
		LastMoveValue IsCoockerIsOn = lastMoveValue;
			
		//TODO A configurer en fonction des temps d'alertes
		CookerStatusValue IsCoockerIsOn = cookerStatusValue;
		
		
		// Cuisinière allumée
		if(IsCoockerIsOn.value()){
			// Personne devant la cuisinière
			if(LastMoveSensor1 == 0){
				// Minuteur défini ?
				if (Config.timer_user != -1){
					
				}
				
			}
		}
		
		
		System.out.println("#DEBUG: SENSOR1"+LastMoveSensor1+" SENSOR2:"+LastMoveSensor2+" DOORSTATUS:"+IsDoorOpen+" COOKERSTATUS"+IsCoockerIsOn);

		return null;
	}




	
	

}
