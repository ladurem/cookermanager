package fr.inria.phoenix.scenario.kitchen.impl;
        
import fr.inria.phoenix.diasuite.framework.context.kitchencontext.AbstractKitchenContext;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.context.lastmove.AbstractLastMove;
import fr.inria.phoenix.diasuite.framework.context.lastmovebehindkitchen.AbstractLastMoveBehindKitchen;
import fr.inria.phoenix.diasuite.framework.controller.kitchencontroller.AbstractKitchenController;
import fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder;

/* (non-Javadoc)
 * The binder to provides the various components of the application
 * @see fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder
 */
public class ComponentBinder extends AppComponentBinder {


	@Override
	public Class<? extends AbstractKitchenStatus> getKitchenStatusClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends AbstractLastMove> getLastMoveClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends AbstractLastMoveBehindKitchen> getLastMoveBehindKitchenClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends AbstractKitchenController> getKitchenControllerClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
