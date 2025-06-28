package org.example.plantvszombies.Model;

import java.util.List;

public class Player {
    private String userName;
    private String password;
    private int level;
    private List<Plant> plants;

    public Player(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.level = 0;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }



}
