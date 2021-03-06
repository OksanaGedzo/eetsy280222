package ee.bcs.eetsy.domain.seller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    List<Seller> findByValidatedTrue();

}