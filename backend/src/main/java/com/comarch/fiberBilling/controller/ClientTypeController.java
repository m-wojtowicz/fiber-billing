package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.dto.ClientTypeDTO;
import com.comarch.fiberBilling.service.ClientTypeService;
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
@RequestMapping("/api/client-type")
public class ClientTypeController {

    private final ClientTypeService clientTypeService;

    @Operation(summary = "Get all client types")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of client types", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<ClientTypeDTO>> getAllClientTypes() {
        return clientTypeService.getAll();
    }

    @Operation(summary = "Get client type by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client type", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientTypeDTO.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "ID not found", content = @Content)
    })
    @GetMapping(value = "/id/{clientTypeId}")
    public ResponseEntity getClientTypeById(@PathVariable("clientTypeId") String clientTypeId) {
        return clientTypeService.getClientTypeById(clientTypeId);
    }

    @Operation(summary = "Get client type by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client type", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientTypeDTO.class))}
            ),
            @ApiResponse(responseCode = "404", description = "ClientType not found", content = @Content)
    })
    @GetMapping(value = "/name/{clientTypeName}")
    public ResponseEntity getClientTypeByName(@PathVariable("clientTypeName") String clientTypeName) {
        return clientTypeService.getClientTypeByName(clientTypeName);
    }
}
