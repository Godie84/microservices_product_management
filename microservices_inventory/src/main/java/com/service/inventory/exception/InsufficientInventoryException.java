package com.service.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando el inventario disponible
 * es insuficiente para completar una operación de compra.
 *
 * <p>
 * Esta excepción se traduce automáticamente en una
 * respuesta HTTP 400 (Bad Request).
 * </p>
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientInventoryException extends RuntimeException {

    /**
     * Crea una nueva excepción de inventario insuficiente.
     *
     * @param productId identificador del producto sin stock suficiente
     */
    public InsufficientInventoryException(Long productId) {
        super("Insufficient inventory for product with id: " + productId);
    }
}
