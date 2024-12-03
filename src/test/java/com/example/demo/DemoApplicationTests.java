package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.service.PlayerService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        playerRepository.deleteAll();
    }
    
    @Test
    void testGetPlayer_UserFound() throws Exception {
        playerService.createPlayer("testNick", "test@example.com");
        Player foundPlayer = playerService.getPlayer("testNick");
        assertNotNull(foundPlayer);
        assertEquals("testNick", foundPlayer.getNickName());
    }
    
    @Test
    void testGetPlayer_UserNotFound() {
        Exception exception = assertThrows(Exception.class, () -> {
            playerService.getPlayer("unknownNick");
        });
        assertEquals("User not found", exception.getMessage());
    }
    
    @Test
    void testGetAllPlayers() {
        Player player = new Player("testNick", "test@example.com", 1);
        playerRepository.save(player);
        List<Player> foundPlayers = playerService.getAllPlayers();
        assertEquals(1, foundPlayers.size());
        assertEquals(player, foundPlayers.get(0));
    }
    
    @Test
    void testGetLevelPlayer() throws Exception {
        Player player = new Player("testNick", "test@example.com", 2);
        playerRepository.save(player);
        int level = playerService.getLevelPlayer("testNick");
        assertEquals(2, level);
    }
    @Test
    void testDeletePlayer() throws Exception {
        Player player = new Player("testNick", "test@example.com", 1);
        playerRepository.save(player);
        playerService.deletePlayer("testNick");
        assertThrows(Exception.class, () -> playerService.getPlayer("testNick"));
    }

    @Test
    void testGetEmail() throws Exception {
        Player player = new Player("testNick", "test@example.com", 1);
        playerRepository.save(player);
        String email = playerService.getEmail("testNick");
        assertEquals("test@example.com", email);
    }

    @Test
    void testGetNickName() {
        Player player = new Player("testNick", "test@example.com", 1);
        String nickName = playerService.getNickName(player);
        assertEquals("testNick", nickName);
    }

    @Test
    void testUpdatePlayer_WithPlayerObject() {
        Player player = new Player("testNick", "test@example.com", 1);
        playerRepository.save(player);
        playerService.updatePlayer(player, "newEmail@example.com", 2);
        assertEquals("newEmail@example.com", player.getEmail());
        assertEquals(2, player.getLevel());
    }

    @Test
    void testUpdatePlayer_WithNickName() throws Exception {
        Player player = new Player("testNick", "test@example.com", 1);
        playerRepository.save(player);
        playerService.updateEmail("testNick", "newEmail@example.com");
        player = playerService.getPlayer("testNick");
        assertEquals("newEmail@example.com", player.getEmail());
    }

    @Test
    void testCreatePlayer() throws Exception {
        playerService.createPlayer("newNick", "new@example.com");
        Player createdPlayer = playerService.getPlayer("newNick");
        assertEquals("new@example.com", createdPlayer.getEmail());
    }
}