package com.ledao.elite.core.framework.listener.event;

import com.ledao.elite.core.domain.project.ProjectMaterial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 记录项目文件任务
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
@AllArgsConstructor
@NoArgsConstructor
public class MaterialTask extends BaseTask{

	private ProjectMaterial material;
}
