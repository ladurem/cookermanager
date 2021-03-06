package cookermanager;

import static fr.inria.phoenix.diasuite.framework.mocks.Mock.TIMEOUT;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.mockContactSensor;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.mockCooker;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.mockElectricMeter;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.mockMotionDetector;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.mockTimer;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.shutdown;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.underTest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.rmi.runtime.Log;
import fr.inria.diagen.log.DiaLog;
import fr.inria.phoenix.diasuite.framework.datatype.state.State;
import fr.inria.phoenix.diasuite.framework.mocks.ContactSensorMock;
import fr.inria.phoenix.diasuite.framework.mocks.CookerMock;
import fr.inria.phoenix.diasuite.framework.mocks.ElectricMeterMock;
import fr.inria.phoenix.diasuite.framework.mocks.MotionDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.kitchen.impl.ComponentBinder;

public class TestCase {

//	private TemperatureSensorMock temperatureSensor;
//	private ControlPanelMock controlPanel;
//	private HVACSystemMock hvacSystem;
	
	private CookerMock cooker;
	private MotionDetectorMock md1;
	private MotionDetectorMock md2;
	private ContactSensorMock cs;
	private ElectricMeterMock em;
	private TimerMock tm;
	
	@Before
	public void setup() throws Exception {
		TIMEOUT = 1000;
		underTest(ComponentBinder.class);
	}
	
	@After
	public void tearDown() throws Exception {
		shutdown();
	}
	
//	@Test
//	public void periodicScheduleStarted() {
//		DiaLog.info("====== periodicScheduleStarted() ======");
//		cooker = mockCooker("1", "kitchen", ("company"));
//		md1 = mockMotionDetector("1", "location", ("company"));
//		md2 = mockMotionDetector("2", "location", ("company"));
//		cs = mockContactSensor("1", "location", ("company"));
//		em = mockElectricMeter("1", "location", ("company"));
//		tm = mockTimer("1");
//		
//		// Periodic timer supposé non lancé
//		assertFalse(tm.expectPeriodicSchedule("1"));
//		
//		// On démarre la cuisinière
//		em.setCurrentElectricConsumption(new State("5f", "45645454"));
//		cooker.status(new State("On", "454654564564"));
//		
//		// Periodic timer supposé lancé
//		assertTrue(tm.expectPeriodicSchedule("1"));
//
//	}
	
//	@Test
//	public void devantCuisiniere() {
//		DiaLog.info("====== devantCuisiniere() ======");
//		cooker = mockCooker("1", "kitchen", ("company"));
//		md1 = mockMotionDetector("1", "Kitchen_1", ("company"));	// MD cuisinière
//		md2 = mockMotionDetector("2", "Kitchen_2", ("company"));	// MD cuisine
//		cs = mockContactSensor("1", "location", ("company"));
//		em = mockElectricMeter("1", "location", ("company"));
//		tm = mockTimer("1");
//		
//		assertFalse(tm.expectPeriodicSchedule("1"));
//		
//		// On définit le statut du contact sensor
//		cs.setState(new State("true", "1"));
//		
//		// On démarre la cuisinière
//		em.setCurrentElectricConsumption(new State("5f", "2"));
//		cooker.status(new State("On", "3"));
//		
//		// On detecte un mouvement devant la cuisine
//		
//		md2.setMotion(new State("true", "4")); 	// Cuisine
//		md1.motion(new State("true", "5"));		// Cuisinière
//		
//		
//		// On doit recevoir les valeurs mais rien ne se passe
//
//		
//		assertTrue(tm.expectPeriodicSchedule("1"));
//		
//		
//	}
	
	
	@Test
	public void expectTimer() {
		DiaLog.info("====== devantCuisiniere() ======");
		cooker = mockCooker("1", "kitchen", ("company"));
		md1 = mockMotionDetector("1", "Kitchen_1", ("company"));	// MD cuisinière
		md2 = mockMotionDetector("2", "Kitchen_2", ("company"));	// MD cuisine
		cs = mockContactSensor("1", "location", ("company"));
		em = mockElectricMeter("1", "location", ("company"));
		tm = mockTimer("1");
		
		// On définit le statut du contact sensor
		cs.setState(new State("true", "1"));
		
		// On démarre la cuisinière
		em.setCurrentElectricConsumption(new State("5f", "2"));
		cooker.status(new State("On", "3"));
		
		
		md2.setMotion(new State("false", "4")); 	// Cuisine
		md1.setMotion(new State("false", "5"));		// Cuisinière
		
		
		// On doit recevoir les valeurs mais rien ne se passe
				
		for (int i = 0 ; i < 18 ; i++)
			tm.timerTriggered("0", "1");
		
		
		assertTrue(tm.expectPeriodicSchedule("1"));

	}
	
	
}