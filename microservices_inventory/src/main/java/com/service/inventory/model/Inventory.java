package com.service.inventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Entidad que representa el inventario de un producto.
 *
 * <p>
 * Cada registro indica la cantidad disponible de un producto específico.
 * Este microservicio es el dueño del inventario y responsable de mantener
 * la consistencia del stock.
 * </p>
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    /**
     * Identificador único del registro de inventario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identificador del producto (proveniente del microservicio de Productos).
     */
    @NotNull
    @Column(name = "product_id", nullable = false, unique = true)
    private Long productId;

    /**
     * Cantidad disponible en inventario.
     */
    @NotNull
    @PositiveOrZero
    @Column(nullable = false)
    private Integer quantity;

    /**
     * Constructor por defecto requerido por JPA.
     */
    protected Inventory() {
    }

    /**
     * Crea un registro de inventario para un producto.
     *
     * @param productId identificador del producto
     * @param quantity  cantidad inicial disponible
     */
    public Inventory(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // ---------- Getters y Setters ----------

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Actualiza la cantidad disponible.
     *
     * @param quantity nueva cantidad
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
