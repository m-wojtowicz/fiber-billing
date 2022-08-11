package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.dto.OrderItemDTO;
import com.comarch.fiberBilling.service.OrderItemParameterService;
import com.comarch.fiberBilling.service.OrderItemService;
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
@RequestMapping("/api/order-item")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderItemParameterService orderItemParameterService;

    @Operation(summary = "Get all items of order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of order items", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/order/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems(@PathVariable("order") String orderId) {
        return orderItemService.getOrderItems(orderId);
    }

    @Operation(summary = "Get order item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OrderItem", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/{orderItemId}")
    public ResponseEntity getOrderItem(@PathVariable("orderItemId") String orderItemId) {
        return orderItemService.getOrderItem(orderItemId);
    }

    @Operation(summary = "Get order item parameters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/{orderItemId}/parameters")
    public ResponseEntity getOrderItemParameters(@PathVariable("orderItemId") String orderItemId) {
        return orderItemParameterService.getOrderItemParameters(orderItemId);
    }

    @Operation(summary = "Add new order item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OrderItemDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "OrderItem not given", content = @Content)})
    @PostMapping
    public ResponseEntity<Long> addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.addOrderItem(orderItemDTO);
    }

    @Operation(summary = "Update order item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OrderItemDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "OrderItem not given", content = @Content),
    })
    @PutMapping(value = "/{orderItemId}")
    public ResponseEntity<OrderItemDTO> changeOrderItem(@PathVariable("orderItemId") String orderItemId, @RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.changeOrderItem(orderItemId, orderItemDTO);
    }

    @Operation(summary = "Delete order item by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OrderItem", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderItemDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @DeleteMapping(value = "/{orderItemId}")
    public ResponseEntity deleteOrder(@PathVariable("orderItemId") String orderItemId) {
        return orderItemService.deleteOrderItem(orderItemId);
    }
}
