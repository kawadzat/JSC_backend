package io.getarrays.securecapita.PurchaseRequest.Repository;

import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequestProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface PurchaseRequestProductRepo extends JpaRepository<PurchaseRequestProduct,Long> {

//    PurchaseRequestProduct findBypurchaseRequestProductNumber();
}
