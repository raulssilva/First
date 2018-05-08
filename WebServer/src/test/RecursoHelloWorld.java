package test;

import javax.ws.rs.GET; //import da biblioteca jersey
import javax.ws.rs.Path; //import da biblioteca jersey
import javax.ws.rs.Produces; //import da biblioteca jersey

@Path("/helloworld") // o @path define a URI do recurso que nesse caso será /helloworld
public class RecursoHelloWorld {

	@GET // utilizando apenas o verbo GET, ou seja, vou apenas ler o recurso
	@Produces("text/plain") // define qual tipo MIME é retornado para o cliente
	public String exibir(){
		return "Hello World";
	}
} 
