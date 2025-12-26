package com.service.products.service;

import com.service.products.model.Product;
import com.service.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la lógica de negocio relacionada con la entidad {@link Product}.
 *
 * <p>
 * Esta capa actúa como intermediaria entre el controlador REST
 * y el repositorio de datos, evitando que el controlador acceda
 * directamente a la base de datos.
 * </p>
 */
@Service
public class ProductService {

    /**
     * Repositorio para operaciones de persistencia de productos.
     */
    private final ProductRepository repository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param repository repositorio de productos
     */
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea y persiste un nuevo producto en la base de datos.
     *
     * @param product objeto producto a guardar
     * @return producto persistido con su ID generado
     */
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id identificador del producto
     * @return producto encontrado o {@code null} si no existe
     */
    public Product getForId(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Lista todos los productos registrados en la base de datos.
     *
     * @return lista de productos
     */
    public List<Product> showAll() {
        return repository.findAll();
    }
}

