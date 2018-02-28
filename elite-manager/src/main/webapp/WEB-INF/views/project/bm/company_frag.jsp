<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

					<table class="project-table table"
						style="margin-left: 15px; margin-right: 15px; width: 770">
						</thead>
						<tbody>
							<c:forEach items="${list}" var="it">
								<tr>
									<td>
										<div style="padding: 5px 10px; text-align: left;"
											id="selectCompanyItem" data="${it.id}" text="${it.nickName}"
											>
											<p>
												<span style="margin-left: 20px;">&nbsp;${it.account}</span>
												<span style="margin-left: 20px;">&nbsp;${it.nickName}</span>
												<span style="margin-left: 20px;">&nbsp;${it.memberNum}</span>
												
											</p>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
