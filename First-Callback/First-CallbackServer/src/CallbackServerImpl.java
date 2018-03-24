import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;
import java.util.Vector;

public class CallbackServerImpl extends UnicastRemoteObject implements CallbackServerInterface{
	
	private static final long serialVersionUID = 1L;
	private Vector<CallbackClientInterface> clientes;
	private int maxClientes;
	private Stack<Pergunta> perguntas;
	private boolean aceitouPergunta; 
	
	protected CallbackServerImpl(Stack<Pergunta> perguntas) throws RemoteException {
		super();
		this.clientes = new Vector<CallbackClientInterface>();
		this.maxClientes = 2;
		this.perguntas  = perguntas;
		this.aceitouPergunta = false;
	}
	
	public String getEnunciado() throws RemoteException{
		return perguntas.peek().getEnunciado();
	}
	
	public String[] getAlternativas() throws RemoteException{
		return perguntas.peek().getAlternativas();
	}
	
	public synchronized void registerForCallback(CallbackClientInterface callbackClientObject) throws RemoteException{
		if(clientes.size() == 0) {
			clientes.addElement(callbackClientObject);
			
			System.out.println("Jogador " + callbackClientObject.getNome() + " adicionado!");
			
			callbackClientObject.defineTamanhoSala();
			//talvez precise de um delay
			maxClientes = callbackClientObject.getTamanhoSala();
			
			System.out.println("Limite de "+ maxClientes + " jogadores");
			
			callbackClientObject.imprimirMensagem("Esperando os outros jogadores!");
		}else if (!(clientes.contains(callbackClientObject))) {
			if(clientes.size() < maxClientes) {
				clientes.addElement(callbackClientObject);
				System.out.println("Jogador " + callbackClientObject.getNome() + " adicionado!");
			}
			if(clientes.size() < maxClientes) {
				callbackClientObject.imprimirMensagem("Esperando os outros jogadores!");
			}
		}
		
		if(clientes.size() == maxClientes ) {
			if(clientes.contains(callbackClientObject)){
				for(int i = 0; i < clientes.size(); i++) {
					System.out.println("Jogador " + clientes.elementAt(i).getNome());
					clientes.elementAt(i).mostrarPergunta();
				}
			}else {
				callbackClientObject.imprimirMensagem("A sala está ocupada no momento!");
			}
		}
	}
	

	public boolean verificaResposta(int resposta, CallbackClientInterface cliente) throws RemoteException {
		aceitouPergunta = false;
		if(this.perguntas.peek().getRespostaCerta() == resposta) {
			this.perguntas.pop();
			
			for(int i = 0; i < clientes.size(); i++) {
				if(clientes.elementAt(i) != cliente) {
					clientes.elementAt(i).imprimirMensagem("Jogador " + cliente.getNome() + " acertou!");
				}
			}
			
			return true;
		}
		
		for(int i = 0; i < clientes.size(); i++) {
			if(clientes.elementAt(i) != cliente) {
				clientes.elementAt(i).imprimirMensagem("Jogador " + cliente.getNome() + " errou!");
			}
		}
		return false;
	}
	
	public void aceitarPergunta(CallbackClientInterface cliente) throws RemoteException {
		if(!aceitouPergunta) {
			aceitouPergunta = true;
			for(int i = 0; i < clientes.size(); i++) {
				clientes.elementAt(i).imprimirMensagem("Jogador " + cliente.getNome() + " aceitou responder!");
			}
			cliente.responder();
		}
		
	}
}