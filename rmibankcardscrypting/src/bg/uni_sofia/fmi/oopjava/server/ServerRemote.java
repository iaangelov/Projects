/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.server;

import bg.uni_sofia.fmi.oopjava.enums.Permissions;
import bg.uni_sofia.fmi.oopjava.exceptions.InvalidCardNumberException;
import bg.uni_sofia.fmi.oopjava.exceptions.NoPermissionException;
import bg.uni_sofia.fmi.oopjava.exceptions.TooManyTriesException;
import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author John
 */
public interface ServerRemote extends Remote {

    String encryptCard(String cardNumber) throws RemoteException, NoPermissionException, TooManyTriesException, InvalidCardNumberException;

    String decryptCard(String encryptedNumber) throws RemoteException, NoPermissionException;
    
    void addUser(String name, String password, Permissions[] permissions) throws RemoteException, NoPermissionException, FileNotFoundException;
    
    void removeUser(String userName) throws RemoteException, NoPermissionException;
    
    boolean isValidUser(String userName, String password) throws RemoteException;
    
    TreeMap<String, ArrayList<String>> getNumbers() throws RemoteException;
}
