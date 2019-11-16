import repository.RootRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static int DEFAULT_PORT=5000;
	private static RootRepository ROOT_REPOSITORY;

	public static void main(String[] args) {
		int port = DEFAULT_PORT;

		ServerSocket server = null;

		try	{
			server = new ServerSocket(port);
		} catch (Exception e) {
			System.err.println("Erro ao criar socket servidor...");
			e.printStackTrace();
			System.exit(-1);
		}

		//Inicia os repositórios para gerir as request's
		ROOT_REPOSITORY = new RootRepository();

		System.out.println("Servidor à espera de ligações no porto " + port);

		while(true) {
			try {
				Socket socket = server.accept();

				RequestHandler t = new RequestHandler(socket, ROOT_REPOSITORY);
				t.start();

			} catch (IOException e) {
				System.out.println("Erro na execucao do servidor: "+e);
				System.exit(1);
			}
		}
	}
}
