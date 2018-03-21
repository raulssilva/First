import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {

	public static void main(String[] args) throws IOException {
		Montador monta = new Montador();
		Pergunta p = monta.getPerguntas(1);
		try {
			System.setProperty("java.rmi.server.hostname", "localhost");
			LocateRegistry.createRegistry(9090);
			Naming.bind("rmi://localhost:9090/PerguntaInterf", p);
			
			System.out.println("Addition Server is ready. FOI");
		} catch (Exception e) {
			System.out.println("Addition Server failed: " + e);
		}
	}

}
