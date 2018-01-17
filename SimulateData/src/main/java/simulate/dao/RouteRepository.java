package simulate.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import simulate.model.Route;


public interface RouteRepository extends CrudRepository<Route, Integer> {
	@Query(value="select route from Route as route where route.routeName = :routeName")
	Route findByRouteName(@Param("routeName") String routeName);
}
