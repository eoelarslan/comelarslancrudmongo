package com.elarslan.crudmongo.controller;


import com.elarslan.crudmongo.controller.base.GenericResponseDTO;
import com.elarslan.crudmongo.controller.requestdto.FootballerRequestDTO;
import com.elarslan.crudmongo.controller.responsedto.FootballerResponseDTO;
import com.elarslan.crudmongo.exception.ResourceNotFoundException;
import com.elarslan.crudmongo.model.Footballer;
import com.elarslan.crudmongo.repository.IFootballerRepository;
import com.elarslan.crudmongo.repository.base.IDataRepository;
import com.elarslan.crudmongo.service.FootballerService;
import com.elarslan.crudmongo.util.enums.MessageStatus;
import com.elarslan.crudmongo.util.helper.MessageHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @PostMapping("/saveFootballer")
    public ResponseEntity saveFootballer(@Valid @RequestBody Footballer footballer) {

        log.debug("[FootballerController]: [Method] saveFootballer:\nSaved Footballer: " + footballer.toString());

        footballerService.save(footballer);
        modelMapper.map(footballer, footballerResponseDTO);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.findAllByOrderByWorthDesc()));
    }

   /* @GetMapping("/getAllFootballers")
    public ResponseEntity getAllFootballers() {

        log.debug("[FootballerController]: [Method] getAllFootballers:Enter");

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.findAll()));
    }

    @GetMapping("/getDetailedFootballer/bySurname/{surname}")
    public ResponseEntity getDetailedFootballerBySurname(@PathVariable(value = "surname") @NotBlank String surname) {

        log.debug("[FootballerController]: [Method] getDetailedFootballerBySurname:\nName: " + surname);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.findBySurname(surname)));
    }

    @GetMapping("/getFootballer/bySurname/{surname}")
    public ResponseEntity getFootballerBySurname(@PathVariable(value = "surname") @NotBlank String surname) {

        log.debug("[FootballerController]: [Method] getFootballerBySurname:\nName: " + surname);
        modelMapper.map(footballerService.findBySurname(surname), footballerResponseDTO);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerResponseDTO));
    }

    @GetMapping("/getDetailedFootballer/byId/{id}")
    public ResponseEntity getDetailedFootballerById(@PathVariable(value = "id") @NotNull Long id) {

        log.debug("[FootballerController]: [Method] getDetailedFootballerById:\nId: " + id);

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.get(id)));
    }

    @GetMapping("/getFootballer/byId/{id}")
    public ResponseEntity getFootballerById(@PathVariable(value = "id") @NotNull Long id) {

        log.debug("[FootballerController]: [Method] getFootballerById:\nId: " + id);
        Optional<Footballer> footballer = Optional.ofNullable(footballerService.get(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));

        footballerResponseDTO.setName(footballer.isPresent() ? footballer.get().getName() : null);
        footballerResponseDTO.setSurname(footballer.isPresent() ? footballer.get().getSurname() : null);

        log.debug("[FootballerController]: [Method] footballerResponseDTO:" + footballerResponseDTO.toString());

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerResponseDTO));
    }

    @PutMapping("/updateFootballer/{id}")
    public ResponseEntity updateFootballer(@PathVariable(value = "id") @NotNull Long id,
                                           @Valid @RequestBody FootballerRequestDTO footballerRequestDTO) {

        log.info("[FootballerController]: [Method] updateFootballer:\nId: " + id + "\nFootballerRequestDTO: " + footballerRequestDTO.toString());
        Optional<Footballer> footballer = Optional.ofNullable(footballerService.get(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));

        // TODO rewrite as lambda function
        if (footballer.isPresent()) {
            log.debug("[FootballerController]: [Method] updateFootballer:\nFootballer - >" + footballer.get().toString());
            footballer.get().setWorth(footballerRequestDTO.getWorth());
        }

        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), footballerService.save(footballer.get())));
    }

    @DeleteMapping("/deleteFootballer/{id}")
    public ResponseEntity deleteFootballer(@PathVariable(value = "id") @NotBlank Long id) {

        log.debug("[FootballerController]: [Method] deleteFootballer:\nId: " + id);
        footballerService.deleteById(id);
        return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                messageHelper.getMessageByMessageStatus(MessageStatus.DATA_RETRIEVED, null), null));

    }*/
}
