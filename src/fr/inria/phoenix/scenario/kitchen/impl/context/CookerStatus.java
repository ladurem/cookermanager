package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.AbstractCookerStatus;
import fr.inria.phoenix.diasuite.framework.device.cooker.StateFromCooker;

public class CookerStatus extends AbstractCookerStatus {

	public CookerStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
System.out.println("test");
	}

	@Override
	protected Boolean onStateFromCooker(StateFromCooker stateFromCooker,
			DiscoverForStateFromCooker discover) {
		
		DiaLog.info("onStateFromCooker");
		
		String CookerState = discover.cookers().anyOne().getState().getState();
		Float ElectricConsumption = discover.electricMeters().anyOne().getCurrentElectricConsumption();
		
		System.out.println("CS : " + CookerState);
		System.out.println("EC : " + ElectricConsumption);
		
		if (CookerState.equals("ON")
				&& ElectricConsumption != 0) {
			DiaLog.warning("système allumé");
			return true;
		} else if (CookerState.equals("ON")
				&& ElectricConsumption == 0) {
			System.out.println("Smartswich allume mais rien branche");
			DiaLog.warning("Smartswich allume mais rien branche");
			return false;
		} else if (CookerState.equals("OFF")
				&& ElectricConsumption != 0) {
			System.out.println("Probleme au niveau du smartwitch");
			DiaLog.warning("Problème au niveau du smartwitch");
			return false;
		} else if (CookerState.equals("OFF")
				&& ElectricConsumption == 0) {
			DiaLog.warning("Système éteint");
			return false;
		} else {
			System.out.println("Probleme de CoockerStatus.java / Aucune condition");
			DiaLog.warning("Probleme de CoockerStatus.java / Aucune condition");
			return null;
		}
	}



}
