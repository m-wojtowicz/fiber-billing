package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.dto.ClientDataDTO;
import com.comarch.fiberBilling.service.ClientDataService;
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
@RequestMapping("/api/client-data")
public class ClientDataController {

    private final ClientDataService clientDataService;

    @Operation(summary = "Get all client data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of client data", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            })
    })
    @GetMapping(value = "/all")
    public ResponseEntity<List<ClientDataDTO>> getAllClientData() {
        return clientDataService.getAll();
    }

    @Operation(summary = "Get client data by login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClientData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/user/{clientDataLogin}")
    public ResponseEntity getClientDataByLogin(@PathVariable("clientDataLogin") String clientDataLogin) {
        return clientDataService.getClientDataByLogin(clientDataLogin);
    }

    @Operation(summary = "Get client data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClientData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/{clientDataId}")
    public ResponseEntity getClientDataById(@PathVariable("clientDataId") String clientDataId) {
        return clientDataService.getClientDataById(clientDataId);
    }


    @Operation(summary = "Add new client data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ClientDataDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Client data not given", content = @Content)})
    @PostMapping
    public ResponseEntity<ClientDataDTO> addClientData(@RequestBody ClientDataDTO clientDataDTO) {
        return clientDataService.addClientData(clientDataDTO);
    }

    @Operation(summary = "Update client data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ClientDataDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Client data not given", content = @Content),
    })
    @PutMapping(value = "/{clientDataId}")
    public ResponseEntity<ClientDataDTO> changeClientData(@PathVariable("clientDataId") String clientDataId, @RequestBody ClientDataDTO clientDataDTO) {
        return clientDataService.changeClientData(clientDataId, clientDataDTO);
    }

    @Operation(summary = "Delete client data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ClientData", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDataDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @DeleteMapping(value = "/{clientDataId}")
    public ResponseEntity deleteClientDataById(@PathVariable("clientDataId") String clientDataId) {
        return clientDataService.deleteClientDataById(clientDataId);
    }
}
