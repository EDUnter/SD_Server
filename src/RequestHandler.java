import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.RootController;
import repository.RootRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class RequestHandler extends Thread {
	private Socket socket;
	private RootController rootController;
	private ObjectMapper objectMapper;
	private BufferedReader in;

	public RequestHandler(Socket socket, RootRepository rootRepository) {
		this.socket = socket;
		this.rootController = new RootController(socket, rootRepository.getUserRepository(), rootRepository.getMessageRepository());
		this.objectMapper = new ObjectMapper();

		try {
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (IOException e) {
			System.out.println("Erro na execução do servidor: " + e);
			System.exit(1);
		}
	}

	public void run() {
		try {

			JsonNode jsonNode;
			String request;

			// lê a primeira linha: request-line
			request = in.readLine();
			int requestLength = request == null ? 0 : request.trim().length();

			//Trata a request line
			if (requestLength != 0) {
				//Trata da rota da request
				StringTokenizer tokens = new StringTokenizer(request);
				String method = tokens.nextToken();
				String path = tokens.nextToken();
				String route = method + " " + path;

				switch (route) {
					case "GET /":
						rootController.getServerController().getServer();
						break;
					case "GET /server":
						rootController.getServerController().getServer();
						break;
					case "GET /users":
						rootController.getUserController().getAllUsers();
						break;
					case "POST /user":
						for (int i = 0; i < 6; i++) {
							in.readLine();
						}

						//Lê o body da request
						String bodyUserFirstLine = in.readLine();
						String bodyUserIdLine = in.readLine();
						String bodyUserThirdLine = "}";

						String bodyUser = bodyUserFirstLine.trim() + bodyUserIdLine.trim() + bodyUserThirdLine.trim();
						jsonNode = objectMapper.readTree(bodyUser);
						String nickname = jsonNode.get("nickname").asText();

						rootController.getUserController().postUser(nickname);
						break;
					case "GET /messages":
						rootController.getMessageController().getAllMessages();
						break;
					case "POST /message":
						for (int i = 0; i < 6; i++) {
							in.readLine();
						}

						//Lê o body da request
						String bodyMsgFirstLine = in.readLine();
						String bodyMessageIdLine = in.readLine();
						String bodyMessageLine = in.readLine();
						String bodyMsgThirdLine = "}";

						String bodyMsg = bodyMsgFirstLine.trim() + bodyMessageIdLine.trim() + bodyMessageLine.trim() + bodyMsgThirdLine.trim();
						jsonNode = objectMapper.readTree(bodyMsg);

						long id = jsonNode.get("id").asLong();
						String message = jsonNode.get("message").asText();

						rootController.getMessageController().postMessage(id, message);
						break;
					default:
						rootController.getServerController().notFound();
						break;
				}

				this.in.close();
				this.socket.close();

			}

		} catch (IOException e) {
			System.out.println("Erro na execucao do servidor: " + e);
			System.exit(1);
		}
	}
}