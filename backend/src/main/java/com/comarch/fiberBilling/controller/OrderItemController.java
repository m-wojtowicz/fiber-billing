package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.api.response.GetAllUserProducts;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.dto.OrderItemDTO;
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
    @GetMapping(value = "/user/{clientDataId}")
    public ResponseEntity<List<GetAllUserProducts>> getUserProducts(@PathVariable("clientDataId") String clientDataId, @RequestParam("pageNo") int pageNo, @RequestParam("filter") String filter, @RequestParam("filterType") String filterType) {
        return orderItemService.getUserProducts(clientDataId, pageNo, filter, filterType);
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

    @Operation(summary = "Add offer to order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @PostMapping(value = "/{orderId}/offer")
    public ResponseEntity addOffer(@PathVariable("orderId") String orderId, @RequestBody String offerId) {
        return orderItemService.addOffer(orderId, offerId);
    }

    @Operation(summary = "Remove offer from order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OrderItem", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity removeItem(@PathVariable("itemId") String itemId) {
        return orderItemService.removeItem(itemId);
    }
}
