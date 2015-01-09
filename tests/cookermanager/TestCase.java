package cookermanager;
import static fr.inria.phoenix.diasuite.framework.mocks.Mock.*;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.phoenix.diasuite.framework.datatype.company.Company;
import fr.inria.phoenix.diasuite.framework.datatype.contact.Contact;
import fr.inria.phoenix.diasuite.framework.datatype.onoffstatus.OnOffStatus;
import fr.inria.phoenix.diasuite.framework.datatype.state.State;
import fr.inria.phoenix.diasuite.framework.mocks.ContactSensorMock;
import fr.inria.phoenix.diasuite.framework.mocks.CookerMock;
import fr.inria.phoenix.diasuite.framework.mocks.ElectricMeterMock;
import fr.inria.phoenix.diasuite.framework.mocks.MotionDetectorMock;
import fr.inria.phoenix.diasuite.framework.mocks.TimerMock;
import fr.inria.phoenix.scenario.kitchen.impl.context.ComponentBinder;


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
	
	@Test
	public void testOff() {
		
		cooker = mockCooker("1", "kitchen", new Company("company"));
		md1 = mockMotionDetector("1", "location", new Company("company"));
		md2 = mockMotionDetector("2", "location", new Company("company"));
		cs = mockContactSensor("1", "location", new Company("company"));
		em = mockElectricMeter("1", "location", new Company("company"));
		tm = mockTimer("1");
		
		em.setCurrentElectricConsumption(5f);
		cooker.state(new State("ON", "4546464564"));
		
		md2.setMotion(false);
		md1.motion(false);
		
		cs.state(new State("ON","45654564546"));
		
		tm.timerTriggered("0", "1");
	
		assertTrue(cooker.expectOff());
		
//		temperatureSensor = mockTemperatureSensor("TemperatureSensor", "A29", new Company("Tester"));
//		controlPanel = mockControlPanel("ControlPanel", "A29", new Company("Tester"));
//		hvacSystem = mockHVACSystem("HAVCSystem", "A29", new Company("Tester"));
//		
//		controlPanel.setTargetTemperature(15.0f);
//		hvacSystem.setStatus(HVACStatus.STOPPED);
//		temperatureSensor.temperature(10.0f, TemperatureUnit.CELSIUS);
//		
//		assertTrue(hvacSystem.expectWarmUp());
	}
	
}