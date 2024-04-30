package com.suyad.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.suyad.entity.AppEntity;

public interface AppRepo extends JpaRepository<AppEntity, Integer> {

	public List<AppEntity> fetchUserApps();

	@Query(value = "from AppEntity where userId =:userId")
	public List<AppEntity> fetchCwApps(Integer userId);

}