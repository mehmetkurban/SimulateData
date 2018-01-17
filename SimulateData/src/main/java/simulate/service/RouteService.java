package simulate.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simulate.dao.RouteRepository;
import simulate.model.Route;



@Service
@Transactional
public class RouteService {
	
	@Autowired
	private  RouteRepository routeRepository;

	
	
	public List<Route> findAll(){
		List<Route> routes = new ArrayList<>();
		for(Route route : routeRepository.findAll()){
			routes.add(route);
		}
		return routes;
	}
	
	public Route findRoute(int id){
		return routeRepository.findOne(id);
	}
	public Route findByRouteName(String routeName){
		return routeRepository.findByRouteName(routeName);
	}
	
	public void save(Route route){
		routeRepository.save(route);
	}
	
	public void delete(int id){
		routeRepository.delete(id);
	}
	public void deleteAll(){
		routeRepository.deleteAll();
	}
	
	
}
