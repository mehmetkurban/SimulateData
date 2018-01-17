package simulate.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import simulate.dao.StationRepository;
import simulate.model.Station;

@Service
@Transactional
public class StationService {
	private final StationRepository stationRepository;

	public StationService(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}
	
	public List<Station> findAll(){
		List<Station> stations = new ArrayList<>();
		for(Station station : stationRepository.findAll()){
			stations.add(station);
		}
		return stations;
	}
	
	public Station findStation(int id){
		return stationRepository.findOne(id);
	}
	
	public void save(Station station){
		stationRepository.save(station);
	}
	
	public void delete(int id){
		stationRepository.delete(id);
	}
	public void deleteAll(){
		stationRepository.deleteAll();
	}
	

}
