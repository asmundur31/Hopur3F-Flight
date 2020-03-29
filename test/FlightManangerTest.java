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
		// Búum til lista af fimm flugum
	    Flight[] flug = new Flight[5];
	    Airport rkv = new Airport("Reykjavíkurflugvöllur", "Reykjavík", 64.129393, -21.940944);
	    Airport ak = new Airport("Akureyrarflugvöllur", "Akureyri", 65.656987, -18.070608);
	    Airport isa = new Airport("Ísafjarðarflugvöllur", "Ísafjörður", 66.056827, -23.138580);
	    Airplain tf123 = new Airplain("TF-123");
	    Airplain tf456 = new Airplain("TF-456");
	    Date date1 = new Date(2020,2,26,20,30);
	    Date date2 = new Date(2020,2,27,7,30);
	    Date date3 = new Date(2020,2,27,7,45);
	    Date date4 = new Date(2019,2,26,8,45);
	    flug[0] = new Flight(rkv,ak,tf123,"RA-1234",date1);
	    flug[1] = new Flight(ak,isa,tf123,"AI-1234",date2);
	    flug[2] = new Flight(rkv,isa,tf456,"RI-1234",date1);
	    flug[3] = new Flight(isa,rkv,tf456,"IR-1234",date3);
	    flug[4] = new Flight(isa,ak,tf123,"IA-1234",date4);
	    // Látum svo FlightMananger fá þessi flug
	    flightMananger = new FlightMananger(flug);
	}
	
	@After
	public void tearDown() {
		flightMananger = null;
		flights = null;
	}
	  
	@Test
 	public void testSearchDate1() {
		// Leitum að flugi á eftirfarandi dagsetningu
		Date dagsetning = new Date(2020,2,26);
		flights = flightMananger.search(dagsetning);
		// Athugum hvort við fáum væntan fjölda fluga
		assertTrue(2==flights.length);
		// Athugum hvort flugin eru á dagsetningu sem var óskast eftir
		for(Flight f : flights) {
			assertEquals(f.getDate().getDate(), dagsetning.getDate());
			assertEquals(f.getDate().getMonth(), dagsetning.getMonth());
		}
		// Fyrsta vélin
		assertEquals("TF-123",flights[0].getAirplain().getName());
		assertEquals("Reykjavíkurflugvöllur",flights[0].getFrom().getName());
		assertEquals("Akureyrarflugvöllur", flights[0].getTo().getName());
		assertEquals("RA-1234",flights[0].getFlightNumber());
		// Önnur vélin
		assertEquals("TF-456",flights[1].getAirplain().getName());
		assertEquals("Reykjavíkurflugvöllur",flights[1].getFrom().getName());
		assertEquals("Ísafjarðarflugvöllur", flights[1].getTo().getName());
		assertEquals("RI-1234",flights[1].getFlightNumber());
	}
	
	@Test
 	public void testSearchDate2() {
		// Leitum að flugi á eftirfarandi dagsetningu
		Date dagsetning = new Date(2020,2,25);
		flights = flightMananger.search(dagsetning);
		// Athugum hvort við fáum tóman lista
		assertTrue(0==flights.length);
		assertNotNull(flights);
	}
	
	@Test
 	public void testSearchDate3() {
		// Leitum að flugi með of nákvæmari tímasetningu
		Date dagsetning = new Date(2020,2,27,7,45);
		flights = flightMananger.search(dagsetning);
		// Nú viljum við fá öll flug 27. mars 2020
		assertTrue(2==flights.length);
		// Fyrsta vélin
		assertEquals("TF-123",flights[0].getAirplain().getName());
		assertEquals("Akureyrarflugvöllur",flights[0].getFrom().getName());
		assertEquals("Ísafjarðarflugvöllur", flights[0].getTo().getName());
		assertEquals("AI-1234",flights[0].getFlightNumber());
		// Önnur vélin
		assertEquals("TF-456",flights[1].getAirplain().getName());
		assertEquals("Ísafjarðarflugvöllur",flights[1].getFrom().getName());
		assertEquals("Reykjavíkurflugvöllur", flights[1].getTo().getName());
		assertEquals("IR-1234",flights[1].getFlightNumber());
	}
	
	// Óþarfi að hafa þetta en var búinn að þessu
	@Test
	public void testSearchAirport() {
		// Leitum að flugi á eftirfarandi dagsetningu
	    Airport vollur = new Airport("Reykjavíkurflugvöllur", "Reykjavík", 64.129393, -21.940944);
		flights = flightMananger.search(vollur, true);
		// Athugum hvort við fáum rétt flug
		assertTrue(1==flights.length);
		// Athugum hvort flugið er það sem óskast var eftir
		assertEquals("TF-456",flights[0].getAirplain().getName());
		assertEquals("Ísafjarðarflugvöllur", flights[0].getFrom().getName());
		assertEquals("Reykjavíkurflugvöllur",flights[0].getTo().getName());
		assertEquals("IR-1234",flights[0].getFlightNumber());
	}
}
