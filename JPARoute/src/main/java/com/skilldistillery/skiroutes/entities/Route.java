package com.skilldistillery.skiroutes.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String distance;
	private String level;
	
	@ManyToOne
	@JoinColumn(name ="peak_id")
	private Peak peak;
	@ManyToOne
	@JoinColumn(name ="lift_id")
	private Lift lift;
//	@ManyToMany
//	@JoinTable(
//			name = "route_condition",
//			joinColumns = @JoinColumn(name = "route_id"),
//			inverseJoinColumns = @JoinColumn(name = "condition_id")
//		)
//	private List<Condition> conditions;
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
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
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
//	public List<Condition> getConditions() {
//		return conditions;
//	}
//	public void setConditions(List<Condition> conditions) {
//		this.conditions = conditions;
//	}
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
