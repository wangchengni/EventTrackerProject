package com.skilldistillery.skiroutes.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="snow_condition")
public class SnowCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
//	@ManyToMany(mappedBy ="snowConditions")
//	private List<Route> routes;
	
	public SnowCondition() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
//--------------------Save for Later
//	public List<Route> getRoutes() {
//		return routes;
//	}
//
//	public void setRoutes(List<Route> routes) {
//		this.routes = routes;
//	}
//	public boolean addRoute(Route route) {
//		if(routes == null) {
//			routes = new ArrayList<>();
//		}
//		boolean addedToList = false;
//		if(route != null) {
//			if(! routes.contains(route)) {
//				addedToList = routes.add(route);
//			}
//			if(! route.getSnowConditions().contains(this)) {
//				route.getSnowConditions().add(this);
//			}
//		}
//		return addedToList;
//	}
//	public boolean removeRoute(Route route) {
//		boolean removed = false;
//		if(route != null && routes.contains(route)) {
//			removed = routes.remove(route);
//			
//		}
//		if(route.getSnowConditions().contains(this)) {
//			route.removeSnowCondition(this);
//		}
//		return removed;
//	}
//----------------------------save for later
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
		SnowCondition other = (SnowCondition) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Condition [id=" + id + ", title=" + title + "]";
	}

	
	
	
}
