import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws IOException {
		Montador monta = new Montador();
		Pergunta p = monta.getPerguntas(1);
		try {
			String ip = "192.168.43.86";
			int port = 1099;
			String name = "first";
			String address = "rmi://" + ip + ":" + port + "/" + name;
			
			System.setProperty("java.rmi.server.hostname", ip);
			LocateRegistry.createRegistry(port);
			Naming.rebind(address, p);
			
			System.out.println("Addition Server is ready. FOI");
		} catch (Exception e) {
			System.out.println("Addition Server failed: " + e);
		}
	}

}
