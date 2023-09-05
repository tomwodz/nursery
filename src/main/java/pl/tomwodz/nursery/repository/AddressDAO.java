package pl.tomwodz.nursery.repository;


import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.Address;

@Repository
public interface AddressDAO {
    void updateById(Long id, Address newAddress);
}
