package simulate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import simulate.service.RoutesSimulator;

@Entity
public class Voyage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int voyageId;
	@ManyToOne @JoinColumn(name ="routeId")
	private Route route;
	@Temporal(TemporalType.TIME)
	private Date startTime;
	@Temporal(TemporalType.TIME)
	private Date endTime;
	
	public Voyage() {
	}
	public Voyage(int voyageId, Route route, Date startTime, Date endTime) {
		super();
		this.voyageId = voyageId;
		this.route = route;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getVoyageId() {
		return voyageId;
	}
	public void setVoyageId(int voyageId) {
		this.voyageId = voyageId;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@SuppressWarnings("deprecation")
	public Station findStationInTime(Date time) {
		int timeMinutes=time.getHours()*60+time.getMinutes();
		int startMinutes=time.getHours()*60+time.getMinutes();
		int stationIndex=(timeMinutes-startMinutes)/RoutesSimulator.STATION_INTERVAL;
		return route.getRouteStationList().get(stationIndex).getStation();
	}
	@Override
	public String toString() {
		return "Voyage [voyageId=" + voyageId + ", route=" + route + ", startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}
	
	
}
