package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "video")
public class Video {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "titile")
	private String titile;
	
	@Column(name = "poster")
	private String poster;
	
	@Column(name = "description")
	private String 	description;
	
	@Column(name = "views")
	private Integer views;
	
	@Column(name = "active")
	private Integer active;
	
//	@OneToMany(
//			mappedBy = "vid",//mappeBy user phải trùng với ManyToOne bên post
//			fetch = FetchType.LAZY
//		)
//	private Integer idFr;
//	
//	public Integer getIdFr() {
//		return idFr;
//	}
//
//	public void setIdFr(Integer idFr) {
//		this.idFr = idFr;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitile() {
		return titile;
	}

	public void setTitile(String titile) {
		this.titile = titile;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}
}
