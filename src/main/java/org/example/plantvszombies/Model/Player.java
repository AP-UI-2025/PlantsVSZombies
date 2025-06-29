package org.example.plantvszombies.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String userName;
    private String password;
    private int level;
    private List<String> plants;

    public Player(String userName, String password , int level , ArrayList<String> plants) {
        this.userName = userName;
        this.password = password;
        this.level = 0;
        this.plants = plants;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public int getLevel() {
        return level;
    }
    public List<String> getPlants() {
        return plants;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPlants(List<String> plants) {
        this.plants = plants;
    }

    public String toFileFormat() {
        String plantsString = String.join("|" , plants);
        return userName + "," + password + "," + level + "," + plants;
    }

    public static Player fromFileFormat(String line) {
        String[] parts = line.split(",");
        ArrayList<String> plants;
        if (parts.length > 3 && !parts[3].isEmpty()) {
            plants = new ArrayList<>(Arrays.asList(parts[3].split("\\|")));
        }else {
            plants = new ArrayList<>();
        }
        return new Player(parts[0], parts[1], Integer.parseInt(parts[2] ) , plants);
    }


}
