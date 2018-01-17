package simulate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import simulate.dao.VoyageRepository;
import simulate.model.Voyage;

@Service
@Transactional
public class VoyageService {
	private final VoyageRepository voyageRepository;

	public VoyageService(VoyageRepository voyageRepository) {
		this.voyageRepository = voyageRepository;
	}
	
	public List<Voyage> findAll(){
		List<Voyage> voyages = new ArrayList<>();
		for(Voyage voyage : voyageRepository.findAll()){
			voyages.add(voyage);
		}
		return voyages;
	}

	public List<Voyage> findAllInTime(Date time){
		List<Voyage> voyages = new ArrayList<>();
		for(Voyage voyage : voyageRepository.findAllInTime(time)){
			voyages.add(voyage);
		}
		return voyages;
	}
	
	public Voyage findVoyage(int id){
		return voyageRepository.findOne(id);
	}
	
	public void save(Voyage voyage){
		voyageRepository.save(voyage);
	}
	
	public void delete(int id){
		voyageRepository.delete(id);
	}
	public void deleteAll(){
		voyageRepository.deleteAll();
	}
}
