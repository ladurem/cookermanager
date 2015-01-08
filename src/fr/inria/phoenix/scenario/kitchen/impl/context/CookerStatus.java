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
	protected Boolean onStateFromCooker(StateFromCooker stateFromCooker,DiscoverForStateFromCooker discover) {
		System.out.println("COOKER");
		return true;
//		String CookerState = discover.cookers().anyOne().toString();
//		String ElectricConsumption = discover.electricMeters().anyOne().toString();
//		if (CookerState.equals("ON")
//				&& Integer.parseInt(ElectricConsumption) != 0) {
//			return true;
//		} else if (CookerState.equals("ON")
//				&& Integer.parseInt(ElectricConsumption) == 0) {
//			System.out.println("Smartswich allume mais rien branche");
//			DiaLog.warning("Smartswich allume mais rien branche");
//			return false;
//		} else if (CookerState.equals("OFF")
//				&& Integer.parseInt(ElectricConsumption) != 0) {
//			System.out.println("Probleme au niveau du smartwitch");
//			DiaLog.warning("Problème au niveau du smartwitch");
//			return false;
//		} else if (CookerState.equals("OFF")
//				&& Integer.parseInt(ElectricConsumption) == 0) {
//			// Systeme eteint
//			return false;
//		} else {
//			System.out
//					.println("Probleme de CoockerStatus.java / Aucune condition");
//			DiaLog.warning("Probleme de CoockerStatus.java / Aucune condition");
//			return null;
//		}
	}



}
