package mx.edu.tecdesoftware.market_backend.persistence.crud;

import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository<Producto> extends CrudRepository<Producto, Integer> {

}
