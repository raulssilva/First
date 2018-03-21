import java.rmi.Remote;
import java.rmi.RemoteException;
public interface PerguntaInterf extends Remote{
	public void imprimir() throws RemoteException;

}