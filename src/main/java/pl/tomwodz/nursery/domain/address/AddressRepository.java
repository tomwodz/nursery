package pl.tomwodz.nursery.domain.address;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface AddressRepository extends Repository<Address,Long> {
    @Modifying
    @Query("UPDATE Address a SET a.street = :#{#newAddress.street}, a.zipCode= :#{#newAddress.zipCode}, a.city = :#{#newAddress.city}  WHERE a.id = :id")
    void updateById(Long id, Address newAddress);
}
