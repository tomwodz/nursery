package pl.tomwodz.nursery.domain.information;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import pl.tomwodz.nursery.domain.information.dto.DeleteInformationResponseDto;
import pl.tomwodz.nursery.domain.information.dto.InformationRequestDto;
import pl.tomwodz.nursery.domain.information.dto.InformationResponseDto;
import pl.tomwodz.nursery.domain.validator.ValidatorFacade;
import pl.tomwodz.nursery.infrastructure.information.controller.error.InformationNotFoundException;

import java.util.List;

import static pl.tomwodz.nursery.domain.information.InformationMapper.mapFromInformationToInformationResponseDto;

@AllArgsConstructor
@Transactional
public class InformationFacade {

    private final InformationRepository informationRepository;
    private final ValidatorFacade validatorFacade;
    private final InformationFactory informationFactory;

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
        validatorFacade.validationInformation(informationRequestDto);
        Information information = informationFactory.mapFromInformationRequestDtoToInformation(informationRequestDto);
        Information savedInformation = this.informationRepository.save(information);
        return mapFromInformationToInformationResponseDto(savedInformation);
    }

    public InformationResponseDto updateInformation(Long id, InformationRequestDto informationRequestDto){
        validatorFacade.validationInformation(informationRequestDto);
        this.existsById(id);
        Information information = informationFactory.mapFromInformationRequestDtoToInformation(informationRequestDto);
        information.setId(id);
        Information savedInformation = this.informationRepository.save(information);
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
