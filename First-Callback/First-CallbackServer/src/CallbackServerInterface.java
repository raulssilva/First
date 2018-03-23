import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackServerInterface extends Remote {
	//public void imprimir() throws RemoteException;
	public String getEnunciado() throws RemoteException;
	public String[] getAlternativas() throws RemoteException;
	
	public void registerForCallback(CallbackClientInterface callbackClientObject) throws RemoteException;
	public void unregisterForCallback(CallbackClientInterface callbackClientObject) throws RemoteException;;
}