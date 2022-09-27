package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.api.response.GetAllUserInvoices;
import com.comarch.fiberBilling.service.InvoiceService;
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
@RequestMapping("/api/invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Operation(summary = "Get all user invoices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of invoices", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/user/{clientDataId}")
    public ResponseEntity<GetAllUserInvoices> getAllUserInvoices(@PathVariable("clientDataId") String clientDataId, @RequestParam("pageNo") int pageNo, @RequestParam("rowsCount") int rowsCount) {
        return invoiceService.getUserInvoices(clientDataId, pageNo, rowsCount);
    }
}
