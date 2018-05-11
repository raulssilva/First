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
	private int countResultado = 0;
	
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
				for(int i = 0; i < clientes.size(); i++) {
					clientes.elementAt(i).imprimirMensagem("O jogo vai come�ar. Prepare-se!");
				}
			if(clientes.contains(callbackClientObject)){

//				for(int i = 0; i < clientes.size(); i++) {
//					clientes.elementAt(i).aceitaResponder();
//				}
			}else {
				callbackClientObject.imprimirMensagem("A sala est� ocupada no momento!");
			}
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
				clientes.elementAt(i).imprimirMensagem("Jogador " + cliente.getNome() + " errou, voc� ganhou ponto!");
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
			cliente.imprimirMensagem("Jogador " + clienteRespondendo.getNome() + " leventou a m�o primeiro!");
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
				Thread.sleep(2000);
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
		
	public void mostrarPergunta(CallbackClientInterface cliente) throws RemoteException {
		countAck++;
		while(countAck != maxClientes) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!perguntas.isEmpty()) {
			
			cliente.imprimirMensagem("");
			cliente.imprimirMensagem("------------------------------------------------");
			cliente.imprimirMensagem("");
			
			cliente.imprimirMensagem(perguntas.peek().getEnunciado());
			cliente.imprimirMensagem("1) " + perguntas.peek().getAlternativa(0));
			cliente.imprimirMensagem("2) " + perguntas.peek().getAlternativa(1));
			cliente.imprimirMensagem("3) " + perguntas.peek().getAlternativa(2));
			cliente.imprimirMensagem("4) " + perguntas.peek().getAlternativa(3));

			
		}else {
			System.out.println("oi");
			pontuacaoMaxima(cliente);
		}
		
		countAck = 0;
	}
	
	public void pontuacaoMaxima(CallbackClientInterface cliente) throws RemoteException{
		countResultado++;
		while(countResultado != maxClientes) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int pontuacao_maxima = 0; 
		for(int i = 0; i < clientes.size(); i++) {
			if(pontuacao_maxima < clientes.elementAt(i).getScore()) {
				pontuacao_maxima = clientes.elementAt(i).getScore();
			}
		}
		
		//countResultado=0;
		
		int pontos = cliente.getScore();
		if(pontos < pontuacao_maxima) {
			cliente.imprimirMensagem("Sua pontua��o foi " + pontos + " pontos. Voc� perdeu :(");
		}else {
			cliente.imprimirMensagem("Sua pontua��o foi " + pontos + " pontos. Voc� ganhou :D");
		}
	}
}