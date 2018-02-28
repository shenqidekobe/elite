package com.ledao.elite.rest.framework.response.project;

import java.util.List;

import lombok.Data;

@Data
public class RProjectWeeklyInfo {
	private String title;
	private Integer monthWeekly;
	private List<RProjectWeekly> weeklyList;
}
