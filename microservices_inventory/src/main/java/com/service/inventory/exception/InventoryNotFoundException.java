package com.service.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando no existe inventario
 * asociado a un producto específico.
 *
 * <p>
 * Se traduce automáticamente en una respuesta
 * HTTP 404 (Not Found).
 * </p>
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InventoryNotFoundException extends RuntimeException {

    /**
     * Crea una excepción indicando que no se encontró inventario
     * para el producto indicado.
     *
     * @param productId identificador del producto
     */
    public InventoryNotFoundException(Long productId) {
        super("Inventory not found for product with id: " + productId);
    }
}
