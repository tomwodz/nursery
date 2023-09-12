package pl.tomwodz.nursery.infrastructure.information.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.information.InformationFacade;
import pl.tomwodz.nursery.domain.information.dto.DeleteInformationResponseDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.domain.user.UserFacade;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/information")
public class InformationRestController {

    private final UserFacade userFacade;
    private final InformationFacade informationFacade;

    @GetMapping
    public ResponseEntity<List<InformationResponseDto>> getAllInformations() {
        return ResponseEntity.ok(this.informationFacade.findAllInformations());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InformationResponseDto> getInformationById(@PathVariable Long id){
        InformationResponseDto informationById = this.informationFacade.findInformationById(id);
        return ResponseEntity.ok(informationById);
    }

    @PostMapping(path = "/")
    public ResponseEntity<InformationResponseDto> addInformation(
            @RequestBody @Valid InformationRequestDto informationRequestDto) {
        this.userFacade.findUserById(informationRequestDto.author_id());
        InformationResponseDto informationSaved = this.informationFacade.saveInformation(informationRequestDto);
        return ResponseEntity.ok(informationSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformationResponseDto> update(@PathVariable Long id,
                                                               @RequestBody @Valid InformationRequestDto informationRequestDto){
        this.userFacade.findUserById(informationRequestDto.author_id());
        InformationResponseDto response = this.informationFacade.updateInformation(id, informationRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DeleteInformationResponseDto> deleteInformationById(@PathVariable Long id) {
       return ResponseEntity.ok(this.informationFacade.deleteInformation(id));
    }

}
