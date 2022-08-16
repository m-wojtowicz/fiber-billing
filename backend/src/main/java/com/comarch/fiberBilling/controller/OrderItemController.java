package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.entity.OrderItem;
import com.comarch.fiberBilling.service.impl.OrderItemServiceImpl;
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
@RequestMapping("/api/orderItem")
public class OrderItemController {

    private final OrderItemServiceImpl orderItemService;

    @Operation(summary = "Get all user products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/all/{id}")
    public ResponseEntity<List<OrderItem>> getAllUserProducts(@PathVariable Long id) {
        return orderItemService.getAllUserProducts(id);
    }

    @Operation(summary = "Get all parameters of product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/parameters/{id}")
    public ResponseEntity getAllProductParameters(@PathVariable Long id) {
        return orderItemService.getAllProductParameters(id);
    }
}
