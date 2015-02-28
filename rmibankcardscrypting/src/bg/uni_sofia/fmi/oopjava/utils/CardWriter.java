/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author John
 */
public class CardWriter {

    public static void writeCards(TreeMap<String, ArrayList<String>> cards, File file) throws FileNotFoundException {
        try (Formatter in = new Formatter(file)) {
            cards.entrySet().stream().forEach((entry) -> {
                in.format("%s - %s%n", entry.getKey(), entry.getValue());
            });
        }
    }

    public static void writeCardsAlt(TreeMap<String, ArrayList<String>> cards, File file) throws FileNotFoundException {
        System.out.println("Here!");
        TreeMap<String, String> byEncrypted = new TreeMap<>();
        ArrayList<String> values;
        String key;
        for (Map.Entry entry : cards.entrySet()) {
            values = (ArrayList<String>) entry.getValue();
            key = (String) entry.getKey();
            for (String value : values) {
                byEncrypted.put(value, key);
            }
        }
        try (Formatter in = new Formatter(file)) {
            byEncrypted.entrySet().stream().forEach((entry) -> {
                in.format("%s - %s%n", entry.getKey(), entry.getValue());
            });
        }
    }

}
