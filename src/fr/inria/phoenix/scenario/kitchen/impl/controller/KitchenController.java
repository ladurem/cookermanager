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

		
		switch (kitchenStatus.value().toString()) {
		/**ACTIONS **/ 
		case "OK":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = OK");
			break;
		case "RUNTIMER":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = PREMIER TOP");
			discover.timers().all().periodicSchedule("1", 1000, 5000);
			break;
		case "STOP":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = STOP");
			discover.cookers().all().off();
			discover.timers().all().cancel("1");
			break;
			/****MESSAGES***/
		case "ERROR":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = ERROR");
			discover.messengers().all().sendMessage(null, "ERREUR", "Une erreur est survenue.", null); 
			break;
			
		case "WARN1":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = WARN");
			discover.messengers().all().sendMessage(null, "Attention", "Votre cuisinière est allumée.", null);
			break;
		case "WARN2":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = WARN2");
			discover.messengers().all().sendMessage(null, "Attention", "[FORT] - Votre cuisinière est allumée.", null);
			break;
			
		case "ALARM1":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = ALARME1");
			discover.messengers().all().sendMessage(null, "ALERTE", "Attention, votre cuisinière est allumée. Extinction de votre cuisnière.", null);
			break;
		case "ALARM2":
			DiaLog.info("[KITCHENCONTROLLER] KitchenStatus = ALARME2");
			discover.messengers().all().sendMessage(null, "ALERTE", "[FORT] Attention, votre cuisinière est allumée. Extinction de votre cuisnière.", null);
			break;
			
			
		default:
			DiaLog.info("[KITCHENCONTROLLER] Erreur d'action = "+kitchenStatus.value().toString());
			break;
		}
		DiaLog.info("[KITCHENCONTROLLER] Fin du controlleur");
		return;
	}
}