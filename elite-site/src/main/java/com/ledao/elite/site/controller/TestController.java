package com.ledao.elite.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class TestController {
	
	
	@ResponseBody
	@RequestMapping("a")
	public String a(){
		return "html/this is test here";
	}
	
	  //页面列表页
    @RequestMapping("/pageList")
    public String pageList() {
        return "html/test";
    }
	  //合作伙伴
    @RequestMapping("/partner")
    public String partner() {
        return "html/partner/partner";
    }

    //人才推荐方注册
    @RequestMapping("/partner/elite")
    public String elite() {
        return "html/partner/elite";
    }

    //项目推荐方注册
    @RequestMapping("/partner/project")
    public String project() {
        return "html/partner/project";
    }

    //注册
    @RequestMapping("/test/register")
    public String register() {
        return "html/register";
    }

    //邀请注册(人才方)
    @RequestMapping("/person/inviteRegister")
    public String inviteRegister() {
        return "html/person/inviteRegister";
    }

    //邀请注册模板页
    @RequestMapping("/account/personLayout")
    public String personLayout() {
        return "html/personLayout";
    }

    //精英活跃度管理(人才方)
    @RequestMapping("/person/personActive")
    public String personActive() {
        return "html/person/personActive";
    }

    //邀请注册(项目方)
    @RequestMapping("/project/inviteRegister")
    public String projectInviteRegister() {
        return "html/project/inviteRegister";
    }

    //项目管理(项目方)
    @RequestMapping("/project/projectManage")
    public String projectManage() {
        return "html/project/projectManage";
    }

    //账户安全(人才方)
    @RequestMapping("/person/accountSecurity")
    public String accountSecurity() {
        return "html/person/accountSecurity";
    }

    //收益中心(人才方)
    @RequestMapping("/person/revenue")
    public String revenue() {
        return "html/person/revenue";
    }


    //账户安全(项目方)
    @RequestMapping("/project/accountSecurity")
    public String accountSecurity2() {
        return "html/project/accountSecurity";
    }

    //收益中心(项目方)
    @RequestMapping("/project/revenue")
    public String revenue2() {
        return "html/project/revenue";
    }

    //完善信息模板
    @RequestMapping("/completeLayout")
    public String completeLayout() {
        return "html/completeLayout";
    }

    //ceo注册
    @RequestMapping("/complete/ceoRegister")
    public String ceoRegister() {
        return "html/complete/ceoRegister";
    }

    //ceo注册2
    @RequestMapping("/complete/ceoRegister2")
    public String ceoRegister2() {
        return "html/complete/ceoRegister2";
    }

    //ceo注册3
    @RequestMapping("/complete/ceoRegister3")
    public String ceoRegister3() {
        return "html/complete/ceoRegister3";
    }


    //cto注册
    @RequestMapping("/complete/ctoRegister")
    public String ctoRegister() {
        return "html/complete/ctoRegister";
    }

    //cto注册2
    @RequestMapping("/complete/ctoRegister2")
    public String ctoRegister2() {
        return "html/complete/ctoRegister2";
    }

    //cto注册
    @RequestMapping("/complete/ctoRegister3")
    public String ctoRegister3() {
        return "html/complete/ctoRegister3";
    }

    //cto注册
    @RequestMapping("/complete/ctoRegister4")
    public String ctoRegister4() {
        return "html/complete/ctoRegister4";
    }

    //精英注册
    @RequestMapping("/complete/eliteRegister")
    public String eliteRegister() {
        return "html/complete/eliteRegister";
    }

    //精英注册2
    @RequestMapping("/complete/eliteRegister2")
    public String eliteRegister2() {
        return "html/complete/eliteRegister2";
    }

    //精英注册3
    @RequestMapping("/complete/eliteRegister3")
    public String eliteRegister3() {
        return "html/complete/eliteRegister3";
    }
    //精英注册4
    @RequestMapping("/complete/eliteRegister4")
    public String eliteRegister4() {
        return "html/complete/eliteRegister4";
    }

    //发布项目第一步
    @RequestMapping("/release/releaseProject")
    public String releaseProject() {
        return "html/release/releaseProject";
    }

    //个人主页模板
    @RequestMapping("/homepageLayout")
    public String homepageLayout() {
        return "html/homepageLayout";
    }

    //CEO个人主页
    @RequestMapping("/homepage/ceoHomepage")
    public String ceoHomepage() {
        return "html/homepage/ceoHomepage";
    }

    //ceo-我的资料
    @RequestMapping("/homepage/ceoInfo")
    public String ceoInfo() {
        return "html/homepage/ceoInfo";
    }

    //CTO个人主页
    @RequestMapping("/homepage/ctoHomepage")
    public String ctoHomepage() {
        return "html/homepage/ctoHomepage";
    }

    //精英个人主页
    @RequestMapping("/homepage/eliteHomepage")
    public String eliteHomepage() {
        return "html/homepage/eliteHomepage";
    }

    //CEO系统消息
    @RequestMapping("/homepage/ceoMessage")
    public String ceoMessage() {
        return "html/homepage/ceoMessage";
    }


    //CEO项目详情
    @RequestMapping("/projectDetail/ceoProjectDetail")
    public String ceoProjectDetail() {
        return "html/projectDetail/ceoProjectDetail";
    }

    //CEO项目日志
    @RequestMapping("/homeCommon/projectLog")
    public String ceoProjectLog() {
        return "html/homeCommon/projectLog";
    }

    //CEO需求更改列表
    @RequestMapping("/projectDetail/ceoDemandChangeList")
    public String ceoDemandChangeList() {
        return "html/projectDetail/ceoDemandChangeList";
    }

    //CEO项目周报
    @RequestMapping("/projectDetail/ceoWeekReport")
    public String ceoWeekReport() {
        return "html/projectDetail/ceoWeekReport";
    }

    //CEO文件管理
    @RequestMapping("/projectDetail/ceoSuppliers")
    public String ceoSupplies() {
        return "html/projectDetail/ceoSuppliers";
    }

    //CEO文件管理
    @RequestMapping("/test")
    public String test() {
        return "html/test/test";
    }

    //CTO任务
    @RequestMapping("/homepage/ctoTask")
    public String ctoTask() {
        return "html/homepage/ctoTask";
    }

    //CTO团队
    @RequestMapping("/homepage/ctoTeam")
    public String ctoTeam() {
        return "html/homepage/ctoTeam";
    }

    //CTO关注
    @RequestMapping("/homepage/ctoNotice")
    public String ctoNotice() {
        return "html/homepage/ctoNotice";
    }

    //CEO提交意向金
    @RequestMapping("/homeOpt/ceoDeposit")
    public String ceoDeposit() {
        return "html/homeOpt/ceoDeposit";
    }

    //CEO确认立项书
    @RequestMapping("/homeOpt/ceoSetUp")
    public String ceoSetUp() {
        return "html/homeOpt/ceoSetUp";
    }

    //CEO托管股权
    @RequestMapping("/homeOpt/ceoStockRight")
    public String ceoStockRight() {
        return "html/homeOpt/ceoStockRight";
    }

    //CEO托管费用
    @RequestMapping("/homeOpt/ceoCost")
    public String ceoCost() {
        return "html/homeOpt/ceoCost";
    }

    //CEO需求更改
    @RequestMapping("/homeOpt/ceoDemandChange")
    public String ceoDemandChange() {
        return "html/homeOpt/ceoDemandChange";
    }

    //CEO需求梳理评价
    @RequestMapping("/homeCommon/ceoEvaluate")
    public String ceoEvaluate() {
        return "html/homeCommon/evaluate";
    }

    //CTO项目详情
    @RequestMapping("/projectDetail/ctoProjectDetail")
    public String ctoProjectDetail() {
        return "html/projectDetail/ctoProjectDetail";
    }

    //CTO看招标书
    @RequestMapping("/homeOpt/ctoViewTender")
    public String ctoViewTender() {
        return "html/homeOpt/ctoViewTender";
    }

    //CTO填标书
    @RequestMapping("/homeOpt/ctoBid")
    public String ctoBid() {
        return "html/homeOpt/ctoBid";
    }

    //CTO确认立项书
    @RequestMapping("/homeOpt/ctoSetUp")
    public String ctoSetUp() {
        return "html/homeOpt/ctoSetUp";
    }

    //CTO评价
    @RequestMapping("/homeCommon/ctoEvaluate")
    public String ctoEvaluate() {
        return "html/homeCommon/evaluate";
    }

    //CTO任务分配
    @RequestMapping("/homepage/ctoTaskDispatcher")
    public String ctoTaskDispatcher() {
        return "html/homepage/ctoTaskDispatcher";
    }

    //CTO需求修改
    @RequestMapping("/projectDetail/ctoDemandChangeList")
    public String ctoDemandChangeList() {
        return "html/projectDetail/ctoDemandChangeList";
    }

    //CTO项目周报
    @RequestMapping("/projectDetail/ctoWeekReport")
    public String ctoWeekReport() {
        return "html/projectDetail/ctoWeekReport";
    }

    //CTO文件管理
    @RequestMapping("/projectDetail/ctoSuppliers")
    public String ctoSuppliers() {
        return "html/projectDetail/ctoSuppliers";
    }

    //精英项目详情
    @RequestMapping("/projectDetail/eliteProjectDetail")
    public String eliteProjectDetail() {
        return "html/projectDetail/eliteProjectDetail";
    }
    
    @RequestMapping("/bcm")
    public String bcm() {
        return "html/bcm/bcm";
    }
}
