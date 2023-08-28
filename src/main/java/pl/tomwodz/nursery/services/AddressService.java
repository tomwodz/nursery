package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.Address;

public interface AddressService {
    void updateById(Long id, Address newAddress);

}
