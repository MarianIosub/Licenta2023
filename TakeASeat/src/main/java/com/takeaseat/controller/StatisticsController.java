package com.takeaseat.controller;

import com.takeaseat.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

import static com.takeaseat.constants.EndpointsConstants.INCOME_PER_DAY_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.ORDERS_PER_DAY_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.STATISTICS_ENDPOINT;
import static com.takeaseat.constants.EndpointsConstants.USERS_VISITED;

@AllArgsConstructor
@RestController
@RequestMapping(STATISTICS_ENDPOINT)
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping(ORDERS_PER_DAY_ENDPOINT)
    public Map<LocalDate, Integer> generateOrderPerDayStatistics(){
        return getStatisticsService().generateOrdersPerDayStatisticsData();
    }

    @GetMapping(USERS_VISITED)
    public Map<String, Integer> generateUsersVisitedStatistics(){
        return getStatisticsService().generateUsersVisitedStatisticsData();
    }

    @GetMapping(INCOME_PER_DAY_ENDPOINT)
    public Map<LocalDate, Double> generateIncomePerDayStatistics(){
        return getStatisticsService().generateIncomePerDayStatisticsData();
    }

    protected StatisticsService getStatisticsService() {
        return statisticsService;
    }
}
