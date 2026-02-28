package com.example.context;

public class TestContext {

    private static String usuario;
    private static String password;

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        TestContext.usuario = usuario;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        TestContext.password = password;
    }
}

