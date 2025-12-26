package com.service.inventory.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO de respuesta para el recurso Inventory siguiendo el estándar JSON API.
 *
 * <pre>
 * {
 *   "data": {
 *     "type": "inventory",
 *     "id": "1",
 *     "attributes": {
 *       "productId": 10,
 *       "quantity": 5
 *     }
 *   }
 * }
 * </pre>
 *
 * Diseñado para Java 17 utilizando records, garantizando:
 * - Inmutabilidad
 * - Simplicidad
 * - Seguridad en entornos de microservicios
 */
public record InventoryResponseDTO(
        String type,
        @NotNull Long id,
        @NotNull Attributes attributes
) implements Serializable {

    /**
     * Constructor de conveniencia para crear la respuesta de inventario
     * sin necesidad de especificar manualmente el tipo JSON API.
     */
    public InventoryResponseDTO(Long id, Long productId, Integer quantity) {
        this("inventory", id, new Attributes(productId, quantity));
    }

    /**
     * Atributos del recurso Inventory según JSON API.
     */
    public record Attributes(
            @NotNull Long productId,
            @NotNull @PositiveOrZero Integer quantity
    ) implements Serializable {}
}
