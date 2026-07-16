package mx.edu.tecdesoftware.market_backend.domain.service;

import mx.edu.tecdesoftware.market_backend.domain.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll() {
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRepository.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        if (purchase.getDate() == null) {
            purchase.setDate(LocalDateTime.now());
        }
        if (purchase.getState() == null || purchase.getState().isBlank()) {
            purchase.setState("P");
        }
        return purchaseRepository.save(purchase);
    }
}
