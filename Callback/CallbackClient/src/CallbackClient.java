import java.rmi.*;

public class CallbackClient {

  public static void main(String args[]) {
    try {
      int portNum = 1099;
      int time = 10;
      String registryURL = "rmi://localhost:" + portNum + "/callback";  
      CallbackServerInterface h = (CallbackServerInterface)Naming.lookup(registryURL);
      System.out.println("Lookup completed " );
      System.out.println("Server said " + h.sayHello());
      CallbackClientInterface callbackObj =  new CallbackClientImpl();
      
      h.registerForCallback(callbackObj);
      System.out.println("Registered for callback.");
      try {
        Thread.sleep(time * 1000);
      }
      catch (InterruptedException ex){ // sleep over
      }
      h.unregisterForCallback(callbackObj);
      System.out.println("Unregistered for callback.");
    } 
    catch (Exception e) {
      System.out.println(
        "Exception in CallbackClient: " + e);
    }
  } //end main
}//end class