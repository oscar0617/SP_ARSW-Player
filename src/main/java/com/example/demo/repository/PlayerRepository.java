package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Player;
import java.util.List;

/**
 * Repository for players
 */
public interface PlayerRepository extends MongoRepository<Player, String>{

    /**
     * Returns the player, searching by player's email
     * @param email player's email
     * @return
     */
    public List<Player> findByEmail(String email);
}
