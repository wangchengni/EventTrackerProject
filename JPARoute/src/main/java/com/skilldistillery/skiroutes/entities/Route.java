package com.skilldistillery.skiroutes.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double distance;
	private String level;
	@Column(name="snow_condition")
	private String snowCondition;
	
	@ManyToOne
	@JoinColumn(name ="peak_id")
	private Peak peak;
	@ManyToOne
	@JoinColumn(name ="lift_id")
	private Lift lift;
//	@ManyToMany
//	@JoinTable(
//			name = "snow_condition_route",
//			joinColumns = @JoinColumn(name = "route_id"),
//			inverseJoinColumns = @JoinColumn(name = "snow_condition_id")
//		)
//	private List<SnowCondition> snowConditions;
//	
	public Route() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Peak getPeak() {
		return peak;
	}
	public void setPeak(Peak peak) {
		this.peak = peak;
	}
	public Lift getLift() {
		return lift;
	}
	public void setLift(Lift lift) {
		this.lift = lift;
	}
	
	public String getSnowCondition() {
		return snowCondition;
	}
	public void setSnowCondition(String snowCondition) {
		this.snowCondition = snowCondition;
	}
	//---------------------------- save for later
//	public List<SnowCondition> getSnowConditions() {
//		return snowConditions;
//	}
//	public void setSnowConditions(List<SnowCondition> snowConditions) {
//		this.snowConditions = snowConditions;
//	}
//	public boolean addSnowConditions(SnowCondition snowCondition) {
//		if(snowConditions == null) {
//			snowConditions = new ArrayList<>();
//		}
//		boolean addedToList = false;
//		if(snowCondition != null) {
//			if(! snowConditions.contains(snowCondition)) {
//				addedToList = snowConditions.add(snowCondition);
//			}
//			if(! snowCondition.getRoutes().contains(this)) {
//				snowCondition.getRoutes().add(this);
//			}
//		}
//		return addedToList;
//	}
//	public boolean removeSnowCondition(SnowCondition snowCondition) {
//		boolean removed = false;
//		if(snowCondition != null && snowConditions.contains(snowCondition)) {
//			removed = snowConditions.remove(snowCondition);
//			
//		}
//		if(snowCondition.getRoutes().contains(this)) {
//			snowCondition.removeRoute(this);
//		}
//		return removed;
//	}
//	-----------------------Save for later
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Route [id=" + id + ", name=" + name + ", distance=" + distance + ", level=" + level + ", peak=" + peak
				+ ", lift=" + lift + "]";
	}
	
}
