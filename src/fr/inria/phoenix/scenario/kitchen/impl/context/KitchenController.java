package fr.inria.phoenix.scenario.kitchen.impl.context;

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
		
		DiaLog.info("Controller");
		
		if (kitchenStatus.value().equals("OK")) {
			DiaLog.debug("KitchenStatus = OK");
//			System.out.println("KitchenStatus = OK");

		}
		if (kitchenStatus.value().equals("WARN")) {
			DiaLog.debug("KitchenStatus = WARN");
//			System.out.println("KitchenStatus = WARN");
			discover.messengers().all().sendMessage(null, "Titre", "MEssage", null);
		}
		if (kitchenStatus.value().equals("STOP")) {
//			DiaLog.debug("KitchenStatus = STOP");
			System.out.println("KitchenStatus = STOP");
			discover.cookers().all().off();

		}
		if (kitchenStatus.value().equals("RUNTIMER")) {
//			DiaLog.debug("KitchenStatus = PREMIER TOP");
			System.out.println("KitchenStatus = PREMIER TOP");
			discover.timers().all().schedule(null, 1000);
		}
		DiaLog.debug("FIn du controlleur");
//		System.out.println("Fin du controlleur");
		return;
		
//
//		// Extinction de la gaziniere
//		discover.cookers().all().off();
//		// Envoi d'une notification
//		discover.messengers().all().sendMessage(null, "Titre", "Message", null);
//
//
	}
}
