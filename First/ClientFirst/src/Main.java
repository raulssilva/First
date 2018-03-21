import java.rmi.Naming;

public class Main {

	public static void main(String[] args) {
		PerguntaInterf pergunta;
		try {
			pergunta = (PerguntaInterf) Naming.lookup("rmi://localhost:9090/PerguntaInterf");
			pergunta.imprimir();
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}
