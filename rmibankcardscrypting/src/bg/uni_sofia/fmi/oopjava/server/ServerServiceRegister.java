/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.server;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author John
 */
public class ServerServiceRegister {

    private static final int PORT_NUMBER = 1099;

    public static void createRegistry(ServerRemote server, String name) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(PORT_NUMBER);
        registry.rebind(name, server);
        System.out.println("Registry created");
    }

    public static void removeFromRegistry(ServerRemote server) throws NoSuchObjectException {
        UnicastRemoteObject.unexportObject(server, true);
        System.out.println("Server service removed from registry");
    }

    public static void registerServerObject(ServerRemote server, String name) throws RemoteException {
        UnicastRemoteObject.exportObject(server, PORT_NUMBER);
        System.out.println("Server service is registered");
    }
}
