package bg.uni_sofia.fmi.corejava.network_stress_tester;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class QueryFileReader {

	public static String getQueryFromFile(Path queryPath) throws IOException {
		String queryText = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(queryPath.toString()))) {
			StringBuilder queryBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				queryBuilder.append(line);
			}
			queryText = queryBuilder.toString();
		}
		return queryText;
	}

	public static String getExpectedResponseFromFile(Path expectedResponsePath) throws IOException {
		String responseText = null;
		try (BufferedReader reader = new BufferedReader(new FileReader(expectedResponsePath.toString()))) {
			StringBuilder responseBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				responseBuilder.append(line);
			}
			responseText = responseBuilder.toString();
		}
		return responseText;
	}
}
