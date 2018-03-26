import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackClientInterface extends Remote {
	public int getTamanhoSala() throws RemoteException;
	public String getNome() throws RemoteException;
	public int getScore() throws RemoteException;
	public void defineTamanhoSala() throws RemoteException;
	public void mostrarPergunta() throws RemoteException;
	public void imprimirMensagem(String mensagem) throws RemoteException;
	public void addScore(int score) throws RemoteException;
	public void setFlagPergunta(boolean temPerguta) throws RemoteException;

	public boolean temPergunta() throws RemoteException;
	public void responder() throws RemoteException;
	public void aceitaResponder() throws RemoteException;
}