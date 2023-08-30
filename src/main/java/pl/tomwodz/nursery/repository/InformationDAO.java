package pl.tomwodz.nursery.repository;

import pl.tomwodz.nursery.model.Information;

import java.util.List;

public interface InformationDAO {
    List<Information> findAll();
    Information findById(Long id);
    Information save(Information information);
    void existsById(Long id);
    void deleteById(Long id);

    void updateById(Long id, Information newInformation);

}
