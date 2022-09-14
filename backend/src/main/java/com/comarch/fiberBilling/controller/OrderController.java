package com.comarch.fiberBilling.controller;

import com.comarch.fiberBilling.model.api.request.PutConfigItems;
import com.comarch.fiberBilling.model.api.response.GetAllOrders;
import com.comarch.fiberBilling.model.api.response.GetConfigItems;
import com.comarch.fiberBilling.model.dto.OrderDTO;
import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.repository.OrderRepository;
import com.comarch.fiberBilling.service.OrderItemService;
import com.comarch.fiberBilling.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;

    private final OrderRepository  orderRepository;

    @Operation(summary = "Get all user orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of orders", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/user/{clientDataId}")
    public ResponseEntity<List<GetAllOrders>> getAllUserOrders(@PathVariable("clientDataId") String clientDataId, @RequestParam("pageNo") int pageNo, @RequestParam("filter") String filter) {
        return orderService.getUserOrders(clientDataId, pageNo, filter);
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

    @Operation(summary = "Create new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @PostMapping(value = "/user/{userId}")
    public ResponseEntity createOrder(@PathVariable("userId") String userId) {
        String businessKey;
        Order order = orderService.createOrder(userId);
        if (order == null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create("http://localhost:9000/camunda/process/new/" + order.getId())).
                POST(HttpRequest.BodyPublishers.ofString("")).
                build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            businessKey = jsonObject.getString("businessKey");
            order.setBusinessKey(businessKey);
            orderRepository.save(order);
        } catch (IOException | InterruptedException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Camunda error");
        }
        return ResponseEntity.ok(order.getId());
    }

    @Operation(summary = "Update order status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @PutMapping(value = "/{orderId}/status")
    public ResponseEntity updateStatus(@PathVariable("orderId") String orderId, @RequestBody String status) {
        return orderService.changeStatus(orderId, status);
    }

    @Operation(summary = "Get open Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/user/{userId}/open")
    public ResponseEntity getOpenOrder(@PathVariable("userId") String userId) {
        return orderService.getOpenOrder(userId);
    }

    @Operation(summary = "Get all data to configure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GetConfigItems.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @GetMapping(value = "/configure/{orderId}")
    public ResponseEntity getConfigureOrderData(@PathVariable("orderId") String orderId, @RequestParam("clientType") String clientType) {
        return orderService.getConfigItems(orderId, clientType);
    }

    @Operation(summary = "Post all data to configure")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "204", description = "ID not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID", content = @Content),
    })
    @PutMapping(value = "/configure/{orderId}")
    public ResponseEntity postConfigItems(@PathVariable("orderId") String orderId, @RequestBody PutConfigItems data) {
        return orderService.putConfigItems(orderId, data);
    }

    @GetMapping(value = "/process/{orderId}")
    public ResponseEntity updateProcess(@PathVariable("orderId") Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID NaN");

        String processRequest = new JSONObject()
                .put("businessKey", order.getBusinessKey())
                .toString();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9000/camunda/process/complete"))
                .POST(HttpRequest.BodyPublishers.ofString(processRequest))
                .header("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            java.lang.System.out.println(e);
        }
        return ResponseEntity.ok("Success");
    }
}
