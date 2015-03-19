/*	Най-голям брой едновременни успешни заявки: 163
 * 	Време за отговор: 9844 milliseconds
 */

package bg.uni_sofia.fmi.corejava.network_stress_tester;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter server name: ");
		String serverName = in.nextLine();
		System.out.print("Enter the file path for the query(\"def\" for default): ");
		String queryFilePath = in.nextLine();
		try {
			if (!queryFilePath.equalsIgnoreCase("Def")) {
				System.out.print("Enter the file path for the expected response: ");
				String responseFilePath = in.nextLine();
				conductStressTest(Paths.get(queryFilePath), Paths.get(responseFilePath), serverName);
			} else {
				conductStressTest(Paths.get("src\\example_request.txt"),
						Paths.get("src\\example_expected_response.txt"), serverName);
			}
		} catch (InterruptedException e) {
			System.out.println("Thread error!");
		}
		in.close();
	}

	public static void conductStressTest(Path txtQuery, Path txtResponse, String serverName)
			throws InterruptedException { // must return some kind of pair:
											// (numOfQueries, execution time)

		boolean testPassed = true;
		int numberOfQueries = 0;
		int numberOfSuccessfulQueries = 0;
		long t0 = 0, t1 = 0;
		while (testPassed) {
			numberOfQueries++;
			
			System.out.println("Testing with " + numberOfQueries + " queries!");

			CyclicBarrier barrier = new CyclicBarrier(numberOfQueries);
			String query = null, response = null;
			try {
				query = QueryFileReader.getQueryFromFile(txtQuery);
				response = QueryFileReader.getExpectedResponseFromFile(txtResponse);
			} catch (IOException e) {
				System.out.println("Problem reading the file!");
			}

			List<ServerQueryThread> threads = new ArrayList<>();
			numberOfSuccessfulQueries = 0;

			for (int i = 0; i < numberOfQueries; i++) {
				ServerQueryThread thread = new ServerQueryThread(serverName, query, response, barrier);
				threads.add(thread);
			}
			t0 = System.currentTimeMillis();
			for (ServerQueryThread thread : threads) {
				thread.start();
			}

			for (ServerQueryThread thread : threads) {
				thread.join(0);
			}
			t1 = System.currentTimeMillis();

			for (ServerQueryThread thread : threads) {
				if (thread.isSuccessful()) {
					numberOfSuccessfulQueries++;
				}
			}

			if (numberOfSuccessfulQueries < numberOfQueries) {
				testPassed = false;
			}
		}

		System.out.println("Execution time: " + (t1 - t0) + " milliseconds");

		System.out.println("Number of successful queries: " + numberOfSuccessfulQueries);
	}
}
