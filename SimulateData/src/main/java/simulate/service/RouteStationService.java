package simulate.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import simulate.dao.RouteStationRepository;
import simulate.model.Route;
import simulate.model.RouteStation;
import simulate.model.Station;


@Service
@Transactional
public class RouteStationService {
	private final RouteStationRepository routeStationRepository;

	public RouteStationService(RouteStationRepository routeStationRepository) {
		this.routeStationRepository = routeStationRepository;
	}
	
	public List<RouteStation> findAll(){
		List<RouteStation> routeStations = new ArrayList<>();
		for(RouteStation routeStation : routeStationRepository.findAll()){
			routeStations.add(routeStation);
		}
		return routeStations;
	}
	public List<RouteStation> findAllByRouteName(String routeName){
		List<RouteStation> routeStations = new ArrayList<>();
		for(RouteStation routeStation : routeStationRepository.findAllByRouteName(routeName)){
			routeStations.add(routeStation);
		}
		return routeStations;
	}
	public RouteStation findRoute(int id){
		return routeStationRepository.findOne(id);
	}
	
	public void save(RouteStation routeStation){
		routeStationRepository.save(routeStation);
	}
	
	public void delete(int id){
		routeStationRepository.delete(id);
	}
	public void deleteAll(){
		routeStationRepository.deleteAll();
	}
	
	public List<Station> findSharedStations(List<Station> stationList,Route left,Route right) {
		List<Station> sharedStations=new ArrayList<>();
	
		for(Station station:stationList) {
			if(left.containsStation(station.getStationId()) && right.containsStation(station.getStationId())) {
				sharedStations.add(station);			}
		}
		return sharedStations;
	}
	
}
