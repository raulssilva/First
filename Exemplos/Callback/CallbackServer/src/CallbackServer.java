import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;

public class CallbackServer  {
  public static void main(String args[]) {
    String registryURL;
    try{
      int RMIPortNum = 1099;
      startRegistry(RMIPortNum);
      CallbackServerImpl exportedObj = new CallbackServerImpl();
      registryURL = "rmi://localhost:" + RMIPortNum + "/callback";
      Naming.rebind(registryURL, exportedObj);
      System.out.println("Callback Server ready.");
    }
    catch (Exception re) {
      System.out.println("Exception in HelloServer.main: " + re);
    }
  } 

  private static void startRegistry(int RMIPortNum)
    throws RemoteException{
    try {
      Registry registry = LocateRegistry.getRegistry(RMIPortNum);
      registry.list( );
    }
    catch (RemoteException e) { 
      Registry registry = LocateRegistry.createRegistry(RMIPortNum);
    }
  }

}
