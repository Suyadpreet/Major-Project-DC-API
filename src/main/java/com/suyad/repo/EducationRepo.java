package com.suyad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suyad.entity.EducationEntity;
import com.suyad.entity.IncomeEntity;

public interface EducationRepo extends JpaRepository<EducationEntity, Integer>
{
	public EducationEntity findByCaseNum(Integer caseNum);
}