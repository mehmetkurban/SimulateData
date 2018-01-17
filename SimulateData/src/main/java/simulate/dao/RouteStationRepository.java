package simulate.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import simulate.model.RouteStation;

public interface RouteStationRepository extends CrudRepository<RouteStation, Integer>{
	@Query(value="select routeStation from RouteStation as routeStation where routeStation.route.routeName = :routeName")
	List<RouteStation> findAllByRouteName(@Param("routeName") String routeName);
}
