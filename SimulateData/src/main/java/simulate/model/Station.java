package simulate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Station {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int stationId; 
	private String stationName;
	
	@OneToMany(mappedBy = "station",cascade = CascadeType.ALL)
	private List<RouteStation> routeStationList;
	public Station() {
	}
	
	public Station(int stationId, String stationName) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
	}
	public int getStationId() {
		return stationId;
	}
	public void setStationId(int stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public List<RouteStation> getRouteStationList() {
		return routeStationList;
	}

	public void setRouteStationList(List<RouteStation> routeStationList) {
		this.routeStationList = routeStationList;
	}

	@Override
	public String toString() {
		return "Route [stationId=" + stationId + ", stationName=" + stationName + "]";
	}
}
