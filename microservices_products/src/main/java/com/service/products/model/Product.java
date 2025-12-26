package com.service.products.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Entidad Producto.
 * Representa un producto dentro del sistema y define
 * la estructura de la tabla "productos" en la base de datos.
 * Esta clase es gestionada por JPA y utilizada por Hibernate
 * para la persistencia automática de datos.
 */
@Entity
@Table(name = "products")
public class Product {

    /**
     * Identificador único del producto.
     * Es la clave primaria de la tabla y se genera
     * automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name del producto.
     * No puede ser nulo ni vacío.
     * Se limita a 100 caracteres.
     */
    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    /**
     * Precio del producto.
     * Se utiliza BigDecimal para evitar errores
     * de precisión en valores monetarios.
     * No puede ser nulo.
     */
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * Descripción opcional del producto.
     * Permite almacenar información adicional
     * sobre el producto.
     */
    @Column(length = 255)
    private String description;

    public Product() {
        // Constructor requerido por JPA
    }


    /**
     * Obtiene el identificador del producto.
     *
     * @return id del producto
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id identificador del producto
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el name del producto.
     *
     * @return name del producto
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param name nombre del producto
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return precio del producto
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto.
     *
     * @param price precio del producto
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return descripción del producto
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param description descripción del producto
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
