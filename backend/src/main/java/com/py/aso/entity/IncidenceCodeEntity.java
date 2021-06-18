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
@Table(name = "incidence_codes")
public class IncidenceCodeEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "description")
	private String description;

	@Column(name = "deleted")
	private boolean deleted;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "incidence")
	private Set<PublicationEntity> publications;

}
