package test;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Date;
import src.controllers.*;
import src.datastructures.*;

public class FlightManangerTest {
	
	private FlightMananger flightMananger;
	private Flight[] flights;
	
	@Before
	public void setUp() {
	    Flight[] flug = new Flight[4];
	    Airport rkv = new Airport("Reykjavíkurflugvöllur", "Reykjavík", 64.129393, -21.940944);
	    Airport ak = new Airport("Akureyrarflugvöllur", "Akureyri", 65.656987, -18.070608);
	    Airport isa = new Airport("Ísafjarðarflugvöllur", "Ísafjörður", 66.056827, -23.138580);
	    Airplain tf123 = new Airplain("TF-123");
	    Airplain tf456 = new Airplain("TF-456");
	    Date date1 = new Date(2020,3,26,20,30);
	    Date date2 = new Date(2020,3,27,7,30);
	    Date date3 = new Date(2020,3,27,7,45);
	    flug[0] = new Flight(rkv,ak,tf123,"RA-1234",date1);
	    flug[1] = new Flight(ak,isa,tf123,"AI-1234",date2);
	    flug[2] = new Flight(rkv,isa,tf456,"RI-1234",date1);
	    flug[3] = new Flight(isa,rkv,tf456,"IR-1234",date3);
	    flightMananger = new FlightMananger(flug);
	}
	
	@After
	public void tearDown() {
		flightMananger = null;
		flights = null;
	}
	  
	@Test
 	public void testSearchDate() {
		// Leitum að flugi á eftirfarandi dagsetningu
		Date dagsetning = new Date(2020,3,26);
		flights = flightMananger.search(dagsetning);
		// Athugum hvort við fáum rétt flug
		assertTrue(1==flights.length);
		Flight f = flights[0];
		assertEquals("RA-1234",f.getAirplain().getName());
		assertEquals("Reykjavíkurflugvöllur",f.getFrom().getName());
		assertEquals("Akureyraflugvöllur", f.getTo().getName());
		assertEquals("RA-1234",f.getFlightNumber());
	}
}
