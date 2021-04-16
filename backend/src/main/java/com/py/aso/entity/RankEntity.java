package com.py.aso.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "ranges")
public class RankEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "deleted")
	private boolean deleted;

	@OneToOne
	@JoinColumn(name = "image_id")
	private ImageEntity image;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rank")
	private Set<FiremanEntity> firemen;

}
