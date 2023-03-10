package pro.sky.telegrambotteamwork.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.telegrambotteamwork.model.Cat;
import pro.sky.telegrambotteamwork.service.CatService;

import java.util.Collection;

@RestController
@RequestMapping("/api/cat")
@AllArgsConstructor
public class CatController {
    private final CatService catService;

    @PostMapping
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
        Cat createCat = catService.addCat(cat);
        if (cat.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createCat);
    }

    @PutMapping
    public ResponseEntity<Cat> updateCat(@RequestBody Cat cat) {
        Cat editCat = catService.updateCat(cat);
        if (editCat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editCat);
    }

    @GetMapping
    public ResponseEntity<Collection<Cat>> findCat() {
        Collection<Cat> findCats = catService.findCat();
        return ResponseEntity.ok(findCats);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cat> deleteCat(@PathVariable Long id) {
        catService.deleteCat(id);
        return ResponseEntity.ok().build();
    }
}
