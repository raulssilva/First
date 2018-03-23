import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class CallbackServerImpl extends UnicastRemoteObject implements CallbackServerInterface{
	
	private Vector clientes;
	private Vector jogadores;
	private int maxClientes;
	private Pergunta[] perguntas;
	
	protected CallbackServerImpl(Pergunta[] perguntas) throws RemoteException {
		super();
		clientes = new Vector();
		maxClientes = 2;
		this.perguntas  = perguntas;
	}

//	public void imprimir() throws RemoteException{
//		
//	}
	
	public String getEnunciado() throws RemoteException{
		return perguntas[0].getEnunciado();
	}
	
	public String[] getAlternativas() throws RemoteException{
		return perguntas[0].getAlternativas();
	}
	
	public synchronized void registerForCallback(CallbackClientInterface callbackClientObject) throws RemoteException{
		if(clientes.size() == 0) {
			Jogador jogador = new Jogador(callbackClientObject.getNome());
			clientes.addElement(callbackClientObject);
			jogadores.addElement(jogador);
			
			System.out.println("Jogador " + jogador.getNome() + " adicionado!");
			
			// Chama um método da interface do cliente para definir o tamanho da sala
			maxClientes = callbackClientObject.getTamanhoSala();
			
			System.out.println("Limite de "+ maxClientes + " jogadores");
		}else if (!(clientes.contains(callbackClientObject))) {
			if(clientes.size() < maxClientes) {
				Jogador jogador = new Jogador(callbackClientObject.getNome());
				clientes.addElement(callbackClientObject);
				jogadores.addElement(jogador);
				System.out.println("Jogador " + callbackClientObject.getNome() + " adicionado!");
			}
		  }
	}
	
	public void unregisterForCallback(CallbackClientInterface callbackClientObject) throws RemoteException{
		if (clientes.removeElement(callbackClientObject)) {
			System.out.println("Jogador removido!");
		} else {
			System.out.println("Não foi possível remover " + callbackClientObject.getNome());
		}
	}
}