package simulate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RouteStation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int routeStationId;
	@ManyToOne @JoinColumn(name ="routeId")
	private Route route;
	@ManyToOne @JoinColumn(name ="stationId")
	private Station station;
		
	public RouteStation() {
	}
	public RouteStation(int routeStationId, Route route, Station station) {
		super();
		this.routeStationId = routeStationId;
		this.route = route;
		this.station = station;
	}
	public int getRouteStationId() {
		return routeStationId;
	}
	public void setRouteStationId(int routeStationId) {
		this.routeStationId = routeStationId;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	@Override
	public String toString() {
		return "RouteStation [routeStationId=" + routeStationId + ", route=" + route + ", station=" + station + "]";
	}
	
	
}
