package bg.uni_sofia.fmi.corejava.tests;

import static org.junit.Assert.*;

import java.util.concurrent.CyclicBarrier;

import org.junit.Test;

import bg.uni_sofia.fmi.corejava.network_stress_tester.ServerQueryThread;

public class ServerQueryThreadTest {

	ServerQueryThread sqtUnderTest = new ServerQueryThread("localhost", "testQuery", "testResponse", new CyclicBarrier(1));

	@Test
	public void isSuccessfulNegative() {
		assertEquals(false, sqtUnderTest.isSuccessful());
	}
}
