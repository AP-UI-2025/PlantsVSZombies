package org.example.plantvszombies.Controller;

import javafx.scene.control.Alert;
import org.example.plantvszombies.Model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerController {
    static PlayerController userController;
    public static PlayerController getUserController() {
        if (userController == null) {
            userController = new PlayerController();
        }
        return userController;
    }
    PlayerController() {
        allPlayers = loadPlayers();
    }


    private ArrayList<Player> allPlayers;

    public static Player LogIn(Player player) {

    }

    public static Player SignUp(Player player) {

    }

    public static ArrayList<Player> loadPlayers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("players.txt"))) {
            List<Player> loadedUsers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                loadedUsers.add(Player.fromFileFormat(line));
            }
        }catch(FileNotFoundException e){
            return new ArrayList<>();
        } catch(IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return loadPlayers();
    }

    public static void savePlayers(ArrayList<Player> players) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("players.txt"))) {
            for (Player player : players) {
                writer.write(player.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    public void setAllPlayers(ArrayList<Player> allPlayers) {
        this.allPlayers = allPlayers;
    }
}
