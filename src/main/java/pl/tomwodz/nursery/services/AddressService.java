package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.domain.address.Address;

public interface AddressService {
    void updateById(Long id, Address newAddress);

}
