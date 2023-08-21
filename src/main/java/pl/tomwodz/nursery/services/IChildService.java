package pl.tomwodz.nursery.services;

import pl.tomwodz.nursery.model.Child;

import java.util.List;
import java.util.Optional;

public interface IChildService {
    List<Child> findAll();

    Child findById(Long id);

}