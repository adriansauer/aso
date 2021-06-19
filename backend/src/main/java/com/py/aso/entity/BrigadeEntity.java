package com.py.aso.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "brigade_details")
public class BrigadeEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "creation")
	private Date creation;

	@Column(name = "number_members")
	private int numberMember;

	@Column(name = "phone", length = 20)
	private String phone;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@OneToOne
	@JoinColumn(name = "image_id")
	private ImageEntity image;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departament_id")
	private DepartamentEntity departament;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private CityEntity city;
	
	@OneToOne(mappedBy = "brigade")
	private BrigadeHistoryEntity history;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brigade")
	private Set<FiremanEntity> firemen;

}
