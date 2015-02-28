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
import bg.uni_sofia.fmi.oopjava.utils.User;
import bg.uni_sofia.fmi.oopjava.utils.XMLTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author John
 */
public class Server extends UnicastRemoteObject implements ServerRemote {

    private String userName;
    private TreeMap<String, ArrayList<String>> numbers;
    private final int STANDART_OFFSET = 5;
    private final String SUFFIX = ".xml";

    public Server() throws RemoteException {
        numbers = new TreeMap<>();
    }

    @Override
    public TreeMap<String, ArrayList<String>> getNumbers() throws RemoteException {
        TreeMap<String, ArrayList<String>> res = new TreeMap<>();
        res.putAll(numbers);
        return res;
    }

    @Override
    public String encryptCard(String cardNumber) throws RemoteException, NoPermissionException, TooManyTriesException, InvalidCardNumberException {
        cardNumber = cardNumber.replaceAll(" ", "");
        if (!hasPermission(userName, Permissions.ENCRYPT)) {
            throw new NoPermissionException("You don't have the required permssion to perform this operation!");
        }
        if (getNumberOfEncryptions(cardNumber) > 12) {
            throw new TooManyTriesException("You have exceeded 12 tries to encrypt!");
        }
        if (!isValidCardNumber(cardNumber)) {
            throw new InvalidCardNumberException("Invalid card number!");
        }
        int offset = generateOffset(cardNumber, STANDART_OFFSET);
        String encryptedNumber = encrypt(cardNumber, offset);
        if (numbers.get(cardNumber) != null) {
            numbers.get(cardNumber).add(encryptedNumber);
        } else {
            ArrayList<String> al = new ArrayList<>();
            al.add(encryptedNumber);
            numbers.put(cardNumber, al);
        }

        return encryptedNumber;
    }

    @Override
    public String decryptCard(String encryptedNumber) throws RemoteException, NoPermissionException {
        encryptedNumber = encryptedNumber.replaceAll(" ", "");
        if (!hasPermission(userName, Permissions.DECRYPT)) {
            throw new NoPermissionException("You don't have the required permssion to perform this operation!");
        }
        for (Map.Entry<String, ArrayList<String>> entrySet : numbers.entrySet()) {
            String key = entrySet.getKey();
            ArrayList<String> value = entrySet.getValue();
            if (value.contains(encryptedNumber)) {
                return key;
            }
        }
        String decrypted = decrypt(encryptedNumber);
        return decrypted;
    }

    @Override
    public void addUser(String name, String password, Permissions[] permissions) throws RemoteException, NoPermissionException, FileNotFoundException {
        if (!hasPermission(userName, Permissions.ADD_REMOVE_USER)) {
            throw new NoPermissionException("You don't have the required permssion to perform this operation!");
        }
        User user = new User(name, password, permissions);
        XMLTools.writeToXML(user, new File(name + SUFFIX));
    }

    @Override
    public void removeUser(String name) throws RemoteException, NoPermissionException {
        if (!hasPermission(userName, Permissions.ADD_REMOVE_USER)) {
            throw new NoPermissionException("You don't have the required permssion to perform this operation!");
        }
        File file = new File(name + SUFFIX);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public boolean isValidUser(String userName, String password) throws RemoteException {
        this.userName = userName;
        File file = new File(userName + SUFFIX);
        if (file.exists()) {
            User user = XMLTools.readFromXML(file);
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private String encrypt(String cardNumber, int offset) {
        int[] cardNumberAsIntegerArray = toIntArray(cardNumber);
        for (int i = 0; i < cardNumberAsIntegerArray.length; i++) {
            cardNumberAsIntegerArray[i] = (cardNumberAsIntegerArray[i] + offset) % 10;
        }
        String encypted = intArrayToString(cardNumberAsIntegerArray);
        return encypted;
    }

    private int getNumberOfEncryptions(String cardNumber) {
        if (numbers.get(cardNumber) != null) {
            return numbers.get(cardNumber).size();
        }
        return 0;
    }

    private boolean isValidCardNumber(String cardNumber) {
        int sum = 0;
        int[] cardNumberAsArray = toIntArray(cardNumber);
        if (cardNumberAsArray[0] != 3 && cardNumberAsArray[0] != 4 && cardNumberAsArray[0] != 5 && cardNumberAsArray[0] != 6) {
            return false;
        }

        for (int i = 0; i < cardNumberAsArray.length; i += 2) {
            cardNumberAsArray[i] *= 2;
            if (cardNumberAsArray[i] > 9) {
                cardNumberAsArray[i] = cardNumberAsArray[i] % 10 + cardNumberAsArray[i] / 10;
            }
        }

        for (int i = 0; i < cardNumberAsArray.length; i++) {
            sum += cardNumberAsArray[i];
        }
        
        return sum % 10 == 0;
    }

    private int[] toIntArray(String cardNumber) {
        char[] cardNumberAsChars = cardNumber.toCharArray();
        int[] intArray = new int[cardNumberAsChars.length];
        for (int i = 0; i < cardNumberAsChars.length; i++) {
            intArray[i] = cardNumberAsChars[i] - '0';
        }
        return intArray;
    }

    private String intArrayToString(int[] cardNumberAsIntegerArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cardNumberAsIntegerArray.length; i++) {
            sb.append(cardNumberAsIntegerArray[i]);
        }
        return sb.toString();
    }

    private int generateOffset(String cardNumber, int standartOffset) {
        if (numbers.get(cardNumber) != null) {
            return (numbers.get(cardNumber).size() + standartOffset) % 16;
        }
        return standartOffset;
    }

    private boolean hasPermission(String userName, Permissions permission) {
        User user = XMLTools.readFromXML(new File(userName + SUFFIX));
        Permissions[] permissions = user.getPermissions();
        for (Permissions per : permissions) {
            if (per.equals(permission)) {
                return true;
            }
        }
        return false;
    }

    private String decrypt(String encryptedNumber) {
        int[] numberAsIntArray = toIntArray(encryptedNumber);
        for (int i = 0; i < numberAsIntArray.length; i++) {
            numberAsIntArray[i] = (numberAsIntArray[i] + 10 - STANDART_OFFSET) % 10;
        }
        return intArrayToString(numberAsIntArray);
    }
}
