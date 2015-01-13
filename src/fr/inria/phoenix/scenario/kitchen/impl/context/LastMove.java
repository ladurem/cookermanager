package fr.inria.phoenix.scenario.kitchen.impl.context;

import fr.inria.diagen.core.ServiceConfiguration;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.context.lastmove.AbstractLastMove;
import fr.inria.phoenix.diasuite.framework.datatype.getsensor.GetSensor;
import fr.inria.phoenix.diasuite.framework.device.motiondetector.MotionFromMotionDetector;

public class LastMove extends AbstractLastMove {

	public LastMove(ServiceConfiguration serviceConfiguration) {
		super(serviceConfiguration);
	}
	Float Sensor1 = 0f;
	Float Sensor2 = 0f;
	@Override
	protected GetSensor onMotionFromMotionDetector(
			MotionFromMotionDetector motionFromMotionDetector,
			DiscoverForMotionFromMotionDetector discover) {
		
		DiaLog.info("[LASTMOVE] GetSensor");

		//Recuperation de la position du capteur
		String motionSensorLocation = discover.motionDetectors().anyOne().location();

		//Récuperation des autres capteurs
		 MotionDetectorCompositeForMotionFromMotionDetector composite = discover.motionDetectors().whereLocation(motionSensorLocation);
	
		 
		 switch (motionSensorLocation) {
		case "Kitchen_1":
			if (Boolean.parseBoolean(composite.andLocation(motionSensorLocation).toString())){
				Sensor1 = 0f;
				DiaLog.info("[LASTMOVE] Mouvement(Sensor1) detecté");
			} else {
				Sensor1 += 1;
				DiaLog.info("[LASTMOVE] Mouvement(Sensor1) non detecté depuis " + Sensor1);
			}
			break;
		case "Kitchen_2":
			if (Boolean.parseBoolean(composite.toString())){
				Sensor2 = 0f;
				DiaLog.info("[LASTMOVE] Mouvement(Sensor2) detecté");
			} else {
				Sensor2 += 1;
				DiaLog.info("[LASTMOVE] Mouvement(Sensor2) non detecté depuis " + Sensor2);
			}
			break;
		default:
			DiaLog.info("[LASTMOVE] Un capteur de mouvement situé ("+motionSensorLocation+") publie.");
			break;
		 }
		 
		 
		DiaLog.debug("[LASTMOVE]  : sensor1 > "+ Sensor1);
		DiaLog.debug("[LASTMOVE]  : sensor2 > "+ Sensor2);
		return new GetSensor(Sensor1,Sensor2);
		
	}

		
	

}
