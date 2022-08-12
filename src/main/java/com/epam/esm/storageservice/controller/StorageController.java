package com.epam.esm.storageservice.controller;

import com.epam.esm.storageservice.dto.StorageDto;
import com.epam.esm.storageservice.entity.DeleteResponse;
import com.epam.esm.storageservice.entity.SaveResponse;
import com.epam.esm.storageservice.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/storages")
@Validated
public class StorageController {
    private StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SaveResponse> saveStorage(@RequestBody @Valid StorageDto storageDto){
        return new ResponseEntity<>(service.saveStorage(storageDto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<StorageDto>> getStorages(){
        return new ResponseEntity<>(service.getStorages(),HttpStatus.OK);
    }
    @DeleteMapping()
    public ResponseEntity<DeleteResponse> deleteStorages(
            @RequestParam @NotEmpty @Size(min = 1, max = 200, message = "Count of ids should be between 1 or 200.") long[] id) {
        return new ResponseEntity<>(new DeleteResponse(service.deleteStorages(id)), HttpStatus.OK);
    }
}
