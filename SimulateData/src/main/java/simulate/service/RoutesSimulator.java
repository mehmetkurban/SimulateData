package simulate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import simulate.model.Route;
import simulate.model.RouteStation;
import simulate.model.Station;
import simulate.model.Voyage;
@Service
@Transactional
public class RoutesSimulator {
	
	private Random random=new Random();
	
	
	public final static int ROUTE_COUNT=12;
	public final static int STATION_COUNT=20;
	public final static int STATION_INTERVAL=5;
	public final static int MIN_ROUTES_STATION_COUNT=7;
	public final static int MAX_ROUTES_STATION_COUNT=20;

	public final static int MIN_SHARED_STATION_COUNT=1;
	public final static int MAX_SHARED_STATION_COUNT=3;
	
	
	private final RouteService routeService;
	private final StationService stationService;
	private final RouteStationService routeStationService;
	private final VoyageService voyageService;


	public RoutesSimulator(RouteService routeService,StationService stationService,RouteStationService routeStationService,VoyageService voyageService) {
		this.routeService = routeService;
		this.stationService = stationService;
		this.routeStationService = routeStationService;
		this.voyageService = voyageService;
	}
	
	
	public List<Route> generatedRoutes() {
	    routeService.deleteAll();
		for(int i=0;i<ROUTE_COUNT;i++) {
			Route route=new Route();
			String vehicle=i%2==0?"Oto":"Mini";
			route.setRouteName(vehicle+" "+((i+1)/2));
			routeService.save(route);
		}
		return routeService.findAll();
	}
	
	public List<Station> generatedStations() {
		stationService.deleteAll();
		for(int i=0;i<STATION_COUNT;i++) {
			Station station=new Station();
			station.setStationName("Station "+(i+1));
			stationService.save(station);
		}
		return stationService.findAll();
	}
	
	public List<RouteStation> simulateRouteStations(){
		routeStationService.deleteAll();
		List<Route> routeList=routeService.findAll();
		List<Station> stationList=stationService.findAll();
		List<RouteStation> routeStationListAll=new ArrayList<>();
		
		
		int routeIndex=0;
		int trialCount=0;
		while(routeIndex< routeList.size()&& trialCount<100000) {
			Route route=routeList.get(routeIndex);
			int routeStationCount=MIN_ROUTES_STATION_COUNT+random.nextInt(MAX_ROUTES_STATION_COUNT-MIN_ROUTES_STATION_COUNT+1);
			System.out.println(""+routeStationCount);
			route.setRouteStationList(new ArrayList<>());
			for(int i=0;i<routeStationCount;i++) {
				int stationIndex=random.nextInt(stationList.size());
				Station station=stationList.get(stationIndex);
				RouteStation routeStation=new RouteStation();
				routeStation.setRoute(route);
				routeStation.setStation(station);
				route.getRouteStationList().add(routeStation);
			}
			if(checkSharedStations(stationList, routeList, routeIndex)) {
				routeStationListAll.addAll(route.getRouteStationList());
				routeIndex++;
			}else {
				System.err.println("GEÇERSİZ ORTAK DURAK SAYISI !");
			}
			trialCount++;
		}
		System.err.println("ROUTE INDEX"+routeIndex);	
		for(RouteStation routeStation:routeStationListAll) {
			routeStationService.save(routeStation);
		}
		return routeStationListAll;
	}
	private boolean checkSharedStations(List<Station> stationList,List<Route> routeList,int routeIndexCurrent) {
		Route currentRoute=routeList.get(routeIndexCurrent);
		Route firstRoute=routeList.get(0);
		int minCount=countSharedStations(stationList,currentRoute,firstRoute);
		int maxCount=countSharedStations(stationList,currentRoute,firstRoute);
		for(int routeIndex=1;routeIndex<routeIndexCurrent;routeIndex++) {
			Route route=routeList.get(routeIndex);
			int sharedCount=countSharedStations(stationList,currentRoute,route);
			if(sharedCount<minCount) {
				minCount=sharedCount;
			}
			if(sharedCount>maxCount) {
				maxCount=sharedCount;
			}
		}
		return minCount>=MIN_SHARED_STATION_COUNT && maxCount<=MAX_SHARED_STATION_COUNT;
	}
	private int countSharedStations(List<Station> stationList,Route left,Route right) {
		int sharedCount=0;
		for(Station station:stationList) {
			if(left.containsStation(station.getStationId()) && right.containsStation(station.getStationId())) {
				sharedCount++;
			}
		}
		return sharedCount;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public List<Voyage> simulateVoyages(){
		voyageService.deleteAll();
		int departureCount=16*4; 
		int firstDeparture=8*60; 
		int timeInterval=15; 
		List<Route> routeList=routeService.findAll();
		for(int i=0;i<departureCount;i++) {
			int routeIndex=i%routeList.size();
			Route route=routeList.get(routeIndex);
			int voyageDeparture=firstDeparture+timeInterval*i;
			Voyage voyage=new Voyage();
			voyage.setRoute(route);
			
			int startHour=voyageDeparture/60;
			int startMinute=voyageDeparture%60;
			Date startTime=new Date(0,0,0,startHour,startMinute);
			voyage.setStartTime(startTime);
			
			int voyageArrival=voyageDeparture+STATION_INTERVAL*route.getRouteStationList().size();
			int endHour=voyageArrival/60;
			int endMinute=voyageArrival%60;
			Date endTime=new Date(0,0,0,endHour,endMinute);
			voyage.setEndTime(endTime);
			voyageService.save(voyage);
		}
		 List<Voyage> voyageList= voyageService.findAll();
		 return voyageList;
	 }
}