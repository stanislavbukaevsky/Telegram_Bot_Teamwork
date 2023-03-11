package pro.sky.telegrambotteamwork.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambotteamwork.model.Pet;
import pro.sky.telegrambotteamwork.service.PetService;


@RestController
@RequestMapping("api/pet")
@Tag(name = "Работа с животными", description = "Позволяет управлять населением питомника")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @Operation(
            summary = "Приём животного в приют",
            description = "Позволяет зарегистрировать новое животное в системе")
    @ApiResponse(
            responseCode = "200",
            description = "добавлено новое животное",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Pet.class))
    )
    @PutMapping
    public HttpStatus registerPet(@RequestBody Pet pet) {
        petService.addNew(pet);
        return HttpStatus.OK;
    }

    @Operation(
            summary = "Изменение данных животного",
            description = "Позволяет изменить параметры животного")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "параметры животного изменены",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Pet.class))
            )
    })
    @PostMapping("/{petId}")
    public HttpStatus updatePet(@PathVariable("petId") Long petId, @RequestBody Pet pet) {
        petService.updatePet(petId, pet);
        return HttpStatus.OK;
    }

//    @Operation(
//            summary = "Поиск животного",
//            description = "Позволяет найти животного по его id")
//    @ApiResponse(
//            responseCode = "200",
//            content = @Content(
//                    mediaType = MediaType.APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = Pet.class))
//    )
//    @GetMapping("/{petId}")
//    public HttpStatus getPet(@PathVariable ("petId") Long petId) {
//
//
//        return HttpStatus.OK;
//    }
}