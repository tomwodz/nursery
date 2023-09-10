package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.domain.address.Address;
import pl.tomwodz.nursery.domain.address.AddressRepository;
import pl.tomwodz.nursery.services.AddressService;
@AllArgsConstructor
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressDAO;

    @Override
    public void updateById(Long id, Address newAdrress) {
        this.addressDAO.updateById(id, newAdrress);
    }
}
