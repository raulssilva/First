package ClienteTeste;

import java.util.Scanner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Cliente {
	
	private static Scanner sc;
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		System.out.print("Informe seu nome: ");
		String nome = scanner.nextLine(); 
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/WebServer/First/start/");
		String saida = webResource.get(String.class);
		//System.out.println(saida);
		
		webResource = client.resource("http://localhost:8080/WebServer/First/init/"+nome);
		
		saida = webResource.get(String.class);
		System.out.println(saida);
		
		if(saida.equals("Defina a quantidade de jogadores")) {
			int qtdJogadores = scanner.nextInt();
			webResource = client.resource("http://localhost:8080/WebServer/First/definirQtd/"+qtdJogadores);
			saida = webResource.get(String.class);
			System.out.println(saida);
			scanner.nextLine();
		}
		
		String aceitarResposta;
		
		for(int i = 0; i < 5; i++) {
			webResource = client.resource("http://localhost:8080/WebServer/First/mostrarPergunta/");
			saida = webResource.get(String.class);
			System.out.print("\n"+ (i+1) + "- ");
			System.out.println(saida);
			
			System.out.println("\nAperte enter!");
			
			sc = new Scanner(System.in);
			aceitarResposta = sc.nextLine();
			if(aceitarResposta != null){
				webResource = client.resource("http://localhost:8080/WebServer/First/aceitarResponder/"+nome);
				saida = webResource.get(String.class);
				System.out.println(saida);
				aceitarResposta = null;
			}
			
			if(saida.equals("Qual a sua resposta? ")){
				int resposta = scanner.nextInt();
				webResource = client.resource("http://localhost:8080/WebServer/First/responder?nome=" + nome + "&resposta=" + resposta);
				saida = webResource.get(String.class);
				System.out.println(saida);
			}
		}
		webResource = client.resource("http://localhost:8080/WebServer/First/resultado?nome=" + nome);
		saida = webResource.get(String.class);
		System.out.println(saida);
	} 

}
