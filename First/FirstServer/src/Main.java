import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws IOException {
		Montador monta = new Montador();
		Pergunta p = monta.getPerguntas(1);
		try {
			String ip = "10.51.65.194";
			int port = 8080;
			String name = "first";
			String address = "rmi://" + ip + ":" + port + "/" + name;
			System.setProperty("java.rmi.server.hostname", ip);
			Registry registry = LocateRegistry.createRegistry(port);
			registry.rebind(address, p);
			System.out.println("Addition Server is ready. FOI");
//			System.setProperty("java.rmi.server.hostname", "localhost");
//			LocateRegistry.createRegistry(9090);
//			Naming.bind("rmi://localhost:9090/PerguntaInterf", p);
//			System.out.println("Addition Server is ready. FOI");
		} catch (Exception e) {
			System.out.println("Addition Server failed: " + e);
		}
	}

}
