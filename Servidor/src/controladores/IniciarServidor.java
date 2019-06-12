package controladores;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import interfaces.InterfazRemota;
import remoto.ObjetoRemoto;

public class IniciarServidor {

	public IniciarServidor() throws RemoteException{
		inicializar();
	}
	
	public static void main(String[] args) {
		try {
			new IniciarServidor();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private void inicializar() throws RemoteException {
		
		InterfazRemota or = new ObjetoRemoto();
		
		try {
			LocateRegistry.createRegistry(1099);
			Naming.rebind("//127.0.0.1/SuperSarasaServer", or);
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
			System.out.println(dtf.format(now) + " - Server started successfully.");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
