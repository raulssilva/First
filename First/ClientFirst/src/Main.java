import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws RemoteException {
		Scanner ler = new Scanner(System.in);
		PerguntaInterf pergunta = null;
		try {
			String ip = "10.9.99.79";
			int port = 8080;
			String name = "first";
			String address = "rmi://" + ip + ":" + port + "/" + name;

			pergunta = (PerguntaInterf) Naming.lookup(address);
			String enunciado = pergunta.getEnunciado();
			String[] alternativas = pergunta.getAlternativas();
			System.out.println(enunciado);
			
			for(int i = 0; i < 4; i++) {
				int j = i + 1;
				System.out.println(j +"- "+ alternativas[i]);
			}
			
			int n = ler.nextInt();
			if(n-1 == pergunta.getRespostaCerta()) {
				System.out.println("Acertou, mizera");
			} else {
				System.out.println("Errou");
			}
		} catch (Exception e) {
			System.out.println("FirstClient exception: " + e);
		}
	}
}
