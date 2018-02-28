package com.ledao.elite.core.service.member;

import java.util.Date;
import java.util.List;

import com.googlecode.genericdao.search.SearchResult;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.base.Pager;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;

/**
 * 会员身份服务接口
 */
public interface MemberPassportService {

	/**
	 * 会员注册
	 * 
	 * @param obj
	 * @return memberPassport
	 */
	MemberPassport createMemberPassport(MemberPassport obj) throws EliteServiceException;
	
	/**
	 * 切换会员身份
	 * */
	MemberPassport updateMemberIdentity(Long id,String identityType)throws EliteServiceException;

	/**
	 * 更新会员信息
	 * 
	 * @param obj
	 * @return memberPassport
	 */
	MemberPassport updateMemberPassport(MemberPassport obj) throws EliteServiceException;
	
	/**
	 * sql更新密码
	 * */
	void updatePassword(Long memberId,String passSalt,String password)throws EliteServiceException;

	/**
	 * 更新会员最后登录时间和登录次序
	 * 
	 * @param memberId
	 * @param lastLoginIp
	 */
	void updateMemberLastLogin(Long memberId,String account,String lastLoginIp) throws EliteServiceException;

	/**
	 * 更新会员的当前身份类型
	 * 
	 * @param memberId
	 * @param currentId
	 */
	void updateMemberCurrentType(Long memberId, String currentType) throws EliteServiceException;

	/**
	 * 关停会员
	 * 
	 * @param id
	 * @param status
	 * @param closeTime
	 * @param closeReason
	 * @param userId
	 * @throws EliteServiceException
	 */
	void updateCloseMemberPassport(Long id, String status, double closeTime, String controlType, String closeReason,
			Long userId) throws EliteServiceException;

	/**
	 * 更新会员登录密码
	 * 
	 * @param memberId
	 * @param oldPass
	 * @param newPass
	 */
	void updateMemberPassword(Long memberId, String oldPass, String newPass) throws EliteServiceException;
	
	/**
	 * 更换手机号
	 * 
	 * @param memberId
	 * @param phone
	 * */
	void updateMemberPhone(Long memberId,String phone)throws EliteServiceException;
	
	/**
	 * 更新会员的被查看次数{累加}
	 * 
	 * @param memberId
	 * */
	void updateMemberViewCount(Long memberId)throws EliteServiceException;

	/**
	 * 根据会员ID查询，且验证其密码是否正确
	 * 
	 * @param memberId
	 * @param password
	 * @return memberPassport
	 */
	MemberPassport findMemberPassportByIdValidPassword(Long memberId, String password) throws EliteServiceException;

	/**
	 * 检查会员帐号是否已经存在
	 * 
	 * @param account
	 * @@return true/false
	 */
	boolean findMemberAccountIsExists(String account) throws EliteServiceException;

	/**
	 * 根据会员帐号查询
	 * 
	 * @param account
	 * @return MemberPassport
	 */
	MemberPassport findMemberPassportByAccount(String account) throws EliteServiceException;

	/**
	 * 根据会员ID查询
	 * 
	 * @param memberId
	 * @return MemberPassport
	 */
	MemberPassport findMemberPassportById(Long memberId) throws EliteServiceException;

	/**
	 * 根据会员ID查询会员详细信息
	 * 
	 * @param memberId
	 * @return memberDetailInfo
	 */
	MemberDetailInfo findMemberDetailInfoById(Long memberId) throws EliteServiceException;

	/**
	 * 查询会员列表
	 * 
	 * @param type:会员类型
	 * @param status:状态
	 * @param keyword:名称or会员编号
	 * @param channel:来源的渠道
	 * @param isStar:是否明星
	 * @param startTime:注册的开始时间
	 * @param endTime:注册的结束时间
	 * @param pager
	 * @return SearchResult<MemberPassport>
	 */
	SearchResult<MemberPassport> findMemberPassportList(String type, String status, String keyword, String channel,
			boolean isStar, Date startTime, Date endTime, Pager pager) throws EliteServiceException;

	/**
	 * 人才推荐方_精英管理 查询精英列表
	 * 
	 * @param startTime
	 *            注册的开始时间
	 * @param endTime
	 *            注册的结束时间
	 * @param realName
	 *            用户名（模糊查询）
	 * @param searchType
	 *            查询类别（近七日未登录，注册后从未接单，近七日未接单且无进行项目）
	 * @param pager
	 *            分页
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberPassportListByMemberParnterElite(Long memberId, Date startTime, Date endTime,
			String realName, String searchType, Pager pager) throws EliteServiceException;

	/**
	 * 渠道方 (项目方) 查询
	 * 
	 * @param keyword
	 *            模糊查询（姓名,渠道号，手机号）
	 * @param type
	 * @param memberPartnerType
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberPassportListByMemberParnter(String keyword, String type,
			String memberPartnerType,Date startTime, Date endTime,Pager pager,String status) throws EliteServiceException;
	
	/**
	 * 渠道方 (人才方) 查询
	 * 
	 * @param keyword
	 *            模糊查询（姓名,渠道号，手机号）
	 * @param type
	 * @param memberPartnerType
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberParnterList(String keyword, String type,
			String memberPartnerType, Date startTime,Date endTime,Pager pager,String status) throws EliteServiceException;

	/**
	 * 人才中心（cto，精英）查询
	 * 
	 * @param keyword
	 *            模糊查询(姓名，编号)
	 * @param ctoed
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberPassportListByMemberElite(String keyword, Boolean ctoed, String status,Date startTime,Date endTime,
			Pager pager,String inviteCode,String role) throws EliteServiceException;

	/**
	 * 精英圈查询
	 * 
	 * @param memberId:当前会员ID
	 * @param keyword
	 *            模糊查询(昵称，编号)
	 * @param role 精英角色
	 * @param jobAge 工作年限
	 * @param duration 可分配时长
	 * @param industry 行业
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberPassportListByEliteCircle(Long memberId,String keyword, String role,
			String jobAge,String duration,String industry,Pager pager) throws EliteServiceException;
	
	/**
	 * 项目方查询
	 * 
	 * @param keyword
	 *            模糊查询(昵称，编号)
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberPassportListByMemberCompany(String keyword, String status,
			String currentType, Date startTime, Date endTime,Pager pager) throws EliteServiceException;
	/**
	 * 根据会员当前类型查询
	 * 
	 * @param keyword
	 *            模糊查询(昵称，电话好，姓名)
	 * @param status
	 * @param pager
	 * @return
	 * @throws EliteServiceException
	 */
	SearchResult<MemberPassport> findMemberPassportListByCurrentType(String keyword, String status,
			String currentType, Pager pager,String orderType) throws EliteServiceException;

	/**
	 * 查询参与该项目的会员
	 * 
	 * @param keyWord
	 * @return List<MemberPassport>
	 */
	List<MemberPassport> findMemberPassportListForProject(String keyWord, Long projectId) throws EliteServiceException;
	
	/**
	 * 查询审核通过的精英会员列表
	 * 
	 * @param count
	 * @return List<MemberDetailInfo>
	 * */
	List<MemberDetailInfo> findMemberEliteListAuditPass(Integer count)throws EliteServiceException;
	
	
	/**
	 *查询CTO 不分页
	 */
	List<MemberPassport> findMemberpassPortCTOList(String keyWord)throws EliteServiceException;
	/**
	 *查询项目方
	 */
	List<MemberPassport> findMemberpassPortCompanyList(String keyWord)throws EliteServiceException;
	
	/**
	 * 查询精英会员入驻的数量，审核通过的精英数量
	 * 
	 * @return integer
	 * */
	Integer findMemberEliteCount()throws EliteServiceException;
	
	/**
	 * 查询项目方数量
	 * 
	 * @return integer
	 * */
	Integer findMemberCompanyCount(String status,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 查询渠道方数量
	 * 
	 * @return integer
	 * */
	Integer findMemberPartnerCount(String memberPartnerType,String status,Date startTime,Date endTime)throws EliteServiceException;
	
	/**
	 * 更新精英首页显示状态
	 * @param memberId
	 * @param homeShow
	 * @throws EliteServiceException
	 */
	void updateMemberHomeShow(Long memberId,Boolean homeShow)throws EliteServiceException;
	
	/**
	 * 禁用会员
	 * 
	 * @param obj
	 * @return memberPassport
	 */
	MemberPassport updateDisableMemberPassport(MemberPassport obj) throws EliteServiceException;
	
	/**
	 * 删除会员
	 * */
	void deleteMember(Long id)throws EliteServiceException;
	
	/**
	 * 查询多角色的用户(不包含本身)
	 * @param account
	 * @return
	 */
	List<MemberPassport> findMoreMemberIdentiyList(String account);
	
}
