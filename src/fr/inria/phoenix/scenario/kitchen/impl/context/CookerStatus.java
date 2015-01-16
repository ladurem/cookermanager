package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.AbstractCookerStatus;
import fr.inria.phoenix.diasuite.framework.device.cooker.StatusFromCooker;

public class CookerStatus extends AbstractCookerStatus {

	public CookerStatus(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected CookerStatusValuePublishable onStatusFromCooker(
			StatusFromCooker statusFromCooker,
			DiscoverForStatusFromCooker discover) {

		DiaLog.info("[COOKERSTATUS] onStateFromCooker");
		String CookerState = statusFromCooker.value().getState();

		Float ElectricConsumption = Float.parseFloat(discover.electricMeters()
				.anyOne().getCurrentElectricConsumption().getState());

		DiaLog.info("[COOKERSTATUS] CS : " + CookerState);
		DiaLog.info("[COOKERSTATUS] EC : " + ElectricConsumption);

		if (CookerState.equals("On") && ElectricConsumption != 0) {
			DiaLog.info("[COOKERSTATUS] Système Allumé");
			return new CookerStatusValuePublishable(true, true);
		} else if (CookerState.equals("On") && ElectricConsumption == 0) {
			DiaLog.info("[COOKERSTATUS] Smartswich allume mais rien branche");
			return new CookerStatusValuePublishable(false, true);
		} else if (CookerState.equals("Off") && ElectricConsumption != 0) {
			DiaLog.info(" [COOKERSTATUS]Problème au niveau du smartwitch");
			return new CookerStatusValuePublishable(false, false);
		} else if (CookerState.equals("Off") && ElectricConsumption == 0) {
			DiaLog.info("[COOKERSTATUS] Système éteint");
			return new CookerStatusValuePublishable(false, false);
		} else {
			DiaLog.info("[COOKERSTATUS] Probleme de CoockerStatus.java / Aucune condition");
			return new CookerStatusValuePublishable(false, false);
		}

	}

	@Override
	protected Boolean getValue(DiscoverForGet discover) {
		String CookerState = discover.cookers().anyOne().getStatus().getState();

		Float ElectricConsumption = Float.parseFloat(discover.electricMeters()
				.anyOne().getCurrentElectricConsumption().getState());
		if (CookerState.equals("On") && ElectricConsumption != 0) {
			DiaLog.info("[COOKERSTATUS] système allumé");
			return true;
		} else if (CookerState.equals("On") && ElectricConsumption == 0) {
			DiaLog.info("[COOKERSTATUS] Smartswich allume mais rien branche");
			return false;
		} else if (CookerState.equals("Off") && ElectricConsumption != 0) {
			DiaLog.info(" [COOKERSTATUS]Problème au niveau du smartwitch");
			return false;
		} else if (CookerState.equals("Off") && ElectricConsumption == 0) {
			DiaLog.info("[COOKERSTATUS] Système éteint");
			return false;
		} else {
			DiaLog.info("[COOKERSTATUS] Probleme de CoockerStatus.java / Aucune condition");
			return false;
		}
	}
}