package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.coockerstate.*;
import fr.inria.phoenix.diasuite.framework.context.kitchencontext.*;
import fr.inria.phoenix.diasuite.framework.context.kitchenstatus.KitchenStatusValue;
import fr.inria.phoenix.diasuite.framework.context.lastmovebehindkitchen.AbstractLastMoveBehindKitchen;
import fr.inria.phoenix.diasuite.framework.controller.kitchencontroller.AbstractKitchenController;
import fr.inria.phoenix.diasuite.framework.datatype.coockerstate.*;
import fr.inria.phoenix.diasuite.framework.datatype.doorstate.*;
import fr.inria.phoenix.diasuite.framework.device.motiondetectorbehindkitchen.MotionFromMotionDetectorBehindKitchen;

public class LastMoveBehind extends AbstractLastMoveBehindKitchen {

	public LastMoveBehind(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	float lastMove = 0;

	@Override
	protected Float onMotionFromMotionDetectorBehindKitchen(
			MotionFromMotionDetectorBehindKitchen motionFromMotionDetectorBehindKitchen,
			DiscoverForMotionFromMotionDetectorBehindKitchen discover) {

		MotionFromMotionDetectorBehindKitchen detector = motionFromMotionDetectorBehindKitchen;
		if (detector.equals("1")) {
			lastMove = 0;
			DiaLog.info("Mouvement detecté");
			System.out.println("Mouvement detecté");

		} else {
			lastMove += 1;
			DiaLog.info("Mouvement non detecté depuis " + lastMove);
			System.out.println("Mouvement non detecté" + lastMove);

		}

		return null;
	}

}
