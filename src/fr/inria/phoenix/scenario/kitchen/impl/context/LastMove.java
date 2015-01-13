package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.lastmove.AbstractLastMove;
import fr.inria.phoenix.diasuite.framework.datatype.getsensor.GetSensor;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;
import fr.inria.phoenix.diasuite.framework.device.timer.TimerTriggeredFromTimer;

public class LastMove extends AbstractLastMove {

	public LastMove(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}

	@Override
	protected GetSensor onMotionFromMotionDetector(
			MotionFromMotionDetector motionFromMotionDetector,
			DiscoverForMotionFromMotionDetector discover) {

		boolean Sensor1 = false;
		boolean Sensor2 = false;
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
		DiaLog.info("[LASTMOVE] value Pushed : " + valuePushed);

		boolean valueGot;

		switch (motionSensorLocation) {
		case "Kitchen_1":
			valueGot = Boolean
					.parseBoolean((discover.motionDetectors()
							.whereLocation("Kitchen_2").anyOne().getMotion()
							.getState()));
			if (valuePushed) {
				Sensor1 = true;
				DiaLog.info("[LASTMOVE] Mouvement(Sensor1) detecté");
				if (valueGot) {
					Sensor2 = true;
					DiaLog.info("[LASTMOVE] Mouvement(Sensor2) récupéré");
				}
			} else {
				if (valueGot) {
					Sensor2 = true;
					DiaLog.info("[LASTMOVE] Mouvement(Sensor2) récupéré");
				}
			}

			break;

		case "Kitchen_2":
			valueGot = Boolean
					.parseBoolean((discover.motionDetectors()
							.whereLocation("Kitchen_1").anyOne().getMotion()
							.getState()));
			if (valuePushed) {
				Sensor2 = true;
				DiaLog.info("[LASTMOVE] Mouvement(Sensor2) detecté");

				if (valueGot) {
					Sensor1 = true;
					DiaLog.info("[LASTMOVE] Mouvement(Sensor1) récupéré");
				}
			} else {
				if (valueGot) {
					Sensor1 = true;
					DiaLog.info("[LASTMOVE] Mouvement(Sensor1) récupéré");
				}
			}

			break;
		default:
			DiaLog.info("[LASTMOVE] Un capteur de mouvement situé ("
					+ motionSensorLocation + ") publie.");
			break;

		}

		DiaLog.info("[LASTMOVE]  : sensor1 > " + Sensor1);
		DiaLog.info("[LASTMOVE]  : sensor2 > " + Sensor2);
		return new GetSensor(Sensor1, Sensor2);

	}

	@Override
	protected GetSensor onTimerTriggeredFromTimer(
			TimerTriggeredFromTimer timerTriggeredFromTimer,
			DiscoverForTimerTriggeredFromTimer discover) {
		boolean Sensor1 = false;
		boolean Sensor2 = false;

		DiaLog.info("[LASTMOVE] GetSensor via timer");

		
		// On parcout les locations des capteurs et on recupere la data
		MotionDetectorCompositeForTimerTriggeredFromTimer motionSensorLocation = discover
				.motionDetectors().all();

		for (MotionDetectorProxyForTimerTriggeredFromTimer sensor : motionSensorLocation) {
			if (sensor.location().equals("Kitchen_1")) {
				if (Boolean.parseBoolean(sensor.getMotion().getState())) {
					Sensor1 = true;
				}

			} else if (sensor.location().equals("Kitchen_2")) {
				if (Boolean.parseBoolean(sensor.getMotion().getState())) {
					Sensor2 = true;
				}
			}

		}
		DiaLog.info("[LASTMOVE]  : sensor1 > " + Sensor1);
		DiaLog.info("[LASTMOVE]  : sensor2 > " + Sensor2);
		return new GetSensor(Sensor1, Sensor2);
	}
}
