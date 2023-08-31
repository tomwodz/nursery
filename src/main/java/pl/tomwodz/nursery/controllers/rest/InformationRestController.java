package pl.tomwodz.nursery.controllers.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.controllers.rest.information.request.CreateInformationRequestDto;
import pl.tomwodz.nursery.controllers.rest.information.request.UpdateInformationRequestDto;
import pl.tomwodz.nursery.controllers.rest.information.response.*;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.model.User;
import pl.tomwodz.nursery.services.InformationService;
import pl.tomwodz.nursery.services.UserService;

import java.time.LocalDateTime;
import java.util.List;

import static pl.tomwodz.nursery.controllers.rest.information.InformationMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/information")
public class InformationRestController {

    private final InformationService informationService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<GetAllInformationsResponseDto> getAllInformations() {
        List<Information> allInformations = this.informationService.findAll();
        GetAllInformationsResponseDto response = mapFromInformationsToGetAllInformationsResponseDto(allInformations);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GetInformationDto> getInformationById(@PathVariable Long id){
        Information information = this.informationService.findById(id);
        GetInformationDto response = mapFromInformationToGetInformationDto(information);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/author/{id}")
    public ResponseEntity<CreateInformationResponseDto> postInformation(
            @RequestBody @Valid CreateInformationRequestDto request,
            @PathVariable Long id) {
        User userFromDb = this.userService.findById(id);
        Information information = mapFromCreateInformationRequestDtoToInformation(request);
        information.setAuthor(userFromDb);
        Information savedInformation = this.informationService.save(information);
        CreateInformationResponseDto response = mapFromInformationToCreateInformationResponseDto(savedInformation);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/author/{author_id}")
    public ResponseEntity<UpdateInformationResponseDto> update(@PathVariable Long id,
                                                               @PathVariable Long author_id,
                                                               @RequestBody @Valid UpdateInformationRequestDto request){
        Information informationFromDb = this.informationService.findById(id);
        User userFromDb = this.userService.findById(author_id);
        Information newInformation = mapFromUpdateInformationRequestDtoToInformation(request);
        newInformation.setDateCreation(LocalDateTime.now());
        newInformation.setAuthor(userFromDb);
        this.informationService.updateById(id, newInformation);
        newInformation.setId(informationFromDb.getId());
        UpdateInformationResponseDto response = mapFromInformationToUpdateInformationResponseDto(newInformation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteInformationResponseDto> deleteInformationById(@PathVariable Long id) {
        this.informationService.deleteById(id);
        DeleteInformationResponseDto response = mapFromInformationToDeleteInformationResponseDto(id);
        return ResponseEntity.ok(response);
    }

}
