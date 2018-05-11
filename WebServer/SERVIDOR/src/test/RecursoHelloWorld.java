package test;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET; 
import javax.ws.rs.Path; 

@Path("/helloworld") 
public class RecursoHelloWorld {

	@GET 
	@Path("hello")
	public String exibir(){
		return "Hello World";
	}
	
	@GET
	@Path("oi")
	public String olaSala() {
		System.out.println("oi");
		return "Oi sala";
	}
	
	@GET
	@Path("aluno/{name}")
	public String alunoOi(@PathParam("name") String name) {
		
		return "oi, " +name;
	}
} 
