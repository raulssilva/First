import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;
import java.util.Vector;

public class CallbackServerImpl extends UnicastRemoteObject implements CallbackServerInterface{
	
	private static final long serialVersionUID = 1L;
	private Vector<CallbackClientInterface> clientes;
	private int maxClientes;
	private Stack<Pergunta> perguntas;
	private CallbackClientInterface clienteRespondendo;
	private int count;
	private int countAck = 0;
	
	protected CallbackServerImpl(Stack<Pergunta> perguntas) throws RemoteException {
		super();
		this.clientes = new Vector<CallbackClientInterface>();
		this.maxClientes = 2;
		this.perguntas  = perguntas;
		this.clienteRespondendo = null;
		this.count = 0;
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
			try {
				for(int i = 0; i < clientes.size(); i++) {
					clientes.elementAt(i).imprimirMensagem("O jogo vai começar. Prepare-se!");
				}
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(clientes.contains(callbackClientObject)){

//				for(int i = 0; i < clientes.size(); i++) {
//					clientes.elementAt(i).aceitaResponder();
//				}
			}else {
				callbackClientObject.imprimirMensagem("A sala está ocupada no momento!");
			}
		}
	}
	
	public void ackMostrarPergunta(CallbackClientInterface cliente) throws RemoteException {
		countAck++;
		System.out.println("entrou");
		while(countAck != maxClientes) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		countAck = 0;
		for(int i = 0; i < clientes.size(); i++) {
				clientes.elementAt(i).aceitaResponder();
		}
		
	}
	

	public boolean verificaResposta(int resposta, CallbackClientInterface cliente) throws RemoteException {
		if(this.perguntas.peek().getRespostaCerta() == resposta) {
			if(!this.perguntas.empty()) {
				this.perguntas.pop();
			}
			cliente.addScore(1);
			clienteRespondendo = null;
			
			for(int i = 0; i < clientes.size(); i++) {
				if(!clientes.elementAt(i).equals(cliente)) {
					clientes.elementAt(i).imprimirMensagem("Jogador " + cliente.getNome() + " acertou!");
				}
			}
			
			return true;
			//notificar os outros jogadores se acertou ou n
		}
		for(int i = 0; i < clientes.size(); i++) {
			if(!clientes.elementAt(i).equals(cliente)) {
				clientes.elementAt(i).addScore(1);
				clientes.elementAt(i).imprimirMensagem("Jogador " + cliente.getNome() + " errou, você ganhou ponto!");
			}
		}
		
		if(!this.perguntas.empty()) {
			this.perguntas.pop();
		}
		clienteRespondendo = null;
		
		return false;
	}
	
	public void aceitarPergunta(CallbackClientInterface cliente) throws RemoteException {
		count++;
		if(clienteRespondendo == null) {
			clienteRespondendo = cliente;
		}
		
		if(!cliente.equals(clienteRespondendo)) {
			//cliente.setFlagPergunta(false);
			cliente.imprimirMensagem("Jogador " + clienteRespondendo.getNome() + " leventou a mão primeiro!");
		}else {
			while(count != maxClientes) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			count = 0;
			cliente.responder();
		}
	}
	

	public void mostrarPrimeiraPergunta(CallbackClientInterface cliente) throws RemoteException {
		while(clientes.size() != maxClientes) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		cliente.imprimirMensagem(perguntas.peek().getEnunciado());
		cliente.imprimirMensagem("1) " + perguntas.peek().getAlternativa(0));
		cliente.imprimirMensagem("2) " + perguntas.peek().getAlternativa(1));
		cliente.imprimirMensagem("3) " + perguntas.peek().getAlternativa(2));
		cliente.imprimirMensagem("4) " + perguntas.peek().getAlternativa(3));
		
		cliente.setFlagPergunta(true);
	}
		
	public void mostrarPergunta() throws RemoteException {		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!perguntas.isEmpty()) {
			
			for(int i = 0; i < clientes.size(); i++) {
				clientes.elementAt(i).setFlagPergunta(true);
				clientes.elementAt(i).imprimirMensagem("");
				clientes.elementAt(i).imprimirMensagem("------------------------------------------------");
				clientes.elementAt(i).imprimirMensagem("");
				//clientes.elementAt(i).mostrarPergunta();
				
				clientes.elementAt(i).imprimirMensagem(perguntas.peek().getEnunciado());
				clientes.elementAt(i).imprimirMensagem("1) " + perguntas.peek().getAlternativa(0));
				clientes.elementAt(i).imprimirMensagem("2) " + perguntas.peek().getAlternativa(1));
				clientes.elementAt(i).imprimirMensagem("3) " + perguntas.peek().getAlternativa(2));
				clientes.elementAt(i).imprimirMensagem("4) " + perguntas.peek().getAlternativa(3));
				
			}
			
		}else {
			pontuacaoMaxima();
		}
	}
	
	public void pontuacaoMaxima() throws RemoteException{
		int pontuacao_maxima = 0; 
		for(int i = 0; i < clientes.size(); i++) {
			if(pontuacao_maxima < clientes.elementAt(i).getScore()) {
				pontuacao_maxima = clientes.elementAt(i).getScore();
			}
		}
		
		for(int i = 0; i < clientes.size(); i++) {
			int pontos = clientes.elementAt(i).getScore();
			if(pontos < pontuacao_maxima) {
				clientes.elementAt(i).imprimirMensagem("Sua pontuação foi " + pontos + " pontos. Você perdeu :(");
			}else {
				clientes.elementAt(i).imprimirMensagem("Sua pontuação foi " + pontos + " pontos. Você ganhou :D");
			}
		}
	}
}