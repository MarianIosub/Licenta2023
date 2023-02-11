package com.takeaseat.service;

import java.time.LocalDate;
import java.util.Map;


public interface StatisticsService {
	Map<LocalDate, Integer> generateOrdersPerDayStatisticsData();

	Map<String, Integer> generateUsersVisitedStatisticsData();

	Map<LocalDate, Double> generateIncomePerDayStatisticsData();

}
