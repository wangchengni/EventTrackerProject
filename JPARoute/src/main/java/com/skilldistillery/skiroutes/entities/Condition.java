package com.skilldistillery.skiroutes.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Condition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
//	@ManyToMany(mappedBy ="conditions")
//	private List<Route> routes;
	
	public Condition() {
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

//	public List<Route> getRoutes() {
//		return routes;
//	}
//
//	public void setRoutes(List<Route> routes) {
//		this.routes = routes;
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
		Condition other = (Condition) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Condition [id=" + id + ", title=" + title + "]";
	}

	
	
	
}
