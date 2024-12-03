package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;

/**
 * Player class
 */
@Document(collection = "a")
@Data 
public class Player{

    @Id
    String nickName;

    @Field
    String email;

    @Field
    int level;

    @Field
    int score;

    /**
     * Constructor for player class
     * @param nickName nickname of the player
     * @param email email of the player
     * @param level level of the player
     */
    public Player(String nickName, String email, int level){
        this.nickName = nickName;
        this.email = email;
        this.level = level;
        this.score = 0;
    }

    
}