package com.suyad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suyad.entity.IncomeEntity;
import com.suyad.entity.KidEntity;

public interface KidRepo extends JpaRepository<KidEntity, Integer>
{
	public List<KidEntity> findByCaseNum(Integer caseNum);
}
