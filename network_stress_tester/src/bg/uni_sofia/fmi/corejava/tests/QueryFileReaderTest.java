package bg.uni_sofia.fmi.corejava.tests;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import bg.uni_sofia.fmi.corejava.network_stress_tester.QueryFileReader;

public class QueryFileReaderTest {

	@Test(expected = IOException.class)
	public void getQueryFromFileIOExceptionTest() throws Exception {
		QueryFileReader.getQueryFromFile(Paths.get("C:\\abc.txt"));
	}

	@Test(expected = IOException.class)
	public void getExpectedResponseFromFileIOExceptionTest() throws Exception {
		QueryFileReader.getExpectedResponseFromFile(Paths.get("C:\\abc.txt"));
	}
}
