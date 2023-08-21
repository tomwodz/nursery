package pl.tomwodz.nursery.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tomwodz.nursery.model.Child;
import pl.tomwodz.nursery.repository.IChildDAO;
import pl.tomwodz.nursery.services.IChildService;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ChildServiceImpl implements IChildService {

    private final IChildDAO childDAO;
    @Override
    public List<Child> findAll() {
        return this.childDAO.findAll();
    }

    @Override
    public Child findById(Long id) {
        return this.childDAO.findById(id);
    }
}
