import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
	public static void main(String[] args) throws IOException {
		Montador monta = new Montador();
		CallbackServerImpl servidor = new CallbackServerImpl(monta.getPerguntas());  
		try {
			String ip = "localhost";
			int port = 8080;
			String name = "first";
			String address = "rmi://" + ip + ":" + port + "/" + name;
			
			startRegistry(port);
						
			Naming.rebind(address, servidor);
			
			System.out.println("Servidor está pronto.");
		} catch (Exception e) {
			System.out.println("Falha no servidor: " + e);
		}
	}
	
	public static void startRegistry(int port) throws RemoteException{
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			registry.list();
		}
		catch(RemoteException e){
			registry = LocateRegistry.createRegistry(port);
		}
	}
}
