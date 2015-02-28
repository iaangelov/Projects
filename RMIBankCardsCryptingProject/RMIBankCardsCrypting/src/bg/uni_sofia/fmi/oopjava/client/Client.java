/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.client;

import bg.uni_sofia.fmi.oopjava.enums.Permissions;
import bg.uni_sofia.fmi.oopjava.exceptions.InvalidCardNumberException;
import bg.uni_sofia.fmi.oopjava.exceptions.NoPermissionException;
import bg.uni_sofia.fmi.oopjava.exceptions.TooManyTriesException;
import bg.uni_sofia.fmi.oopjava.server.ServerRemote;
import java.io.FileNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author John
 */
public class Client {

    private final int PORT_NUMBER = 1099;
    private final String SERVICE_NAME = "Server";
    private ServerRemote serverRemote;

    public Client() {
        init();
    }

    private void init() {
        try {
            Registry registry = LocateRegistry.getRegistry(PORT_NUMBER);
            serverRemote = (ServerRemote) registry.lookup(SERVICE_NAME);
        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    public String encryptCard(String cardNumber) throws RemoteException, NoPermissionException, TooManyTriesException, InvalidCardNumberException {
        return serverRemote.encryptCard(cardNumber);
    }

    public String decryptCard(String encryptedNumber) throws RemoteException, NoPermissionException {
        return serverRemote.decryptCard(encryptedNumber);
    }
    
    public void addUser(String name, String password, Permissions[] permissions) throws RemoteException, NoPermissionException, FileNotFoundException {
        serverRemote.addUser(name, password, permissions);
    }

    public void removeUser(String name) throws RemoteException, NoPermissionException {
        serverRemote.removeUser(name);
    }

    public boolean isValidUser(String userName, String password) throws RemoteException {
        return serverRemote.isValidUser(userName, password);
    }

}
