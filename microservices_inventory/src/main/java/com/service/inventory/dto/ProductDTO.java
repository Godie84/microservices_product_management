package com.service.inventory.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

/**
 * DTO que representa un Producto obtenido desde el microservicio de Productos.
 *
 * <p>
 * Este objeto se utiliza exclusivamente para la comunicación entre
 * el microservicio de Inventario y el microservicio de Productos.
 * </p>
 *
 * <p>
 * No debe ser persistido ni utilizado como entidad JPA.
 * </p>
 */
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Identificador único del producto */
    @NotNull(message = "El id del producto no puede ser nulo")
    private Long id;

    /** Nombre del producto */
    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String name;

    /** Precio unitario del producto */
    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double price;

    /** Descripción opcional del producto */
    private String description;

    public ProductDTO() {
        // Constructor vacío requerido para serialización/deserialización
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
