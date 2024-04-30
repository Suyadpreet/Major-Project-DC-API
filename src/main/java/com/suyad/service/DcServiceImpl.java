package com.suyad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suyad.binding.*;
import com.suyad.entity.AppEntity;
import com.suyad.entity.EducationEntity;
import com.suyad.entity.IncomeEntity;
import com.suyad.entity.KidEntity;
import com.suyad.entity.PlanEntity;
import com.suyad.entity.UserEntity;
import com.suyad.repo.AppRepo;
import com.suyad.repo.EducationRepo;
import com.suyad.repo.IncomeRepo;
import com.suyad.repo.KidRepo;
import com.suyad.repo.PlanRepo;
import com.suyad.repo.UserRepo;



@Service
public class DcServiceImpl implements DcService
{
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private AppRepo  appRepo;
	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private EducationRepo eduRepo;
	
	@Autowired
	private KidRepo kidRepo;
	
	@Override
	public Map<Integer, String> getPlans() 
	{
		List<PlanEntity> plans = planRepo.findAll();
		Map<Integer,String> plansMap = new HashMap<>();
		for(PlanEntity  entity: plans)
		{
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		return plansMap;
	}

	@Override
	public boolean savePlanSelection(PlanSelection planSel) 
	{
		Integer caseNum = planSel.getCaseNum();
		Optional<AppEntity> findById = appRepo.findById(caseNum);
		UserEntity userEntity = userRepo.findById(planSel.getUserId()).get();
		if(findById.isPresent())
		{
			AppEntity appEntity = findById.get();
			appEntity.setPlanId(planSel.getPlanId());
			appEntity.setUser(userEntity);
			appRepo.save(appEntity);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean saveIncome(Income income) 
	{
		IncomeEntity entity = new IncomeEntity();
		BeanUtils.copyProperties(income, entity);
		Integer caseNum = income.getCaseNum();
		Integer userId = income.getUserId();
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserEntity userEntity = userRepo.findById(userId).get();
		
		entity.setApp(appEntity);
		entity.setUser(userEntity);
		
		incomeRepo.save(entity);
		return true;
	}

	@Override
	public boolean saveEducation(Education edu) 
	{
		Integer caseNum = edu.getCaseNum();
		Integer userId = edu.getUserId();
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserEntity userEntity = userRepo.findById(userId).get();
		
		EducationEntity entity = new EducationEntity();
		BeanUtils.copyProperties(edu, entity);
		
		entity.setUser(userEntity);
		entity.setApp(appEntity);
		eduRepo.save(entity);
		return true;
	}

	@Override
	public boolean saveKids(Kids kids) 
	{
		Integer caseNum = kids.getCaseNum();
		Integer userId = kids.getUserId();
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserEntity userEntity = userRepo.findById(userId).get();
		
		List<Kid> kidsList = kids.getKidsList();
		for(Kid kid :kidsList)
		{
			KidEntity entity = new KidEntity();
			BeanUtils.copyProperties(kids, entity);
			entity.setApp(appEntity);
			entity.setUser(userEntity);
			kidRepo.save(entity);
		}
		return false;
	}

	@Override
	public DcSummary fetchSummaryInfo(Integer caseNum) 
	{
		DcSummary summary = new DcSummary();
		AppEntity appEntity = appRepo.findById(caseNum).get();
		PlanEntity planEntity = planRepo.findById(appEntity.getPlanId()).get();
		IncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
		EducationEntity educationEntity = eduRepo.findByCaseNum(caseNum);
		List<KidEntity> kidEntities = kidRepo.findByCaseNum(caseNum);
		summary.setCaseNum(caseNum);
		summary.setPlanName(planEntity.getPlanName());
		Income income  = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);
		Education education  = new Education();
		BeanUtils.copyProperties(educationEntity, education);
		summary.setEducation(education);
		List<Kid> list = new ArrayList();
		for(KidEntity entity : kidEntities)
		{
			Kid kid = new Kid();
			BeanUtils.copyProperties(entity, kid);
			list.add(kid);
		}
		Kids kids = new Kids();
		kids.setKidsList(list);
		summary.setKids(kids);
		return summary;
	}
}
