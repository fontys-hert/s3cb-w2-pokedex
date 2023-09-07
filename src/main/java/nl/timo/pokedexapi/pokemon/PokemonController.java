package nl.timo.pokedexapi.pokemon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

// GET /pokemon -> get all caught pokemon
// POST /pokemon -> store pokemon in "pokedex"
// GET /pokemon/1 -> get details of first pokemon

// POST /battle/1/2 or /battle?pokemon=1&target=2 -> do attack!

@RestController
public class PokemonController {
    private final List<Pokemon> pokemonCollection;

    public PokemonController() {
        pokemonCollection = new ArrayList<>();
        pokemonCollection.add(new Pokemon("Pikachu", 20, 3));
        pokemonCollection.add(new Pokemon("Weedle", 5, -1));
    }

    @GetMapping("/pokemon")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(pokemonCollection);
    }

    @PostMapping("/pokemon")
    public ResponseEntity<?> add(@RequestBody PokemonDto dto) {
        Pokemon pokemonToAdd = null;

        if (dto.getName().equalsIgnoreCase("charmander")) {
            pokemonToAdd = new Pokemon("Charmander", 20, 6);
        }

        if (pokemonToAdd != null) {
            pokemonCollection.add(pokemonToAdd);
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.created(URI.create("/pokemon/" + (pokemonCollection.size() - 1))).build();
    }
}
