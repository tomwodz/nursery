package pl.tomwodz.nursery.domain.information;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.information.dto.DeleteInformationResponseDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationNotFoundException;
import pl.tomwodz.nursery.model.Information;

import java.util.List;

import static pl.tomwodz.nursery.domain.information.InformationMapper.mapFromInformationRequestDtoToInformation;
import static pl.tomwodz.nursery.domain.information.InformationMapper.mapFromInformationToInformationResponseDto;

@AllArgsConstructor
@Transactional
public class InformationFacade {

    private final InformationRepository informationRepository;

    public List<InformationResponseDto> findAllInformations(){
        return this.informationRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(InformationMapper::mapFromInformationToInformationResponseDto)
                .toList();
    }

    public InformationResponseDto findInformationById(Long id) {
        return this.informationRepository.findById(id)
                .map(InformationMapper::mapFromInformationToInformationResponseDto)
                .orElseThrow(() -> new InformationNotFoundException("not found information id: " + id));
    }

    public InformationResponseDto saveInformation(InformationRequestDto informationRequestDto){
        InformationValidator.validateInformation(informationRequestDto);
        final Information information = mapFromInformationRequestDtoToInformation(informationRequestDto);
        final Information savedInformation = this.informationRepository.save(information);
        return mapFromInformationToInformationResponseDto(savedInformation);
    }

    public InformationResponseDto updateInformation(Long id, InformationRequestDto informationRequestDto){
        this.existsById(id);
        final Information information = InformationMapper.mapFromUpdateInformationRequestDtoToInformation(id, informationRequestDto);
        final Information savedInformation = this.informationRepository.save(information);
        return mapFromInformationToInformationResponseDto(savedInformation);
    }

    public DeleteInformationResponseDto deleteInformation(Long id) {
        this.existsById(id);
        this.informationRepository.deleteById(id);
        return DeleteInformationResponseDto.builder()
                .message("You deleted information with id: " + id)
                .status(HttpStatus.OK)
                .build();
    }

    private void existsById(Long id){
        if(!this.informationRepository.existsById(id)){
            throw new InformationNotFoundException("not found information id: " + id);
        }
    }

}
