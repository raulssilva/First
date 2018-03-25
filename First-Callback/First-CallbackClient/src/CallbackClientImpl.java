import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class CallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface{
	private static final long serialVersionUID = 1L;
	private int tamanhoSala;
	private String nome;
	private int score;
	private CallbackServerInterface server;
	private boolean flagPergunta;
	
	public CallbackClientImpl(String nome, CallbackServerInterface server) throws RemoteException {
		this.nome = nome;
		this.tamanhoSala = 2;
		this.score = 0;
		this.server = server;
		this.flagPergunta = false;
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
		
		flagPergunta = true;
	}
	
	public void responder() throws RemoteException {
		System.out.print("Sua resposta: ");
		Scanner scanner = new Scanner(System.in);
		int resposta = scanner.nextInt();
		if(server.verificaResposta(resposta - 1, this)) {
			System.out.println("Acertou! Ganhou um ponto");
		}else{
			System.out.println("Errou!");
		}		
	}
	
	public void aceitaResponder() throws RemoteException {
		System.out.print("Aceita responder? (s/n) ");
		Scanner scanner = new Scanner(System.in);
		String aceita = scanner.nextLine();
		flagPergunta = false;
		if(aceita.equalsIgnoreCase("s")) {
			server.aceitarPergunta(this);
		}
	}
	
	public void imprimirMensagem(String mensagem) {
		System.out.println(mensagem);
	}
	
	public boolean temPergunta() {
		return flagPergunta;
	}

}
