package com.skilldistillery.skiroutes.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Peak {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String elevation;
	
	@OneToMany(mappedBy ="peak")
	private List<Route> routes;
	
	@OneToMany(mappedBy ="peak")
	private List<Lift> lifts;
	
	public Peak() {
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
	public String getElevation() {
		return elevation;
	}
	public void setElevation(String elevation) {
		this.elevation = elevation;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}
	public List<Lift> getLifts() {
		return lifts;
	}
	public void setLifts(List<Lift> lifts) {
		this.lifts = lifts;
	}
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
		Peak other = (Peak) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Peak [id=" + id + ", name=" + name + ", elevation=" + elevation + "]";
	}
	public Peak(int id, String name, String elevation) {
		super();
		this.id = id;
		this.name = name;
		this.elevation = elevation;
	}
	
	
	
}
