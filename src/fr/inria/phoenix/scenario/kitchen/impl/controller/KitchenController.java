package fr.inria.phoenix.scenario.kitchen.impl.controller;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.KitchenStatusValue;
import fr.inria.phoenix.diasuite.framework.controller.kitchencontroller.AbstractKitchenController;

public class KitchenController extends AbstractKitchenController {

	public KitchenController(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected void onKitchenStatus(KitchenStatusValue kitchenStatus,
			DiscoverForKitchenStatus discover) {

		DiaLog.info("[KITCHENCONTROLLER] Controller : "+ kitchenStatus.value().toString());

		if (kitchenStatus.value().toString().equals("OK")) {
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = OK");
		}
		if (kitchenStatus.value().toString().equals("WARN")) {
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = WARN");
			discover.messengers().all().sendMessage(null, "Titre", "MEssage", null);
		}
		if (kitchenStatus.value().toString().equals("STOP")) {
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = STOP");
			discover.cookers().all().off();
		}
		if (kitchenStatus.value().toString().equals("RUNTIMER")) {
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = PREMIER TOP");
			discover.timers().all().periodicSchedule("1", 1000, 1000);
		}
		DiaLog.info("[KITCHENCONTROLLER] Fin du controlleur");
		return;
	}
}