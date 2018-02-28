package com.ledao.elite.core.framework.listener.event;

import com.ledao.elite.core.domain.project.ProjectLog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 项目日志任务对象
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class ProjectLogTask extends BaseTask {

	private ProjectLog projectLog;
}
