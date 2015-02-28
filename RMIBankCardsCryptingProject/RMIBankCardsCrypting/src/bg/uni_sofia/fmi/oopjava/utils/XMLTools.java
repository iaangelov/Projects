/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;

/**
 *
 * @author John
 */
public class XMLTools {

    public static void writeToXML(User user, File file) throws FileNotFoundException {
        XStream xs = new XStream();
        try (Formatter out = new Formatter(file)) {
            String xml = xs.toXML(user);
            out.format(xml);
        }
    }

    public static User readFromXML(File file) {
        XStream xs = new XStream(new DomDriver());
        User user = (User) xs.fromXML(file);
        return user;
    }
}
