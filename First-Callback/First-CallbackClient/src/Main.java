import java.rmi.Naming;
import java.util.Scanner;

public class Main {

	private static Scanner s;

	public static void main(String[] args) {
		s = new Scanner(System.in);
		System.out.print("Informe seu nome: ");
		String nome = s.nextLine(); 
		
		try {
			String ip = "localhost";
			int port = 8080;
			String name = "first";
			String address = "rmi://" + ip + ":" + port + "/" + name;
			
			CallbackServerInterface callbackServerObject = (CallbackServerInterface) Naming.lookup(address);
			CallbackClientInterface callbackObj = new CallbackClientImpl(nome, callbackServerObject);
			callbackServerObject.registerForCallback(callbackObj);
			callbackServerObject.mostrarPrimeiraPergunta(callbackObj);
			for(int i = 0; i < 5; i++) {
				while(callbackObj.temPergunta() == false) {
					callbackServerObject.mostrarPergunta(callbackObj);
					callbackObj.setFlagPergunta(true);
				}
				while(callbackObj.temPergunta() == true) {
					callbackObj.aceitaResponder();
					callbackObj.setFlagPergunta(false);
				}
			}
			callbackServerObject.pontuacaoMaxima(callbackObj);
			
		} catch (Exception e) {
			System.out.println("Falha no servidor: " + e);
		}

	}

}
