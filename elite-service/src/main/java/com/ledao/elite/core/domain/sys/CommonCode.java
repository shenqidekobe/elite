package com.ledao.elite.core.domain.sys;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 系统公用流水号生成记录对象
 * @author Administrator
 *
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class CommonCode extends IdentifiedEntity{

	private static final long serialVersionUID = 3496910356107317543L;
	
	//编号前缀定义
	public enum COMMON_PREVAL {
        P("项目编号"),
        T("任务编号"),
        X("需求编号"),
        M("会员编号"),
        MA("会员账户编号"),
        DT("入账订单编号"),
        DU("出账订单编号"),
        S("流水编号"),
        W("提现编号"),
        IV("邀请码");
        private String label;
        COMMON_PREVAL(String label){this.label = label;}
        public String getLabel(){
            return this.label;
        }
    }

	
	private int flag;//标识
	@Column(length=32)
	private String funcCode;//函数代码
	@Column(length=32)
	private String glide;//当前值
	@Column(length=16)
	private String preVal;//值的前缀
	
}
