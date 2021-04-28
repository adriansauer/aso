package com.py.aso.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "departaments")
public class DepartamentEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "deleted")
	private boolean deleted;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departament")
	private Set<BrigadeEntity> brigades;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departament")
	private Set<FiremanEntity> firemen;

}
