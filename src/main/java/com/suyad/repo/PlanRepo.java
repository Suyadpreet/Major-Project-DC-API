package com.suyad.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suyad.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {
}
