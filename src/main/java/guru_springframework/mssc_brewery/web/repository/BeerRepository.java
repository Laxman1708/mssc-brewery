package guru_springframework.mssc_brewery.web.repository;

import guru_springframework.mssc_brewery.web.domain.Beer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
public interface BeerRepository extends CrudRepository<Beer, UUID> {
}
