package com.br.listadecompras;

public class Utilizador {
    String username;
    String password;

    public Utilizador() {
        username = "";
        password = "";
    }
    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuário: " + username +
                "  Senha: " + password;
    }
}
