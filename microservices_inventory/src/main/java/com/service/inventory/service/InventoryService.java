package com.service.inventory.service;

import com.service.inventory.client.ProductClient;
import com.service.inventory.dto.ProductDTO;
import com.service.inventory.exception.*;
import com.service.inventory.model.Inventory;
import com.service.inventory.repository.InventoryRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de dominio encargado de la gestión del inventario.
 *
 * <p>
 * Contiene la lógica de negocio relacionada con:
 * <ul>
 *   <li>Consulta de inventario</li>
 *   <li>Actualización de cantidades</li>
 *   <li>Compra y validación de stock</li>
 * </ul>
 * </p>
 */
@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final ProductClient productClient;

    public InventoryService(InventoryRepository repository, ProductClient productClient) {
        this.repository = repository;
        this.productClient = productClient;
    }

    /**
     * Verifica si un producto existe consultando el microservicio de productos.
     *
     * @param productId identificador del producto
     * @return información del producto si existe
     * @throws ProductNotFoundException si el producto no existe
     */
    private ProductDTO verifyProductExists(Long productId) {
        try {
            return productClient.getProductById(productId);
        } catch (FeignException.NotFound ex) {
            throw new ProductNotFoundException(productId);
        }
    }

    /**
     * Obtiene el inventario de un producto.
     *
     * @param productId identificador del producto
     * @return inventario asociado al producto
     * @throws ProductNotFoundException si el producto no existe
     * @throws InventoryNotFoundException si no hay inventario registrado
     */
    @Transactional(readOnly = true)
    public Inventory getInventoryByProductId(Long productId) {
        verifyProductExists(productId);

        return repository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));
    }

    /**
     * Actualiza la cantidad de inventario de un producto.
     * <p>
     * Si el inventario no existe, se crea automáticamente.
     * </p>
     *
     * @param productId identificador del producto
     * @param newQuantity nueva cantidad de inventario
     * @return inventario actualizado
     * @throws ProductNotFoundException si el producto no existe
     */
    @Transactional
    public Inventory updateQuantity(Long productId, Integer newQuantity) {
        verifyProductExists(productId);

        Inventory inventory = repository.findByProductId(productId)
                .orElseGet(() -> new Inventory(productId, 0));

        inventory.setQuantity(newQuantity);
        return repository.save(inventory);
    }

    /**
     * Reduce la cantidad de inventario tras una compra.
     *
     * @param productId identificador del producto
     * @param amount cantidad a descontar
     * @return inventario actualizado
     * @throws ProductNotFoundException si el producto no existe
     * @throws InventoryNotFoundException si no hay inventario
     * @throws InsufficientInventoryException si no hay stock suficiente
     */
    @Transactional
    public Inventory decreaseQuantity(Long productId, Integer amount) {
        verifyProductExists(productId);

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() -> new InventoryNotFoundException(productId));

        if (inventory.getQuantity() < amount) {
            throw new InsufficientInventoryException(productId);
        }

        inventory.setQuantity(inventory.getQuantity() - amount);
        return repository.save(inventory);
    }
}
