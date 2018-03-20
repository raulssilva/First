import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;

public class HelloWorldServer {

	public static void main(String[] args) {
		try {
			HelloWorldImpl Hello = new HelloWorldImpl();
			LocateRegistry.createRegistry(9090);
			Naming.bind("rmi://localhost:9090/HelloWorldImpl", Hello);
			
			System.out.println("Addition Server is ready.");
		} catch (Exception e) {
			System.out.println("Addition Server failed: " + e);
		}
	}
}
