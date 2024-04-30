package com.suyad.entity;

import java.time.LocalDate;
import java.util.List;

import com.suyad.binding.Kid;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class KidEntity 
{
	private String kidName;

	private LocalDate kidDob;

	private Long kidSsn;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer kidId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@OneToOne
	@JoinColumn(name = "case_num")
	private AppEntity app;
}
