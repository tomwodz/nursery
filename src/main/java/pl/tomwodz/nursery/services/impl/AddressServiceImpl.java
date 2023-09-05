package pl.tomwodz.nursery.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.Address;
import pl.tomwodz.nursery.repository.AddressDAO;
import pl.tomwodz.nursery.services.AddressService;
@AllArgsConstructor
@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO;

    @Override
    public void updateById(Long id, Address newAdrress) {
        this.addressDAO.updateById(id, newAdrress);
    }
}
