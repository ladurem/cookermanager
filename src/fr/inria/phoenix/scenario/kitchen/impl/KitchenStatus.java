package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.coockerstate.CoockerStateValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KitchenStatusValuePublishable onCoockerState(
			CoockerStateValue coockerStateValue,
			DiscoverForCoockerState discover) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
