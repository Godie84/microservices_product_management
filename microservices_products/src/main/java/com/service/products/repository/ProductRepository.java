package com.service.products.repository;

import com.service.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad {@link Product}.
 *
 * <p>
 * Esta interfaz proporciona acceso a la base de datos utilizando
 * Spring Data JPA. Al extender {@link JpaRepository}, se heredan
 * automáticamente operaciones CRUD sin necesidad de implementar código.
 * </p>
 *
 * <p>
 * Operaciones disponibles por defecto:
 * <ul>
 *   <li>Guardar un producto</li>
 *   <li>Buscar por ID</li>
 *   <li>Listar todos los productos</li>
 *   <li>Actualizar un producto</li>
 *   <li>Eliminar un producto</li>
 * </ul>
 * </p>
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // No es necesario agregar métodos por ahora,
    // Spring Data JPA genera las implementaciones automáticamente.
}
