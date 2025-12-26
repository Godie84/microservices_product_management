package com.service.inventory.repository;

import com.service.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Inventory}.
 *
 * <p>
 * Proporciona operaciones CRUD básicas y consultas específicas
 * relacionadas con el inventario de productos.
 * </p>
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Busca el inventario asociado a un producto específico.
     *
     * @param productId identificador del producto
     * @return un {@link Optional} con el inventario si existe,
     *         o {@link Optional#empty()} si no se encuentra
     */
    Optional<Inventory> findByProductId(Long productId);
}
