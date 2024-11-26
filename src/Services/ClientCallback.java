package Services;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallback extends Remote{
	void notifyBalanceChange(String accountNumber, BigDecimal newBalance) throws RemoteException;

	
}
