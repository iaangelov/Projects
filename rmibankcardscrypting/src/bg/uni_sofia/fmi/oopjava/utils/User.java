/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.uni_sofia.fmi.oopjava.utils;

import bg.uni_sofia.fmi.oopjava.enums.Permissions;

/**
 *
 * @author John
 */
public class User {

    private String name;
    private String password;
    private Permissions[] permissions;

    public User(String name, String password, Permissions[] permissions) {
        setName(name);
        setPassword(password);
        setPermissions(permissions);
    }

    public User() {
        this("noName", "noPassword", new Permissions[0]);
    }

    public User(User u) {
        this(u.name, u.password, u.permissions);
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            this.name = "noName";
        }
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password;
        } else {
            this.password = "noPassword";
        }
    }

    public void setPermissions(Permissions[] permissions) {
        this.permissions = new Permissions[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            this.permissions[i] = permissions[i];
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Permissions[] getPermissions() {
        Permissions[] res = new Permissions[permissions.length];
        for (int i = 0; i < permissions.length; i++) {
            res[i] = permissions[i];
        }
        return res;
    }

    @Override
    public String toString() {
        String result = String.format("[%s, %s,", name, password);
        for (int i = 0; i < permissions.length; i++) {
            result += String.format(", %s", permissions[i]);
        }
        result += "]\n";
        return result;
    }

}
