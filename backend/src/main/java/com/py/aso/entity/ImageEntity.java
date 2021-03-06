package com.py.aso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class ImageEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "path")
	private String path;
	
	@Column(name = "file")
	private String file;

	@OneToOne(mappedBy = "image")
	private RankEntity rank;

	@OneToOne(mappedBy = "image")
	private BrigadeEntity brigade;

	@OneToOne(mappedBy = "image")
	private FiremanEntity fireman;
}
