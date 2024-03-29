package io.getarrays.securecapita.PurchaseRequest.Repository;


import io.getarrays.securecapita.PurchaseRequest.Domain.PurchaseRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PurchaseRequestRepo extends JpaRepository<PurchaseRequest,Long> {
  //     PurchaseRequest findBypurchaseRequestNumber();


}
