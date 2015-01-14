package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.lastmove.AbstractLastMove;
import fr.inria.phoenix.diasuite.framework.datatype.getsensor.GetSensor;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;

public class LastMove extends AbstractLastMove {

	boolean Sensor1 = false;
	boolean Sensor2 = false;

	public LastMove(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected void onMotionFromMotionDetector(
			MotionFromMotionDetector motionFromMotionDetector,
			DiscoverForMotionFromMotionDetector discover) {

		DiaLog.info("[LASTMOVE] GetSensor");

		// Si une donnée est PUSH ..

		// Recuperation de la position du capteur
		String motionSensorLocation = motionFromMotionDetector.sender()
				.location();

		// Recuperation de la valeur
		boolean valuePushed = Boolean.parseBoolean(motionFromMotionDetector
				.value().getState());

		String sender = motionFromMotionDetector.sender().location();

		DiaLog.info("[LASTMOVE] value Pushed : " + valuePushed);
		DiaLog.info("[LASTMOVE] sender : " + sender);

		switch (motionSensorLocation) {
		case "Kitchen_1":
			Sensor2 = Boolean
					.parseBoolean((discover.motionDetectors()
							.whereLocation("Kitchen_2").anyOne().getMotion()
							.getState()));
			Sensor1 = valuePushed;

			DiaLog.info("[LASTMOVE] Mouvement(Sensor1) detecté");
			DiaLog.info("[LASTMOVE] Mouvement(Sensor2) récupéré : " + Sensor2);
			break;

		case "Kitchen_2":
			Sensor1 = Boolean
					.parseBoolean((discover.motionDetectors()
							.whereLocation("Kitchen_1").anyOne().getMotion()
							.getState()));

			Sensor2 = valuePushed;
			DiaLog.info("[LASTMOVE] Mouvement(Sensor2) detecté");
			DiaLog.info("[LASTMOVE] Mouvement(Sensor1) récupéré : " + Sensor1);
			break;
		default:
			DiaLog.info("[LASTMOVE] Un capteur de mouvement situé ("
					+ motionSensorLocation + ") publie.");
			break;

		}

		DiaLog.info("[LASTMOVE]  : sensor1 > " + Sensor1);
		DiaLog.info("[LASTMOVE]  : sensor2 > " + Sensor2);
		return;
	}

	@Override
	protected GetSensor onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer) {
		DiaLog.info("[LASTMOVE] GetSensor via timer");

		DiaLog.info("[LASTMOVE]  : sensor1 > " + Sensor1);
		DiaLog.info("[LASTMOVE]  : sensor2 > " + Sensor2);
		return new GetSensor(Sensor1, Sensor2);

	}

}