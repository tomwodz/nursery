package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.Child;

import java.util.List;
import java.util.Optional;

public interface IChildDAO {
    List<Child> findAll();

    Child findById(Long id);

}