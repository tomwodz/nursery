package pl.tomwodz.nursery.repository;


import pl.tomwodz.nursery.model.Address;

public interface AddressDAO {
    void updateById(Long id, Address newAddress);
}
