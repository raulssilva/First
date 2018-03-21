import java.rmi.*;
public interface PerguntaInterf extends Remote{
	public void imprimir() throws RemoteException;
	public int getRespostaCerta() throws RemoteException;
	public String getEnunciado() throws RemoteException;
	public String[] getAlternativas() throws RemoteException;

}
