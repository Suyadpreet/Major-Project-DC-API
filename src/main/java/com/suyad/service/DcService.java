package com.suyad.service;

import java.util.Map;

import com.suyad.binding.DcSummary;
import com.suyad.binding.Education;
import com.suyad.binding.Income;
import com.suyad.binding.Kids;
import com.suyad.binding.PlanSelection;


public interface DcService
{
public Map<Integer, String> getPlans();
	
	public boolean savePlanSelection(PlanSelection planSel);

	public boolean saveIncome(Income income);

	public boolean saveEducation(Education education);

	public boolean saveKids(Kids kids);

	public DcSummary fetchSummaryInfo(Integer caseNum);
}
