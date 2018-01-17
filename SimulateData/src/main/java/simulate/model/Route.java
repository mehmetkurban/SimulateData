package simulate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Route {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int routeId; 
	private String routeName; 
	private int stationCount;
	
	@OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
	private List<Voyage> voyageList;
	@OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
	private List<RouteStation> routeStationList;
	
	public Route() {
	}
	public Route(int routeId, String routeName, int stationCount) {
		super();
		this.routeId = routeId;
		this.routeName = routeName;
		this.stationCount = stationCount;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public int getStationCount() {
		return stationCount;
	}
	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}
	
	public List<Voyage> getVoyageList() {
		return voyageList;
	}
	public void setVoyageList(List<Voyage> voyageList) {
		this.voyageList = voyageList;
	}
	public List<RouteStation> getRouteStationList() {
		return routeStationList;
	}
	public void setRouteStationList(List<RouteStation> routeStationList) {
		this.routeStationList = routeStationList;
	}
	public boolean containsStation(int stationId) {
		for(RouteStation routeStation: routeStationList) {
			if(routeStation.getStation().getStationId()==stationId) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "RouteStation [routeId=" + routeId + ", routeName=" + routeName + ", stationCount=" + stationCount + "]";
	}
	
}
