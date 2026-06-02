package mx.edu.tecdesoftware.market_backend.persistence;

import mx.edu.tecdesoftware.market_backend.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Producto;


import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    // SELECT * FROM productos
    public List<Producto> getAll() {
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findbyIdCategoriaOrderByNombreAsc(idCategoria);

    }
    /*
     SELECT *
     FROM producto
     WHERE cantidad_stock <?
     AND estado = true
      */
    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
    }

    //Obtener un producto dado el id
    public Optional<Producto> getProductoById(int idProducto) {
        return productoCrudRepository.findById(idProducto);
    }

    //Guardar un producto
    public Producto save(Producto producto) {
        return (Producto) productoCrudRepository.save(producto);
    }
}



