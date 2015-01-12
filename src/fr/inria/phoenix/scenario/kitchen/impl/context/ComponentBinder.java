package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.phoenix.diasuite.framework.context.cookerstatus.AbstractCookerStatus;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.AbstractKitchenStatus;
import fr.inria.phoenix.diasuite.framework.context.lastmove.AbstractLastMove;
import fr.inria.phoenix.diasuite.framework.controller.kitchencontroller.AbstractKitchenController;
import fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder;
import fr.inria.phoenix.scenario.kitchen.impl.controller.KitchenController;

/* (non-Javadoc)
 * The binder to provides the various components of the application
 * @see fr.inria.phoenix.diasuite.framework.misc.AppComponentBinder
 */
public class ComponentBinder extends AppComponentBinder {

	@Override
	public Class<? extends AbstractCookerStatus> getCookerStatusClass() {
		// TODO Auto-generated method stub
		return CookerStatus.class;
	}

	@Override
	public Class<? extends AbstractKitchenStatus> getKitchenStatusClass() {
		// TODO Auto-generated method stub
		return KitchenStatus.class;
	}

	@Override
	public Class<? extends AbstractLastMove> getLastMoveClass() {
		// TODO Auto-generated method stub
		return LastMove.class;
	}

	@Override
	public Class<? extends AbstractKitchenController> getKitchenControllerClass() {
		// TODO Auto-generated method stub
		return KitchenController.class;
	}
	
	public static void main(String[] args) {
		ComponentBinder binder = new ComponentBinder();
		binder.deployAll();
		

		/*ControlPanel controlPanel = new ControlPanel(
				binder.getServiceConfiguration("ControlPanel_1"),"ControlPanel", "A29", "phoenix");

		HVACSystem hvacSystem = new HVACSystem(new LocalServiceConfiguration("HvacSystem_1"), "HvacSystem", "A29", "phoenix");

		TemperatureSensor temperatureSensor = new TemperatureSensor(new LocalServiceConfiguration("TemperatureSensor_1"),"TemperatureSensor", "A29", "phoenix");

		binder.deploy(controlPanel);
		binder.deploy(hvacSystem);
		binder.deploy(temperatureSensor);

		temperatureSensor.show();
		controlPanel.show();
		hvacSystem.show();
		*/
	}

}
