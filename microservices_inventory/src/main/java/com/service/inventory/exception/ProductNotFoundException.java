package com.service.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando un producto no existe
 * en el microservicio de productos.
 *
 * <p>
 * Se traduce automáticamente en una respuesta
 * HTTP 404 (Not Found).
 * </p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    /**
     * Crea una excepción indicando que el producto
     * solicitado no existe.
     *
     * @param productId identificador del producto
     */
    public ProductNotFoundException(Long productId) {
        super("Product not found with id: " + productId);
    }
}
