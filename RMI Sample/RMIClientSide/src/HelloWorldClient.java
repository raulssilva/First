import java.net.MalformedURLException;
import java.rmi.*;

public class HelloWorldClient {
	public static void main(String[] args) {
		HelloWorld hello;
		try {
			hello = (HelloWorld) Naming.lookup("rmi://localhost/ABC");
			String request = hello.hello();
			System.out.println(request);
		} catch (Exception e) {
			System.out.println("HelloClient exception: " + e);
		}
	}
}