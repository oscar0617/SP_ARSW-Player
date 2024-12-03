package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;
    
    @GetMapping("/v1/player")
    @ResponseBody
    public Player getplayer(@RequestParam String nickName) {
        try {
            return playerService.getPlayer(nickName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/v1/allplayers")
    @ResponseBody
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    
    @GetMapping("/v1/level")
    public int getLevelPlayer(@RequestParam String nickName) {
        try {
            return playerService.getLevelPlayer(nickName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    @DeleteMapping("/v1/{nickName}")
    public void deletePlayer(@PathVariable String nickName) {
        try {
            playerService.deletePlayer(nickName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @GetMapping("/v1/email")
    public String getEmail(@RequestParam String nickName) {
        try {
            return playerService.getEmail(nickName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    @GetMapping("/v1/nickname")
    public String getNickName(@RequestBody Player player) {
        return playerService.getNickName(player);
    }

    @PutMapping("/v1/player")
    public void updatePlayer(@RequestBody Player player, @RequestParam String email, @RequestParam int level) {
        playerService.updatePlayer(player, email, level);
    }

    @PutMapping("/v1/player/{nickname}")
    public void updatePlayer(@PathVariable String nickName, @RequestParam String email, @RequestParam int level) {
        try {
            playerService.updatePlayer(nickName, email, level);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/v1/{nickname}")
    public void createPlayer(@PathVariable String nickname, @RequestBody String email) {
        playerService.createPlayer(nickname, email);
    }

    @PostMapping("/v1/sesion/{nickName}")
    public void addSesionPlayer(@PathVariable String nickName){
        playerService.addPlayerSesion(nickName);
    }
    
    @GetMapping("/v1/sesion")
    public List<String> getMethodName(@RequestParam String param) {
        return playerService.getSesionPlayers();
    }
    
    @GetMapping("/v1/nickname/{email}")
    public String getNickName(@PathVariable String email) {
        return playerService.getNiickNameByEmail(email);
    }

    @PostMapping("/v1/score/{nickName}")
    public void addScorePlayer(@PathVariable String nickName){
        try {
            playerService.updateScore(nickName);
        } catch (Exception e) {
            System.out.println("Me rompi");
        }
    }
}
