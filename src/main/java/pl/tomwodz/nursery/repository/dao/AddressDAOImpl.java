package pl.tomwodz.nursery.repository.dao;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.tomwodz.nursery.model.Address;
import pl.tomwodz.nursery.repository.AddressDAO;
import pl.tomwodz.nursery.repository.dao.springdata.AddressRepository;

@AllArgsConstructor
@Repository
@Transactional
public class AddressDAOImpl implements AddressDAO {

    private final AddressRepository addressRepository;

    @Override
    public void updateById(Long id, Address newAddress) {
        this.addressRepository.updateById(id, newAddress);
    }
}
