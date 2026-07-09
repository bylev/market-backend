package mx.edu.tecdesoftware.market_backend.persistence;
import mx.edu.tecdesoftware.market_backend.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend.domain.service.Purchase;
import mx.edu.tecdesoftware.market_backend.persistence.crud.PurchaseCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CompraRepositorio implements PurchaseRepository {
    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public List<Purchase> getAll(){
        List<Compra> compras = (List<Compra>)  purchaseCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        List<Compra> compras = purchaseCrudRepository.findByIdCliente(clientId);
        return Optional.of(purchaseMapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase){
        Compra compra =  purchaseMapper.toCompra(purchase);
        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> producto.setCompra(compra));
        }
        return purchaseMapper.toPurchase(purchaseCrudRepository.save(compra));
    }
}
