package ClienteTeste;

import java.util.Scanner;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Cliente {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Informe seu nome: ");
		String nome = scanner.nextLine(); 
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/WebServer/First/start/");
		String saida = webResource.get(String.class);
		System.out.println(saida);
		
//		webResource = client.resource("http://localhost:8080/WebServer/First/init/"+nome);
//		
//		String saida = webResource.get(String.class);
//		System.out.println(saida);
//		
//		if(saida.equals("Defina a quantidade de jogadores")) {
//			int qtdJogadores = scanner.nextInt();
//			webResource = client.resource("http://localhost:8080/WebServer/First/definirQtd/"+qtdJogadores);
//			saida = webResource.get(String.class);
//			System.out.println(saida);
//		}
//		
//		 webResource = client.resource("http://localhost:8080/WebServer/First/mostrarPrimeiraPergunta/");
//		 saida = webResource.get(String.class);
//		 System.out.println(saida);

		
	} 

}
