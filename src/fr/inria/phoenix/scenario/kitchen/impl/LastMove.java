package fr.inria.phoenix.scenario.kitchen.impl;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.lastmove.AbstractLastMove;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;

public class LastMove extends AbstractLastMove {


	public LastMove(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	float lastMove = 0;

	@Override
	protected Float onMotionFromMotionDetector(MotionFromMotionDetector motionFromMotionDetector,
			DiscoverForMotionFromMotionDetector discover) {
		MotionFromMotionDetector detector = motionFromMotionDetector;
		
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
