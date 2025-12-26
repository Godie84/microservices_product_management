package com.service.inventory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;

/**
 * DTO de respuesta para una compra realizada exitosamente.
 *
 * <p>
 * Cumple con el estándar JSON:API y mantiene los nombres de los
 * atributos en inglés para garantizar compatibilidad entre servicios,
 * mientras que la documentación se presenta en español.
 * </p>
 *
 * <pre>
 * {
 *   "data": {
 *     "type": "purchase",
 *     "id": "10",
 *     "attributes": {
 *       "purchased_amount": 2,
 *       "remaining_quantity": 8,
 *       "product_description": "Teclado mecánico"
 *     }
 *   }
 * }
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Respuesta que representa una compra realizada")
public record PurchaseResponseDTO(

        @Schema(
                description = "Tipo de recurso según el estándar JSON:API",
                example = "purchase"
        )
        String type,

        @Schema(
                description = "Identificador del producto comprado",
                example = "10"
        )
        @NotNull
        Long id,

        @Schema(description = "Atributos asociados a la compra")
        @NotNull
        Attributes attributes

) implements Serializable {

    /**
     * Constructor de conveniencia que asigna automáticamente
     * el tipo JSON:API 'purchase'.
     */
    public PurchaseResponseDTO(
            Long productId,
            Integer purchasedAmount,
            Integer remainingQuantity,
            String productDescription
    ) {
        this(
                "purchase",
                productId,
                new Attributes(
                        purchasedAmount,
                        remainingQuantity,
                        productDescription
                )
        );
    }

    /**
     * Atributos del recurso Purchase.
     *
     * <p>
     * Los nombres de los campos se mantienen en inglés para
     * cumplir con el contrato del API, pero su significado
     * se documenta en español.
     * </p>
     */
    @Schema(description = "Detalle de la compra")
    public record Attributes(

            @JsonProperty("purchased_amount")
            @Schema(
                    description = "Cantidad de unidades compradas del producto",
                    example = "2"
            )
            @NotNull
            @Positive
            Integer purchasedAmount,

            @JsonProperty("remaining_quantity")
            @Schema(
                    description = "Cantidad de unidades restantes en inventario",
                    example = "8"
            )
            @NotNull
            @PositiveOrZero
            Integer remainingQuantity,

            @JsonProperty("product_description")
            @Schema(
                    description = "Descripción del producto (opcional)",
                    example = "Teclado mecánico"
            )
            String productDescription

    ) implements Serializable {}
}
