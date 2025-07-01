package org.example.plantvszombies.Controller;

import javafx.scene.control.Alert;
import org.example.plantvszombies.Model.Player;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    private static PlayerController playerController;
    public static PlayerController getPlayerController() {
        if (playerController == null) {
            playerController = new PlayerController();
        }
        return playerController;
    }
    PlayerController() {
        allPlayers = loadPlayers();
    }


    private ArrayList<Player> allPlayers;

    public static Player LogIn(String username, String password) {
        for (Player player : getPlayerController().allPlayers){
            if (player.getUserName().equals(username) && player.getPassword().equals(password)) {
                return player;
            }
        }
        return null;
    }

    public static void SignUp(String username, String password ) {
        for (Player player : getPlayerController().allPlayers){
            if (player.getUserName().equals(username)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sign Up");
                alert.setHeaderText(null);
                alert.setContentText("this username is already taken!");
                alert.showAndWait();
                return;
            }
        }
        Player newPlayer = new Player(username , password , 0 , 0);
        savePlayers(newPlayer);
        getPlayerController().setAllPlayers(loadPlayers());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up");
        alert.setHeaderText(null);
        alert.setContentText("Sign Up Successful");
        alert.showAndWait();
    }

    public static ArrayList<Player> loadPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        try {
            String URL = "jdbc:mysql://localhost/plantsvszomies";
            String USER = "root";
            String PASSWORD = "";
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();
            String SQLCom = "SELECT * FROM players";
            stmt = con.prepareStatement(SQLCom);
            ResultSet rs = stmt.executeQuery(SQLCom);
            while (rs.next()) {
                String UserName = rs.getString("UserName");
                String Password = rs.getString("Password");
                int Level = rs.getInt("Level");
                int Plants = rs.getInt("Plants");
                players.add(new Player(UserName , Password , Level , Plants ));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return players;

    }

    public static void savePlayers(Player player)  {
        try {
            String URL = "jdbc:mysql://localhost/plantsvszomies";
            String USER = "root";
            String PASSWORD = "";
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();
            String SQLCom = String.format("INSERT INTO players (UserName , Password , Level , Plants) VALUES ('%s' , '%s' , '%s' , '%s')", player.getUserName(), player.getPassword(), player.getLevel(), player.getPlants());
            stmt = con.prepareStatement(SQLCom);
            stmt.execute(SQLCom);
        } catch (Exception e) {

        }


    }

    public ArrayList<Player> getAllPlayers() {
        return loadPlayers();
    }

    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
}
