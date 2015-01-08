package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.KitchenStatusValue;
import fr.inria.phoenix.diasuite.framework.controller.kitchencontroller.AbstractKitchenController;

public class KitchenController extends AbstractKitchenController {

	public KitchenController(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected void onKitchenStatus(KitchenStatusValue kitchenStatus,
			DiscoverForKitchenStatus discover) {

		if (kitchenStatus.value().equals("OK")) {

		}
		if (kitchenStatus.value().equals("WARN")) {

		}
		if (kitchenStatus.value().equals("STOP")) {

		}

		// Extinction de la gaziniere
		discover.cookers().all().off();
		// Envoi d'une notification
		discover.messengers().all().sendMessage(null, "Titre", "MEssage", null);

	}
}
