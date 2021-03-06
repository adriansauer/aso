package com.py.aso.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_details")
public class FiremanEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "admission")
	private Date admission;

	@Column(name = "phone", length = 20)
	private String phone;

	@Column(name = "ci", length = 30)
	private String ci;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brigade_id")
	private BrigadeEntity brigade;

	@OneToOne
	@JoinColumn(name = "image_id")
	private ImageEntity image;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "departament_id")
	private DepartamentEntity departament;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id")
	private CityEntity city;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "range_id")
	private RankEntity rank;

}
