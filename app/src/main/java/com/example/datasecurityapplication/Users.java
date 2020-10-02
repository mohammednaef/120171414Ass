package com.example.datasecurityapplication;

import java.io.Serializable;

public class Users implements Serializable {
    int id;
    String name,password,identity,encryption,decrypted,gethash;

    public static final String TABLE_NAME = "Users";
    public static final String U_ID = "id";
    public static final String U_NAME = "name";
    public static final String U_PASSWORD = "password";
    public static final String U_IDENTITY = "identity";
    public static final String U_Encryption = "encryption";
    public static final String U_Decrypted = "decrypted";
    public static final String U_Hash = "gethash";


    public static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME +
            "("+ U_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            U_NAME +" TEXT,"+
            U_IDENTITY +" TEXT,"+
            U_PASSWORD +" TEXT,"+
            U_Encryption +" TEXT,"+
            U_Decrypted +" TEXT,"+
            U_Hash +" TEXT"+")";


    public Users(int id, String name, String password, String identity, String encryption, String decrypted, String gethash) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.identity = identity;
        this.encryption = encryption;
        this.decrypted = decrypted;
        this.gethash = gethash;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getDecrypted() {
        return decrypted;
    }

    public void setDecrypted(String decrypted) {
        this.decrypted = decrypted;
    }

    public String getGethash() {
        return gethash;
    }

    public void setGethash(String gethash) {
        this.gethash = gethash;
    }
}
