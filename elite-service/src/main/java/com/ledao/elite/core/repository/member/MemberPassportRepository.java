package com.ledao.elite.core.repository.member;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.base.Pager;

/**
 * 会员通行证接口
 * */
public interface MemberPassportRepository extends GenericDAO<MemberPassport, Long>{
	
	/**
	 * 更新会员的被查看次数，每次累加
	 * @param memberId
	 * */
	int updateMemberViewCount(Long memberId);
	
	/**
	 * 更新密码
	 * */
	int updatePassword(Long memberId,String passSalt,String password);
	
	/**
	 * 查询帐号的所有身份
	 * */
	List<MemberPassport> queryMemberIdentityList(String account);
	
	/**
	 * 组合条件查询会员列表
	 * */
	List<MemberPassport> queryMemberPassportList(String type, String status, String keyword,
			String channel, boolean isStar, Date startTime, Date endTime, Pager pager);
	
	/**
	 * 组合条件查询会员数量
	 * */
	Integer queryMemberPassportCount(String type, String status, String keyword,
			String channel, boolean isStar, Date startTime, Date endTime);
 
	/**
	 * 人才渠道方_精英管理   组合条件查询精英列表
	 */
	
	List<MemberPassport> queryMemberPassportListByPartnerElite(Long partnerId,Date startTime, Date endTime,
			String keyword, String searchType,Date beforeServenDayTime, Pager pager);
	/**
	 * 人才渠道方_精英管理   组合条件查询精英数量
	 */
	
	Integer queryMemberPassportListByPartnerEliteCount(Long partnerId,Date startTime, Date endTime,
			String keyword, String searchType,Date beforeServenDayTime);

	/**
	 * 渠道方(项目,人才) 组合条件查询
	 * @param keyword
	 * @param type
	 * @param pager
	 * @return
	 */
	List<MemberPassport> queryMemberPassportListByMemberPartner(String keyword,String type,String memberPartnerType,Date startTime,Date endTime,Pager pager,String status);
	
	/**
	 * 渠道方(项目,人才) 组合条件查询 数量
	 * @param keyword
	 * @param type
	 * @param pager
	 * @return
	 */
	Integer queryMemberPassportListByMemberPartnerCount(String keyword,String type,String memberPartnerType,Date startTime,Date endTime,String status);

	/**
	 * 人才中心 （CTO,精英）组合条件分页查询
	 * @param keyword(姓名  编号 模糊查询）
	 * @param type
	 * @param status
	 * @param pager
	 * @return
	 */
	List<MemberPassport> queryMemberPassportListByMemberElite(String keyword,Boolean ctoed,String status,Date startTime,Date endTime,Pager pager,String inviteCode,String role);
	
	/**
	 * 人才中心 （CTO,精英）组合条件分页查询 数量
	 * @param keyword(姓名  编号 模糊查询）
	 * @param type
	 * @param status
	 * @param pager
	 * @return
	 */
	Integer queryMemberPassportListCountByMemberElite(String keyword,Boolean ctoed,String status,Date startTime,Date endTime,String inviteCode,String role);
	
	/**
	 * 项目方组合条件分页查询
	 * @param keyword(姓名  编号 模糊查询）
	 * @param type
	 * @param status
	 * @param pager
	 * @return
	 */
	List<MemberPassport> queryMemberPassportListByMemberCompany(String keyword,String status,Date startTime,Date endTime,Pager pager);
	
	/**
	 * 项目方组合条件分页查询 数量
	 * @param keyword(姓名  编号 模糊查询）
	 * @param type
	 * @param status
	 * @param pager
	 * @return
	 */
	Integer queryMemberPassportListCountByMemberCompany(String keyword,String status,Date startTime,Date endTime);
	/**
	 * 组合条件分页查询根据会员当前类型类型
	 * @param keyword(用户名/姓名/手机号码 模糊查询）
	 * @param currentType
	 * @param status
	 * @param pager
	 * @return
	 */
	List<MemberPassport> queryMemberPassportListByCurrentType(String keyword,String currentType,String status,Pager pager,String orderType);
	
	/**
	 * 组合条件分页查询根据会员当前类型类型数量
	 * @param keyword(用户名/姓名/手机号码 模糊查询）
	 * @param currentType
	 * @param status
	 * @param pager
	 * @return
	 */
	Integer queryMemberPassportListCountByCurrentType(String keyword,String currentType,String status);
	
	/**
	 * 查询参与该项目的会员
	 * @param keyWord
	 * @return
	 */
	List<MemberPassport> findMemberPassportListForProject(String keyWord,Long projectId);
	
	/**
	 * 查询审核通过的精英
	 * 
	 * @param count
	 * @return List<MemberPassport>
	 * */
	List<MemberPassport> queryMemberEliteList(Integer count);
	
   /**
    * 查询ＣＴＯ
    * @param keyword(用户名，昵称，手机号码)
    * @return
    */
	List<MemberPassport> queryCTOList(String keyword);
	
	/**
	 * 精英圈查询
	 * @param keyword(姓名  编号 模糊查询）
	 * @param role
	 * @param jobAge
	 * @param duration
	 * @param industry
	 * @param pager
	 * @return
	 */
	List<MemberPassport> queryMemberEliteCircleList(String keyword, String role,
			String jobAge,String duration,String industry,Pager pager);
	
	/**
	 * 精英圈数量查询
	 * @param keyword(姓名  编号 模糊查询）
	 * @param role
	 * @param jobAge
	 * @param duration
	 * @param industry
	 * @return
	 */
	Integer queryMemberEliteCircleCount(String keyword, String role,
			String jobAge,String duration,String industry);
	
	/**
	 * 删除无效会员的相关数据
	 * */
	void deleteByMemberId(Long memberId);
}
