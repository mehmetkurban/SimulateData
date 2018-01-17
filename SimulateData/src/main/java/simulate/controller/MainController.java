package simulate.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import simulate.model.Route;
import simulate.model.RouteStation;
import simulate.model.Station;
import simulate.model.Voyage;
import simulate.service.RouteService;
import simulate.service.RouteStationService;
import simulate.service.RoutesSimulator;
import simulate.service.StationService;
import simulate.service.VoyageService;

@Controller
public class MainController {
	
	@Autowired
	private RoutesSimulator routesSimulator;
	@Autowired
	private RouteStationService routeStationService;
	@Autowired
	private RouteService routeService;
	@Autowired
	private StationService stationService;
	@Autowired
	private VoyageService voyageService;

	@GetMapping("/")
	public String home(HttpServletRequest request){
		request.setAttribute("mode", "MODE_HOME");
		return "index";
	}
	
	@GetMapping("/generate-data")
	public String generateData(HttpServletRequest request){
		request.setAttribute("routes", routesSimulator.generatedRoutes());
		request.setAttribute("stations", routesSimulator.generatedStations());
		request.setAttribute("mode", "MODE_GENERATE_DATA");
		return "index";
	}
	
	@GetMapping("/simulate-data")
	public String simulateData(HttpServletRequest request){

		request.setAttribute("routeStations", routesSimulator.simulateRouteStations());
		request.setAttribute("voyages", routesSimulator.simulateVoyages());
		request.setAttribute("mode", "MODE_SIMULATE_DATA");
		return "index";
	}
	@GetMapping("/search-form")
	public String showStation(HttpServletRequest request){
		request.setAttribute("routeName","" );
		request.setAttribute("mode", "MODE_SEARCH_FORM");
		return "index";
	}
	
	@PostMapping("/search-results")
	public String searchStation( HttpServletRequest request){
		String routeName=request.getParameter("rootName");
		System.out.println("routeName : "+routeName);
		List<RouteStation> routeStations=routeStationService.findAllByRouteName(routeName);
		System.out.println("routeStations.size : "+routeStations.size());
		request.setAttribute("routeStations",routeStations);
		request.setAttribute("mode", "MODE_SEARCH_RESULTS");
		return "index";
	}
	@GetMapping("/shared-form")
	public String showShared(HttpServletRequest request){
		request.setAttribute("left","" );
		request.setAttribute("right","" );
		request.setAttribute("mode", "MODE_SHARED_FORM");
		return "index";
	}
	
	@PostMapping("/shared-results")
	public String resultShared( HttpServletRequest request){
		String left=request.getParameter("left");
		System.out.println("left : "+left);
		String right=request.getParameter("right");
		System.out.println("right : "+right);
		List<Station> stations= stationService.findAll();

		Route leftRoute=routeService.findByRouteName(left);
		Route rightRoute=routeService.findByRouteName(right);
		List<Station> sharedStations= routeStationService.findSharedStations(stations, leftRoute, rightRoute);
		request.setAttribute("sharedStations",sharedStations);
		request.setAttribute("mode", "MODE_SHARED_RESULTS");
		return "index";
	}
	@GetMapping("/time-form")
	public String formTime(HttpServletRequest request){
		request.setAttribute("time",new Date());
		request.setAttribute("mode", "MODE_TIME_FORM");
		return "index";
	}
	@PostMapping("/time-results")
	public String resultTime( HttpServletRequest request){
		String time=request.getParameter("time");
		System.out.println("Time : "+time);
		Date date=null;
		SimpleDateFormat format = new SimpleDateFormat("hh:mm");
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Voyage> voyages=null;
		if(date!=null) {
			voyages=voyageService.findAllInTime(date);
		}else {
			voyages=new ArrayList<>();
		}
		request.setAttribute("voyages",voyages);
		request.setAttribute("date",date);
		request.setAttribute("mode", "MODE_TIME_RESULTS");
		return "index";
	}
}
