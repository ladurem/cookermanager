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

	@Override
	protected GetSensor onMotionFromMotionDetector(
			MotionFromMotionDetector motionFromMotionDetector,
			DiscoverForMotionFromMotionDetector discover) {

		//Recuperation de la position du capteur
		String motionSensorLocation = motionFromMotionDetector.sender().location();

		//Récuperation des autres capteurs
		MotionDetectorCompositeForMotionFromMotionDetector composite = discover.motionDetectors().whereLocation(motionSensorLocation);
		
		Float Sensor1 = 0f;
		Float Sensor2 = 0f;
		
		for(MotionDetectorProxyForMotionFromMotionDetector sensor : composite){
			if(Integer.parseInt(sensor.id()) == 1){
				if (sensor.getMotion().equals("1")) {
					Sensor1 = 0f;
					DiaLog.info("Mouvement(Sensor1) detecté");
					System.out.println("Mouvement(Sensor1) detecté");

				} else {
					Sensor1 += 1;
					DiaLog.info("Mouvement(Sensor1) non detecté depuis " + Sensor1);
					System.out.println("Mouvement(Sensor1) non detecté" + Sensor1);
				}
			} else {
				if (sensor.getMotion().equals("1")) {
					Sensor2 = 0f;
					DiaLog.info("Mouvement(Sensor2) detecté");
					System.out.println("Mouvement(Sensor2) detecté");

				} else {
					Sensor2 += 1;
					DiaLog.info("Mouvement(Sensor2) non detecté depuis " + Sensor2);
					System.out.println("Mouvement(Sensor2) non detecté" + Sensor2);
				}
				
			}
				
			
		}
		
		
		
		//TODO A gerer le return
		return new GetSensor(Sensor1,Sensor2) ;

		//return  Float(Sensor1,Sensor2);
	}



}