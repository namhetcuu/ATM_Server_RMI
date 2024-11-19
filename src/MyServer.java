import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import Services.ITinhToan;

public class MyServer {
	public static void main(String[] args) {
		try {
            TinhToan tt = new TinhToan();
            ITinhToan atmInterface = tt; 
//            ITinhToan atmInterface = (ITinhToan) UnicastRemoteObject.exportObject(tt, 0);
//            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("TinhToan", atmInterface);
            System.out.print("Dang cho Client yeu cau: ");
        } catch (Exception tt) {
            System.out.print(tt);
        }
	}
}
