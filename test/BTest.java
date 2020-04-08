package test;

import org.junit.*;
import static org.junit.Assert.*;

import src.datastructures.*;


public class BTest {
	private Airplain p;

	@Before
	public void setUp() {
		p = new Airplain("A1");
	}

	@After
	public void tearDown() {
		p = null;
	}

	@Test
	public void testAirplane() {
		assertEquals("A1", p.getName());
		Boolean[][] seats = p.getAvailableSeats();
		for (int i=0; i<seats.length; i++) {
			for (int j=0; j<seats[0].length; j++) {
				System.out.print(seats[i][j]+" ");
				
			}
			System.out.println();
		}
			
	}

}
