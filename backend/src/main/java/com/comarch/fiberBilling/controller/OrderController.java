package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.service.OrderItemService;
import com.comarch.fiberBilling.service.OrderService;
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
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @Operation(summary = "Get all user orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/user/{clientDataId}")
    public ResponseEntity<List<OrderDTO>> getAllUserOrders(@PathVariable("clientData") String clientDataId) {
        return orderService.getUserOrders(clientDataId);
    }

    @Operation(summary = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/{orderId}")
    public ResponseEntity getOrder(@PathVariable("orderId") String orderId) {
        return orderService.getOrder(orderId);
    }

    @Operation(summary = "Get order items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of order items", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/{orderId}/items")
    public ResponseEntity getOrderItems(@PathVariable("orderId") String orderId) {
        return orderItemService.getOrderItems(orderId);
    }

    @Operation(summary = "Add new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OrderDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "400", description = "Order not given", content = @Content)})
    @PostMapping
    public ResponseEntity<Long> addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @Operation(summary = "Update order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OrderDTO", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Order not given", content = @Content),
    })
    @PutMapping(value = "/{orderId}")
    public ResponseEntity<OrderDTO> changeOrder(@PathVariable("orderId") String orderId, @RequestBody OrderDTO orderDTO) {
        return orderService.changeOrder(orderId, orderDTO);
    }

    @Operation(summary = "Delete order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))}
            ),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") String orderId) {
        return orderService.deleteOrder(orderId);
    }
}
