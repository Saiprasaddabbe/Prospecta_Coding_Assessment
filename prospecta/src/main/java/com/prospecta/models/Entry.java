package com.prospecta.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer entryId;

	@JsonProperty("API")
	private String api;
	
	@JsonProperty("Link")
	private String link;
	
	@JsonProperty("Description")
	private String description;
	
	@JsonProperty("Auth")
	private String auth;
	
	@JsonProperty("Https")
	private boolean HTTPS;
	
	@JsonProperty("Cors")
	private String cors;
	
	@JsonProperty("Category")
	private String category;

	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public boolean isHTTPS() {
		return HTTPS;
	}
	public void setHTTPS(boolean hTTPS) {
		HTTPS = hTTPS;
	}
	public String getCors() {
		return cors;
	}
	public void setCors(String cors) {
		this.cors = cors;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Entry(String api, String description, String auth, boolean hTTPS, String cors, String link,
			String category) {
		super();
		this.api = api;
		this.description = description;
		this.auth = auth;
		HTTPS = hTTPS;
		this.cors = cors;
		this.link = link;
		this.category = category;
	}
	
	public Entry() {
	
	}
	
	
	
	
	
	
	
}
