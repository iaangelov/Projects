package bg.uni_sofia.fmi.corejava.network_stress_tester;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ServerQueryThread extends Thread {

	private String server;
	private String query;
	private String expectedResponse;
	private boolean successful;
	CyclicBarrier barrier;

	public ServerQueryThread(String server, String query, String expectedResponse, CyclicBarrier cyclicBarrier) {
		this.server = server;
		this.query = query;
		this.expectedResponse = expectedResponse;
		this.barrier = cyclicBarrier;
	}

	public boolean isSuccessful() {
		return successful;
	}

	private boolean isValidResponse(String response) {
		return response.equals(expectedResponse);
	}

	@Override
	public void run() {
		try {

			barrier.await();

			URL url = new URL(server);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(query);

			conn.connect();

			String response = conn.getResponseCode() + " " + conn.getResponseMessage();

			successful = isValidResponse(response);
		} catch (IOException | InterruptedException | BrokenBarrierException e) {
		}
	}

}
