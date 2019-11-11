import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.RootController;
import repository.RootRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class RequestHandler extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private RootController rootController;

	public RequestHandler(Socket socket, RootRepository rootRepository) {
		this.socket = socket;
		this.rootController = new RootController(socket, rootRepository.getUserRepository(), rootRepository.getMessageRepository());

		try
		{
			this.in = new BufferedReader (new InputStreamReader(socket.getInputStream()));

			this.out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Erro na execução do servidor: " + e);
			System.exit(1);
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public PrintWriter getOut() {
		return out;
	}

	public RootController getRootController() {
		return rootController;
	}

	private void handleRoute(String route, JsonNode bodyJsonNode) throws IOException {
		switch(route) {
			case "GET /":
				getRootController().getServerController().getServer();
				break;
			case "GET /server":
				getRootController().getServerController().getServer();
				break;
			case "GET /users":
				getRootController().getUserController().getAllUsers();
				break;
			case "POST /user":
				String nickname = bodyJsonNode.get("nickname").asText();

				getRootController().getUserController().postUser(nickname);
				break;
			case "GET /messages":
				getRootController().getMessageController().getAllMessages();
				break;
			case "POST /message":
				Long id = bodyJsonNode.get("id").asLong();
				String message = bodyJsonNode.get("message").asText();

				getRootController().getMessageController().postMessage(id, message);
				break;
			default:
				out.println("HTTP/1.1 404 Not Found");
				break;
		}

		getIn().close();
		getOut().close();
		getSocket().close();
	}

	public void run() {
			try {

				StringBuilder buffer = new StringBuilder();
				String line = null;
				String request = null;
				String method = null;
				String route = null;
				JsonNode bodyJsonNode = null;
				long idParameter = 0;
				ObjectMapper objectMapper = new ObjectMapper();

				// lê a primeira linha: request-line
				if(in.ready() && (line = in.readLine()) != null) {
					request = line;
					int requestLength = request == null ? 0 : request.trim().length();

					//Trata a request line
					if (requestLength != 0 ) {

						//Trata da rota da request
						StringTokenizer tokens = new StringTokenizer(request);
						method = tokens.nextToken();
						String path = tokens.nextToken();
						route = method + " " + path;

						//Lê o body da request caso esta apresente o método "POST"
						if(method.equals("POST")) {
							while (in.ready() && (line = in.readLine()) != null) {
								buffer.append(line + "\n");
							}
							System.out.println(request);
							request = buffer.toString();
							int pos = request.trim().indexOf("{");
							String jsonBody = request.trim().substring(pos);
							System.out.println(jsonBody);
							bodyJsonNode = objectMapper.readTree(jsonBody);
						}
					}

					handleRoute(route, bodyJsonNode);
				}

			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: " + e);
				System.exit(1);
		}
	}
}