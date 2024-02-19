package cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.rodriguez.jose.s05.t02.n01.model.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/players") //TODO controlar que solo se produzca un ANONIMOUS como player.
    public ResponseEntity<PlayerDTO> createPlayer(@RequestBody PlayerDTO playerDTO) {
        if (playerDTO.getName().isEmpty()) {
            playerDTO.setName("ANONIMOUS");
        }
        return new ResponseEntity<>(playerService.createPlayer(playerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/players")
    public ResponseEntity<PlayerDTO> updatePlayerName(@RequestParam Long id, @RequestParam String name) {
        return ResponseEntity.ok().body(playerService.updatePlayerName(id, name));
    }

    @PostMapping("/players/{id}/games")
    public ResponseEntity<String> addGame(@PathVariable Long id, @RequestBody GameDTO gameDTO) {
        playerService.addGame(id, gameDTO);
        return new ResponseEntity<>(("Game added successfully for Player ID:" + id), HttpStatus.OK);
    }

    @DeleteMapping("/players/{id}/games")
    public ResponseEntity<String> deleteGames(@PathVariable Long id) {
        playerService.deleteGames(id);
        return new ResponseEntity<>(("Games deleted successfully for Player ID:" + id), HttpStatus.OK);
    }

    @GetMapping("/players")
    public ResponseEntity<List<PlayerDTO>> getAllPlayersWithAvgSuccessRate() {
        return ResponseEntity.ok().body(playerService.getAllPlayersWithAvgSuccessRate());
    }

    @GetMapping("/players/{id}/games")
    public ResponseEntity<List<GameDTO>> getGamesByPlayerId(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService.getGamesByPlayerId(id));
    }

    @GetMapping("/players/ranking")
    public ResponseEntity<Double>  getAverageSuccessRate() {
        return ResponseEntity.ok().body(playerService.getAverageSuccessRate());
    }

    @GetMapping("/players/ranking/loser")
    public ResponseEntity<PlayerDTO> getLoserPlayer() {
        return ResponseEntity.ok().body(playerService.getLoserPlayer());
    }

    @GetMapping("/players/ranking/winner")
    public ResponseEntity<PlayerDTO> getWinnerPlayer() {
        return ResponseEntity.ok().body(playerService.getWinnerPlayer());
    }

}