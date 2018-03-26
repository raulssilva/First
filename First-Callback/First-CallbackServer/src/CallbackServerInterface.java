import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackServerInterface extends Remote {
	public String getEnunciado() throws RemoteException;
	public String[] getAlternativas() throws RemoteException;
	public boolean verificaResposta(int resposta, CallbackClientInterface cliente) throws RemoteException;
	public void aceitarPergunta(CallbackClientInterface cliente) throws RemoteException;
	public void mostrarPergunta() throws RemoteException;
	public void ackMostrarPergunta(CallbackClientInterface cliente) throws RemoteException;
	
	public void registerForCallback(CallbackClientInterface callbackClientObject) throws RemoteException;
}