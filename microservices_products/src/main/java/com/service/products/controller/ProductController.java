package com.service.products.controller;

import com.service.products.model.Product;
import com.service.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Controlador REST encargado de exponer los endpoints
 * relacionados con la gestión de productos.
 *
 * Ruta base: /productos
 */
@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Operaciones sobre productos")
public class ProductController {

    /**
     * Servicio que contiene la lógica de negocio de productos.
     * Se inyecta mediante constructor (inyección de dependencias).
     */
    private final ProductService service;

    /**
     * Constructor del controlador.
     *
     * @param service Servicio de productos
     */
    public ProductController(ProductService service) {
        this.service = service;
    }

    /**
     * Crea un nuevo producto.
     *
     * @param product Objeto producto recibido en el cuerpo de la petición
     * @return Producto creado con estado HTTP 201 (CREATED)
     */
    @PostMapping
    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto en la base de datos")
    public ResponseEntity<Product> crear(@RequestBody Product product) {
        Product creado = service.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id Identificador del producto
     * @return Producto encontrado con estado HTTP 200 (OK)
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto por ID")
    public ResponseEntity<Product> getForId(@PathVariable Long id) {
        Product product = service.getForId(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Lista todos los productos registrados.
     *
     * @return Lista de productos con estado HTTP 200 (OK)
     */
    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public ResponseEntity<List<Product>> show() {
        return ResponseEntity.ok(service.showAll());
    }
}
