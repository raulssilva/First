import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;

public class Pergunta extends UnicastRemoteObject implements PerguntaInterf{
	private String enunciado;
	private String[] alternativas = new String[4];
	private int respostaCerta;
	private int valor;
	
	public Pergunta() throws RemoteException {
		valor = 1;
	}
	
	public Pergunta(String enunciado, String[] alternativas, int respostaCerta) throws RemoteException {
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.respostaCerta = respostaCerta;
		this.valor = 1;
	}
	
	public Pergunta(String enunciado, String[] alternativas, int respostaCerta, int valor) throws RemoteException {
		this.enunciado = enunciado;
		this.alternativas = alternativas;
		this.respostaCerta = respostaCerta;
		this.valor = valor;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String[] getAlternativas() {
		return alternativas;
	}
	
	public String getAlternativa(int index) {
		return alternativas[index];
	}

	public void setAlternativas(String[] alternativas) {
		this.alternativas = alternativas;
	}
	
	public void addAlternativa(String alternativa, int index) {
		this.alternativas[index] = alternativa;
	}

	public int getRespostaCerta() {
		return respostaCerta;
	}

	public void setRespostaCerta(int respostaCerta) {
		this.respostaCerta = respostaCerta;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void imprimir() {
		System.out.println(enunciado);
		for(int i = 0; i < 4; i++) {
			System.out.println(alternativas[i]);
		}
		System.out.print("RESPOSTA: ");
		System.out.println(alternativas[respostaCerta]);
		System.out.println("-------------------------------");
	}
}