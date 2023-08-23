package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.repository.AddressDAO;
import pl.tomwodz.nursery.services.AddressService;
@AllArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressDAO addressDAO;

}
