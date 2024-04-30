package com.suyad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suyad.entity.IncomeEntity;

public interface IncomeRepo extends JpaRepository<IncomeEntity, Integer>
{
	public IncomeEntity findByCaseNum(Integer caseNum);
}
