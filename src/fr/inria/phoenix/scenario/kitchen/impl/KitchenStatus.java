package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.CookerStatusValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KitchenStatusValuePublishable onCookerStatus(
			CookerStatusValue cookerStatusValue,
			DiscoverForCookerStatus discover) {
		Float LastMoveSensor1 = discover.lastMove().getSensor1().floatValue();
		Float LastMoveSensor2 = discover.lastMove().getSensor2().floatValue();
		String IsDoorOpen = discover.contactSensors().anyOne().toString();
		CookerStatusValue IsCoockerIsOn = cookerStatusValue;
			
		//TODO A configurer en fonction des temps d'alertes
		
		
		System.out.println("#DEBUG: SENSOR1"+LastMoveSensor1+" SENSOR2:"+LastMoveSensor2+" DOORSTATUS:"+IsDoorOpen+" COOKERSTATUS"+IsCoockerIsOn);
		return null;
	}


	
	

}
