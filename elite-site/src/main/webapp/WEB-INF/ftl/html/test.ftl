<@extend name="layout">
    <@block name="cs">
        <style type="text/css">

            /*正文样式*/
            .content{
                width:1000px;
                margin:0 auto;
            }

            .content a{
               color:#000;
            }

            .content a:hover{
                color:#2CB7C9;
            }

            .title{
                text-align: center;
            }

            .page-table th,td{
                border:1px solid #2CB7C9;
            }

            .page-table thead tr th{
                border:1px solid #2CB7C9!important;
                text-align:center;
            }


        </style>
    </@block>
    <@block name="body">
        <#--头部-->
        <@mainhead clazz="index" picpath="" pagetype="" user=""/>

        <#--正文开始-->
        <div class="content">
            <div class="title">
                <h3>云英汇前端静态HTML一览表(进行中)</h3>
            </div>

            <table class="page-table table table-hover">
                <thead>
                    <tr>
                        <th>首页</th>
                        <th>注册/登录</th>
                        <th>发布项目</th>
                        <th>认领任务</th>
                        <th>精英入驻</th>
                        <th>合作伙伴</th>
                        <th>个人中心</th>
                        <th>其它</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <a href="/" target="_blank">首页</a>
                        </td>
                        <td>
                            <a href="/login" target="_blank">登录</a>
                        </td>
                        <td>
                            <a href="/release/releaseProject" target="_blank">发布项目</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/partner" target="_blank">栏目首页</a>
                        </td>
                        <td>
                            <a href="/person/inviteRegister" target="_blank">邀请注册(人才方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ceoDeposit" target="_blank">CEO提交意向金</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/member/register" target="_blank">注册</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/partner/elite" target="_blank">人才推荐方注册</a>
                        </td>
                        <td>
                            <a href="/person/personActive" target="_blank">精英管理(人才方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ceoSetUp" target="_blank">CEO确认立项书</a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ceoRegister" target="_blank">CEO注册-个人信息</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/partner/project" target="_blank">项目推荐方注册</a>
                        </td>
                        <td>
                            <a href="/project/inviteRegister" target="_blank">邀请注册(项目方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ceoStockRight" target="_blank">CEO托管股权</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ceoRegister2" target="_blank">CEO注册-创业信息</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/project/projectManage" target="_blank">项目管理(项目方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ceoCost" target="_blank">CEO托管费用</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ceoRegister3" target="_blank">CEO注册-征信信息</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/person/accountSecurity" target="_blank">账户安全(人才方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ceoDemandChange" target="_blank">CEO需求更改</a>
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ctoRegister" target="_blank">CTO注册-当前状态</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/person/revenue" target="_blank">收益中心(人才方)</a>
                        </td>
                        <td>
                            <a href="/homeCommon/ceoEvaluate" target="_blank">CEO需求梳理评价</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ctoRegister2" target="_blank">CTO注册-基本信息</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/project/accountSecurity" target="_blank">账户安全(项目方)</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ctoRegister3" target="_blank">CTO注册-项目经历</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/project/revenue" target="_blank">收益中心(项目方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ctoViewTender" target="_blank">CTO看招标书</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/ctoRegister4" target="_blank">CTO注册-个人征信</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/project/revenue" target="_blank">收益中心(项目方)</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ctoBid" target="_blank">CTO填标书</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/eliteRegister" target="_blank">精英注册-当前状态</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ceoHomepage" target="_blank">CEO个人主页</a>
                        </td>
                        <td>
                            <a href="/homeOpt/ctoSetUp" target="_blank">CTO确认立项书</a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/eliteRegister2" target="_blank">精英注册-基本信息</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ceoInfo" target="_blank">CEO我的资料</a>
                        </td>
                        <td>
                            <a href="/homeCommon/ctoEvaluate" target="_blank">CTO评价</a>
                        </td>
                    </tr>


                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/eliteRegister3" target="_blank">精英注册-项目经历</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ctoHomepage" target="_blank">CTO个人主页</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/complete/eliteRegister4" target="_blank">精英注册-个人征信</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/eliteHomepage" target="_blank">精英个人主页</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ceoMessage" target="_blank">CEO系统消息</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ceoProjectDetail" target="_blank">CEO项目详情</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homeCommon/projectLog" target="_blank">CEO项目日志</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ceoDemandChangeList" target="_blank">CEO需求更改列表</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ceoWeekReport" target="_blank">CEO项目周报</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ceoSuppliers" target="_blank">CEO文件管理</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ctoTask" target="_blank">CTO任务</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ctoTeam" target="_blank">CTO团队</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ctoNotice" target="_blank">CTO关注</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/homepage/ctoTaskDispatcher" target="_blank">CTO任务分配</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ctoDemandChangeList" target="_blank">CTO需求修改</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ctoWeekReport" target="_blank">CTO项目周报</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ctoSuppliers" target="_blank">CTO文件管理</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/ctoProjectDetail" target="_blank">CTO项目详情</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                        <td>
                            <a href="/projectDetail/eliteProjectDetail" target="_blank">精英项目详情</a>
                        </td>
                        <td>
                            <a href="#" target="_blank"></a>
                        </td>
                    </tr>
                </tbody>
            </table>

            <div>
                <p>前端编码规范:</p>
                <p>1.前端为适应敏捷开发和工程化思想,引入bootstrap框架辅助布局,使用了强大的freemarker模板引擎构建组件,
                    很好的体现了灵活,封装和继承的特性.bootstrap是比较流行的前端框架</p>
                <p>2.样式文件采用scss管理,gulp编译,js文件采用requireJS管理和引用,体现了工程化的思想.都是流行的管理插件</p>
              <p>3.编码:class选择器按要求采用中划线命名,如 login-form,id选择器可以用中划线命名,也可以使用驼峰式命名,
                  参见bootstrap中文网更详细的命名规范.
                  图片要求使用下划线命名,如banner_login,location_icon等,所有页面相关文件名要求采用驼峰式命名.
                  样式尽量使用class选择器,谨慎使用id选择器,少用全局变量.</p>
                <p>4.页面布局要求简化,清晰易懂,样式尽量写在样式文件中;js不引无关的插件,提升效率.各模块之间的耦合性要做到最弱,提升安全性.</p>
                <p>5.命名要求英文单词或词组,表意,严禁使用汉语拼音或无意义的字符替代.文件夹包含关系清晰不乱放,非公共文件不得暴露在公共部分</p>
                <p>6.网站整体风格保持统一,尽量重用样式,拒绝过多冗余代码.尽量兼容到Win7最低IE版本IE8.</p>
                <p>7.营造良好编码环境,预防后期技术债务,保持规范,从我做起</p>
            </div>
        </div>

        <@optmodal title="删除提示" ask="您确定删除?"/>
        <#--<@tipmodal tip="恭喜您操作成功"/>-->

    </@block>
<@block name="script">
    
</@block>
</@extend>