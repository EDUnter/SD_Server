package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import payload.responses.ServerResponse;

import java.io.PrintWriter;

public class ServerController {
	private ObjectMapper objectMapper;
	private String response;
	private PrintWriter out;

	public ServerController(PrintWriter out) {
		this.objectMapper = new ObjectMapper();
		this.response = null;
		this.out = out;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void getServer() {
		ServerResponse serverResponse = new ServerResponse("Hello there! I'm running...");

		try {
			setResponse(objectMapper.writeValueAsString(serverResponse));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		RootController.sendResponse(out, getResponse());

	}
}
