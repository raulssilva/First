import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class CallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface{
	private static final long serialVersionUID = 1L;
	private int tamanhoSala;
	private String nome;
	private int score;
	
	public CallbackClientImpl(String nome) throws RemoteException {
		this.nome = nome;
		this.tamanhoSala = 2;
		this.score = 0;
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

}
