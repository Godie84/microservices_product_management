package com.service.inventory.controller;

import com.service.inventory.dto.InventoryResponseDTO;
import com.service.inventory.dto.PurchaseResponseDTO;
import com.service.inventory.model.Inventory;
import com.service.inventory.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@Tag(name = "Inventario", description = "Operaciones relacionadas con la gestión de inventario")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // ===================== DTOs =====================

    @Schema(description = "Solicitud de compra de un producto")
    public static class PurchaseRequest {

        @Schema(
                description = "Cantidad de productos a comprar",
                example = "2",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private Integer amount;

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }
    }

    @Schema(description = "Solicitud para actualizar la cantidad de inventario")
    public static class InventoryUpdateRequest {

        @Schema(
                description = "Nueva cantidad disponible en inventario",
                example = "10",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        private Integer quantity;

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    // ===================== ENDPOINTS =====================

    @Operation(
            summary = "Consultar inventario por producto",
            description = "Obtiene la cantidad disponible de inventario para un producto específico"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Inventario encontrado",
            content = @Content(schema = @Schema(implementation = InventoryResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Producto o inventario no encontrado"
    )
    @GetMapping("/{productId}")
    public InventoryResponseDTO getInventory(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Long productId
    ) {
        Inventory inventory = inventoryService.getInventoryByProductId(productId);

        return new InventoryResponseDTO(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    @Operation(
            summary = "Actualizar inventario",
            description = "Actualiza la cantidad disponible de inventario para un producto"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Inventario actualizado correctamente",
            content = @Content(schema = @Schema(implementation = InventoryResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado"
    )
    @PutMapping("/{productId}")
    public InventoryResponseDTO updateInventory(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Long productId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para actualizar el inventario",
                    required = true
            )
            @RequestBody InventoryUpdateRequest request
    ) {
        Inventory inventory = inventoryService.updateQuantity(productId, request.getQuantity());

        return new InventoryResponseDTO(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    @Operation(
            summary = "Comprar producto",
            description = "Descuenta del inventario la cantidad solicitada para una compra"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Compra realizada exitosamente",
            content = @Content(schema = @Schema(implementation = PurchaseResponseDTO.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Inventario insuficiente"
    )
    @ApiResponse(
            responseCode = "404",
            description = "Producto o inventario no encontrado"
    )
    @PostMapping("/{productId}/purchase")
    public PurchaseResponseDTO purchaseProduct(
            @Parameter(description = "ID del producto", example = "1", required = true)
            @PathVariable Long productId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Cantidad a comprar",
                    required = true
            )
            @RequestBody PurchaseRequest request
    ) {
        Inventory inventory = inventoryService.decreaseQuantity(productId, request.getAmount());

        return new PurchaseResponseDTO(
                productId,
                request.getAmount(),
                inventory.getQuantity(),
                null // Inventory NO conoce detalles del producto
        );
    }
}
