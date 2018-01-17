package simulate.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import simulate.model.Voyage;

public interface VoyageRepository extends CrudRepository<Voyage, Integer>{
	@Query(value="select voyage from Voyage as voyage where voyage.startTime < :time and :time < voyage.endTime")
	List<Voyage> findAllInTime(@Param("time") Date time);
}
