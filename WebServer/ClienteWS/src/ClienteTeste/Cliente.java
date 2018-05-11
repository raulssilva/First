package ClienteTeste;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class Cliente {
	
	public static void main(String[] args) {
		
		Client client = Client.create();
		WebResource webResource = client.resource("http://localhost:8080/WebServer/helloworld/aluno/"+"Debora");
		
		String saida = webResource.get(String.class);
		
		System.out.println(saida);

		
	} 

}
