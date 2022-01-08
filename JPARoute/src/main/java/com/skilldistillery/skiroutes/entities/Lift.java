package com.skilldistillery.skiroutes.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lift {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(name ="run_time")
	private String runTime;
	@Column(name ="carrier_number")
	private int carrierNumber;
	@ManyToOne
	@JoinColumn(name ="peak_id")
	private Peak peak;
	@JsonIgnore
	@OneToMany(mappedBy ="lift")
	private List<Route> routes;
	public Lift() {
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
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public int getCarrierNumber() {
		return carrierNumber;
	}
	public void setCarrierNumber(int carrierNumber) {
		this.carrierNumber = carrierNumber;
	}
	public Peak getPeak() {
		return peak;
	}
	public void setPeak(Peak peak) {
		this.peak = peak;
	}
	public List<Route> getRoutes() {
		return routes;
	}
	public void setRoutes(List<Route> routes) {
		this.routes = routes;
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
		Lift other = (Lift) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Lift [id=" + id + ", name=" + name + ", runTime=" + runTime + ", carrierNumber=" + carrierNumber
				+ ", peak=" + peak + "]";
	}
	
	

}
