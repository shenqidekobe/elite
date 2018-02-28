package com.ledao.elite.rest.framework.response.project;

import java.util.List;

import lombok.Data;

@Data
public class RProjectWeeklyDetail {
	private Integer count;//周报数量
	private Integer unReadCount;//未读周报数量
	private List<RProjectWeeklyInfo> list;
}
