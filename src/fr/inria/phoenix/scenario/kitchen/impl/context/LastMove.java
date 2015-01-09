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
		
		DiaLog.info("GetSensor");

		//Recuperation de la position du capteur
		String motionSensorLocation = motionFromMotionDetector.sender().location();

		//Récuperation des autres capteurs
		MotionDetectorCompositeForMotionFromMotionDetector composite = discover.motionDetectors().whereLocation(motionSensorLocation);
		

		
		for(MotionDetectorProxyForMotionFromMotionDetector sensor : composite){
			DiaLog.info("GetSensor : val capteur = "+sensor.getMotion() +" | id= " + sensor.id());
			if(Integer.parseInt(sensor.id()) == 1){
				if (sensor.getMotion()) {
					Sensor1 = 0f;
					DiaLog.info("Mouvement(Sensor1) detecté");
//					System.out.println("Mouvement(Sensor1) detecté");

				} else {
					Sensor1 += 1;
					DiaLog.info("Mouvement(Sensor1) non detecté depuis " + Sensor1);
//					System.out.println("Mouvement(Sensor1) non detecté" + Sensor1);
				}
			} else {
				if (sensor.getMotion()) {
					Sensor2 = 0f;
					DiaLog.info("Mouvement(Sensor2) detecté");
//					System.out.println("Mouvement(Sensor2) detecté");

				} else {
					Sensor2 += 1;
					DiaLog.info("Mouvement(Sensor2) non detecté depuis " + Sensor2);
//					System.out.println("Mouvement(Sensor2) non detecté" + Sensor2);
				}
				
			}
		}
		DiaLog.debug("LM : sensor1 > "+ Sensor1);
		DiaLog.debug("LM : sensor2 > "+ Sensor2);
		return new GetSensor(Sensor1,Sensor2) ;
		}



}
