package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.cookerstatus.AbstractCookerStatus;
import fr.inria.phoenix.diasuite.framework.datatype.onoffstatus.OnOffStatus;
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
		String CookerState = discover.cookers().anyOne().getStatus().toString();
		Float ElectricConsumption = Float.parseFloat(discover.electricMeters()
				.anyOne().getCurrentElectricConsumption().getState());

		DiaLog.info("[COOKERSTATUS] CS : " + CookerState);
		DiaLog.info("[COOKERSTATUS] EC : " + ElectricConsumption);

		DiaLog.info("[COOKERSTATUS] OnOff=" + OnOffStatus.ON);
		DiaLog.info("[COOKERSTATUS] STATE" + CookerState.equals(OnOffStatus.ON));
		DiaLog.info("[COOKERSTATUS] STATE" + CookerState.equals("ON"));

		if (CookerState.equals("ON") && ElectricConsumption != 0) {
			DiaLog.info("[COOKERSTATUS] système allumé");
			return new CookerStatusValuePublishable(true, true);
		} else if (CookerState.equals(OnOffStatus.ON)
				&& ElectricConsumption == 0) {
			DiaLog.info("[COOKERSTATUS] Smartswich allume mais rien branche");
			return new CookerStatusValuePublishable(false, true);
		} else if (CookerState.equals(OnOffStatus.OFF)
				&& ElectricConsumption != 0) {
			DiaLog.info(" [COOKERSTATUS]Problème au niveau du smartwitch");
			return new CookerStatusValuePublishable(false, true);
		} else if (CookerState.equals(OnOffStatus.OFF)
				&& ElectricConsumption == 0) {
			DiaLog.info("[COOKERSTATUS] Système éteint");
			return new CookerStatusValuePublishable(false, true);
		} else {
			DiaLog.info("[COOKERSTATUS] Probleme de CoockerStatus.java / Aucune condition");
			return new CookerStatusValuePublishable(false, true);
		}

	}
}
