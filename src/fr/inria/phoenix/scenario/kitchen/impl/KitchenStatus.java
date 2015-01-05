package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.coockerstate.CoockerStateValue;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.context.lastmove.LastMoveValue;
import fr.inria.phoenix.diasuite.framework.context.lastmovebehindkitchen.LastMoveBehindKitchenValue;
import fr.inria.phoenix.diasuite.framework.datatype.kitchenstate.KitchenState;

public class KitchenStatus extends AbstractKitchenStatus{

	public KitchenStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
		}

	@Override
	protected KitchenState onLastMove(LastMoveValue lastMoveValue,
			DiscoverForLastMove discover) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected KitchenState onLastMoveBehindKitchen(
			LastMoveBehindKitchenValue lastMoveBehindKitchenValue,
			DiscoverForLastMoveBehindKitchen discover) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected KitchenState onCoockerState(CoockerStateValue coockerStateValue) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
