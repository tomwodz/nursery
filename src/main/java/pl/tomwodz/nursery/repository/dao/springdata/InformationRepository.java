package pl.tomwodz.nursery.repository.dao.springdata;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.tomwodz.nursery.model.Information;
import pl.tomwodz.nursery.repository.InformationDAO;

import java.util.List;
import java.util.Optional;

public interface InformationRepository extends InformationDAO, Repository <Information, Long> {

    List<Information> findAll(Sort sort);

    Optional<Information> findById(Long id);
    Information save(Information information);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Information i SET i.content = :#{#newInformation.content}, i.author = :#{#newInformation.author}, " +
            "i.dateCreation = :#{#newInformation.dateCreation}, i.title = :#{#newInformation.title} WHERE i.id = :id")
    void updateById(Long id, Information newInformation);
}
