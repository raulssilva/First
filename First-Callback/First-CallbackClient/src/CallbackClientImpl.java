import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class CallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface{
	private static final long serialVersionUID = 1L;
	private int tamanhoSala;
	private String nome;
	private int score;
	private CallbackServerInterface server;
	
	public CallbackClientImpl(String nome, CallbackServerInterface server) throws RemoteException {
		this.nome = nome;
		this.tamanhoSala = 2;
		this.score = 0;
		this.server = server;
	}
		
	public int getTamanhoSala() {
		return tamanhoSala;
	}

	public void setTamanhoSala(int tamanhoSala) {
		this.tamanhoSala = tamanhoSala;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int valor) {
		this.score += valor;
	}
	
	public void defineTamanhoSala() {
		System.out.print("Quantos jogadores vão participar (incluindo você)? ");
		Scanner scanner = new Scanner(System.in);
		this.tamanhoSala = scanner.nextInt();
	}
	
	public void mostrarPergunta() throws RemoteException {
		System.out.println(server.getEnunciado());
		String[] alternativas = server.getAlternativas();
		for(int i = 0; i < alternativas.length; i++) {
			System.out.println((i+1) + " " + alternativas[i]);
		}
		
	}
	
	public void emEspera() {
		System.out.println("Esperando os outros jogadores!");
	}
	
	public void serverOcupado() {
		System.out.println("A sala está ocupada no momento!");
	}

}
