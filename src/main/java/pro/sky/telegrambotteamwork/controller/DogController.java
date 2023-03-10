package pro.sky.telegrambotteamwork.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.telegrambotteamwork.model.Dog;
import pro.sky.telegrambotteamwork.service.DogService;

import java.util.Collection;

@RestController
@RequestMapping("/api/dog")
@AllArgsConstructor
public class DogController {
    private final DogService dogService;

    @PostMapping
    public ResponseEntity<Dog> addDog(@RequestBody Dog dog) {
        Dog createDog = dogService.addDog(dog);
        if (dog.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createDog);
    }

    @PutMapping
    public ResponseEntity<Dog> updateDog(@RequestBody Dog dog) {
        Dog editDog = dogService.updateDog(dog);
        if (editDog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editDog);
    }

    @GetMapping
    public ResponseEntity<Collection<Dog>> findDog() {
        Collection<Dog> findDogs = dogService.findDog();
        return ResponseEntity.ok(findDogs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Dog> deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
        return ResponseEntity.ok().build();
    }
}
