package pl.tomwodz.nursery.infrastructure.presence.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tomwodz.nursery.domain.presence.PresenceFacade;
import pl.tomwodz.nursery.domain.presence.dto.PresenceResponseDto;
import pl.tomwodz.nursery.domain.presence.dto.PresenceRequestDto;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/presence")
public class PresenceRestController {

    private final PresenceFacade presenceFacade;


    @GetMapping(path = "/{id}")
    public ResponseEntity<PresenceResponseDto> getPresenceById(@PathVariable Long id){
        return ResponseEntity.ok(this.presenceFacade.findPresenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<PresenceResponseDto>> getAllPresences(){
        return ResponseEntity.ok(this.presenceFacade.findAllPresences());
    }

    @PostMapping
    public ResponseEntity<PresenceResponseDto> savePresence(PresenceRequestDto requestPresenceDto){
        return ResponseEntity.ok(this.presenceFacade.savePresence(requestPresenceDto));
    }

    @GetMapping(path = "/groupchildren/{id}")
    public ResponseEntity<List<PresenceResponseDto>> getAllPresencesByGroupChildrenId(@PathVariable Long id){
        return ResponseEntity.ok(this.presenceFacade.findAllPresencesByGroupChildrenId(id));
    }

}

