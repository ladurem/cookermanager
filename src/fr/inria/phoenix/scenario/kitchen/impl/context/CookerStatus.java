package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.AbstractCookerStatus;
import fr.inria.phoenix.diasuite.framework.device.cooker.StateFromCooker;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;

public class CookerStatus extends AbstractCookerStatus {

	public CookerStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected CookerStatusValuePublishable onStateFromCooker(StateFromCooker stateFromCooker,
			DiscoverForStateFromCooker discover) {
		
		DiaLog.info("Cooker Status : onStateFromCooker");
//		System.out.println("Cooker Status : onStateFromCooker");
		
		String CookerState = discover.cookers().anyOne().getState().getState();
		Float ElectricConsumption = discover.electricMeters().anyOne().getCurrentElectricConsumption();
		
		DiaLog.info("CS : " + CookerState);
		DiaLog.info("EC : " + ElectricConsumption);
		
		if (CookerState.equals("ON") && ElectricConsumption != 0) {
			DiaLog.warning("système allumé");	
			return new CookerStatusValuePublishable(true, true);
			
		} else if (CookerState.equals("ON") && ElectricConsumption == 0) {
//			System.out.println("Smartswich allume mais rien branche");
			DiaLog.warning("Smartswich allume mais rien branche");
			return new CookerStatusValuePublishable(false, true);
		} else if (CookerState.equals("OFF") && ElectricConsumption != 0) {
//			System.out.println("Probleme au niveau du smartwitch");
			DiaLog.warning("Problème au niveau du smartwitch");
			return new CookerStatusValuePublishable(false, true);
		} else if (CookerState.equals("OFF") && ElectricConsumption == 0) {
			DiaLog.warning("Système éteint");
			return new CookerStatusValuePublishable(false, true);
		} else {
//			System.out.println("Probleme de CoockerStatus.java / Aucune condition");
			DiaLog.warning("Probleme de CoockerStatus.java / Aucune condition");
			return new CookerStatusValuePublishable(false, true);
		}
	}

}
