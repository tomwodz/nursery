package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.Address;
import pl.tomwodz.nursery.repository.AddressDAO;

public interface AddressRepository extends AddressDAO, Repository<Address,Long> {
    @Modifying
    @Query("UPDATE Address a SET a.street = :#{#newAddress.street}, a.zipCode= :#{#newAddress.zipCode}, a.city = :#{#newAddress.city}  WHERE a.id = :id")
    void updateById(Long id, Address newAddress);
}
