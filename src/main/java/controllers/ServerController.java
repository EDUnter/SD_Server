package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import payload.responses.ServerResponse;

import java.io.PrintWriter;

public class ServerController {
	private ObjectMapper objectMapper;
	private PrintWriter out;

	public ServerController(PrintWriter out, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.out = out;
	}

	public void getServer() {
		ServerResponse serverResponse = new ServerResponse("Hello there! I'm running...");

		String response = null;

		try {
			response = objectMapper.writeValueAsString(serverResponse);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, response);

	}

	public void notFound() {
		out.println("HTTP/1.1 404 Not Found");
		out.flush();
		out.close();
	}
}
