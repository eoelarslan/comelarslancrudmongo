package com.elarslan.crudmongo.controller;


import com.elarslan.crudmongo.controller.base.GenericResponseDTO;
import com.elarslan.crudmongo.controller.requestdto.FootballerRequestDTO;
import com.elarslan.crudmongo.controller.responsedto.FootballerResponseDTO;
import com.elarslan.crudmongo.exception.ResourceNotFoundException;
import com.elarslan.crudmongo.model.Footballer;
import com.elarslan.crudmongo.repository.IFootballerRepository;
import com.elarslan.crudmongo.service.FootballerService;
import com.elarslan.crudmongo.util.enums.MessageStatus;
import com.elarslan.crudmongo.util.helper.Constants;
import com.elarslan.crudmongo.util.helper.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Created by ersin on 25.11.2019.
 * <p>
 * New Football Player can be added or Existing players can be retrieved
 * updated and deleted.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/")
public class FootballerController {

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FootballerService footballerService;

    @Autowired
    private FootballerResponseDTO footballerResponseDTO;

    @Autowired
    private IFootballerRepository footballerRepository;

    @PostMapping(value = "/saveFootballer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveFootballer(@Valid @RequestBody Footballer footballer ) {
        log.debug("[FootballerController]: [Method] saveFootballer:\nSaved Footballer: " + footballer.toString());

        footballerService.save(footballer);
        modelMapper.map(footballer, footballerResponseDTO);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.USER_CREATED, null), footballerResponseDTO ));
    }

    @GetMapping(value = "/getAllFootballers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllFootballers() {
        log.debug("[FootballerController]: [Method] getAllFootballers:Enter");

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.findAll()));
    }

    @GetMapping(value = "/getMostValuableFootballer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMostValuableFootballer() {
        log.debug("[FootballerController]: [Method] getMostValuableFootballer:Enter");

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.findMostValuableFootballer()));
    }

    @GetMapping(value = "/getDetailedFootballer/bySurname/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDetailedFootballerBySurname(@PathVariable(value = "surname") @NotBlank String surname) {
        log.debug("[FootballerController]: [Method] getDetailedFootballerBySurname:\nName: " + surname);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.findBySurname(surname)));
    }

    @GetMapping(value = "/getFootballer/bySurname/{surname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFootballerBySurname(@PathVariable(value = "surname") @NotBlank String surname) {

        log.debug("[FootballerController]: [Method] getFootballerBySurname:\nName: " + surname);
        List<Footballer> footballerList = footballerService.findBySurname(surname);
        /*footballerList.forEach(footballer -> {
                footballerResponseDTO.setSurname(footballer.getSurname());
                footballerResponseDTO.setName(footballer.getName());});*/


        /* Below code is written in order to
        *  use java8 features. Stream, filter, lambda functions etc.
        *  But this code can be written more efficient!!!
        */
        footballerList.stream()
                .filter(footballer -> footballerList.size() == Constants.SINGLE)
                .forEach(footballer -> {
                    footballerResponseDTO.setSurname(footballer.getSurname());
                    footballerResponseDTO.setName(footballer.getName());
                });

        if (footballerResponseDTO.getName() != null &&
                footballerResponseDTO.getSurname() != null) {
            return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                    messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerResponseDTO));
        }
        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerList));
    }

    @PutMapping("/updateFootballer/{name}/{surname}")
    public ResponseEntity updateFootballer(@PathVariable(value = "name") @NotNull String name,
                                           @PathVariable(value = "surname") @NotNull String surname,
                                           @Valid @RequestBody FootballerRequestDTO footballerRequestDTO) {

        log.info("[FootballerController]: [Method] updateFootballer:\nSurname: " + "surname" + "\nFootballerRequestDTO: " + footballerRequestDTO.toString());

        // Assume that name and surname are  unique together.
        Footballer footballer = Optional.ofNullable(footballerService.findByNameAndSurname(name, surname))
                .orElseThrow(() -> new ResourceNotFoundException("Footballer", "name", name));

        footballer.setWorth(footballerRequestDTO.getWorth());

        footballerService.updateFootballer(footballer);
        modelMapper.map(footballer,footballerResponseDTO);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.USER_UPDATED, null),
                footballerResponseDTO));
    }

    @DeleteMapping("/deleteFootballer/{name}/{surname}")
    public ResponseEntity deleteFootballer(@PathVariable(value = "name") @NotNull String name,
                                           @PathVariable(value = "surname") @NotNull String surname) {

        log.debug("[FootballerController]: [Method] deleteFootballer:\nname: " + name
                    + "\nsurname: " +surname);
        footballerService.deleteFootballerByNameAndSurname(name, surname);
        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DELETED, null), null));

    }
}
