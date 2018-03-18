import java.rmi.*;
import java.rmi.server.*;

public class HelloWorldServer {

	public static void main(String[] args) {
		try {
			HelloWorldImpl Hello = new HelloWorldImpl();
			Naming.bind("rmi://localhost/ABC", Hello);
			
			System.out.println("Addition Server is ready.");
		} catch (Exception e) {
			System.out.println("Addition Server failed: " + e);
		}
	}
}
