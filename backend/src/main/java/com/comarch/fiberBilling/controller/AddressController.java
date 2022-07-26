package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.dto.AddressDTO;
import com.comarch.fiberBilling.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RequestMapping("/api/address")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Get all addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of addresses", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return addressService.getAll();
    }

    @Operation(summary = "Get address by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "ID not found", content = @Content)
    })
    @GetMapping(value = "/{addressId}")
    public ResponseEntity getAddressById(@PathVariable("addressId") String addressId) {
        return addressService.getAddressById(addressId);
    }


    @Operation(summary = "Add new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AddressDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AddressDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Address not given", content = @Content)})
    @PostMapping
    public ResponseEntity<AddressDTO> addAddress(@RequestBody AddressDTO addressDTO) {
        return addressService.addAddress(addressDTO);
    }
}
