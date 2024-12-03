package com.example.demo.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;

/**
 * Service layer for managing Player operations.
 */
@Service
public class PlayerService {

    ArrayList<String> sesionPlayers = new ArrayList<>();

    @Autowired
    PlayerRepository playerRepository;

    /**
     * Retrieves a player by their nickname.
     *
     * @param nickName the nickname of the player.
     * @return the Player object if found.
     * @throws Exception if the player is not found.
     */
    public Player getPlayer(String nickName) throws Exception{
        Optional<Player> player = playerRepository.findById(nickName);
        if(player.isPresent()){
            return player.get();
        }
        throw new Exception("User not found");
    }

    /**
     * Adds a player to the session list.
     *
     * @param nickName the nickname of the player.
     */
    public void addPlayerSesion(String nickkName){
        sesionPlayers.add(nickkName);
    }

    /**
     * Retrieves the list of players currently in session.
     *
     * @return a list of nicknames of players in session.
     */
    public List<String> getSesionPlayers(){
        return sesionPlayers;
    }

    /**
     * Retrieves all players.
     *
     * @return a list of all Player objects.
     */
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    /**
     * Retrieves the level of a player.
     *
     * @param nickName the nickname of the player.
     * @return the level of the player.
     * @throws Exception if the player is not found.
     */
    public int getLevelPlayer(String nickName) throws Exception{
        Player player = getPlayer(nickName);
        return player.getLevel();
    }

    /**
     * Deletes a player from the database.
     *
     * @param nickName the nickname of the player to delete.
     * @throws Exception if the player is not found.
     */
    public void deletePlayer(String nickName) throws Exception{
        Player player = getPlayer(nickName);
        playerRepository.delete(player);
    }

    /**
     * Retrieves the email of a player by their nickname.
     *
     * @param nickName the nickname of the player.
     * @return the email of the player.
     * @throws Exception if the player is not found.
     */
    public String getEmail(String nickName) throws Exception{
        Player player = getPlayer(nickName);
        return player.getEmail();
    }

    /**
     * Retrieves the nickname of a player.
     *
     * @param player the Player object.
     * @return the nickname of the player.
     */
    public String getNickName(Player player){
        return player.getNickName();
    }

    /**
     * Updates a player's email and level.
     *
     * @param player the Player object to update.
     * @param email the new email for the player.
     * @param level the new level for the player.
     */
    public void updatePlayer(Player player, String email, int level){
        player.setEmail(email);
        player.setLevel(level);
        playerRepository.save(player);
    }    

    /**
     * Updates a player's email and level by their nickname.
     *
     * @param nickName the nickname of the player to update.
     * @param email the new email for the player.
     * @param level the new level for the player.
     * @throws Exception if the player is not found.
     */
    public void updatePlayer(String nickName, String email, int level) throws Exception{
        Player player = getPlayer(nickName);
        player.setEmail(email);
        player.setLevel(level);
        playerRepository.save(player);
    } 

    /**
     * Creates a new player and saves them to the database.
     *
     * @param nickName the nickname of the new player.
     * @param email the email of the new player.
     */
    public void createPlayer(String nickName, String email){
        playerRepository.save(new Player(nickName, email, 0));
    }

    /**
     * Retrieves or creates a nickname for a player by their email.
     *
     * @param email the email of the player.
     * @return the nickname of the player.
     */
    public String getNiickNameByEmail(String email){
        List<Player> result = playerRepository.findByEmail(email);
        if(result.isEmpty()){
            String[] data = email.split("@");
            String nickName = data[0];
            createPlayer(nickName, email);
            return nickName;
        }
        else{
            return getNickName(result.get(0));
        }
    }

    /**
     * Updates a player's email by their nickname.
     *
     * @param nickName the nickname of the player.
     * @param email the new email for the player.
     * @throws Exception if the player is not found.
     */
    public void updateEmail(String nickname, String email) throws Exception{
        Player player = getPlayer(nickname);
        player.setEmail(email);
        playerRepository.save(player);
    }

    /**
     * Updates a player's score, incrementing it by 1.
     *
     * @param nickName the nickname of the player.
     * @throws Exception if the player is not found.
     */
    public void updateScore(String nickname) throws Exception {
        Player player = getPlayer(nickname);
        int actualScore = player.getScore();
        actualScore++;
        player.setScore(actualScore);
        playerRepository.save(player);
    }

}
