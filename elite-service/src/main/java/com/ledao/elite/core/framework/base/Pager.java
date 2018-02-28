package com.ledao.elite.core.framework.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Pager对象封装分页和排序查询
 * 
 * @author kobe.liu
 */
public class Pager {

	/** 页码列表大小 */
	public static final int SHOW_PAGETH_COUNT = 5;
	
	/** 开始索引 */
	Integer startIndex = 0;
	/** 分页大小 */
	Integer pageSize = 10;
	/** 排序字段 */
	String sort = "";
	/** 排序方向 */
	String dir = "";
	/** 当前页码 */
	Integer pageth = 1;
	/** 页码列表 */
	Integer[] pageths;
	/** 总页码数 */
	Integer pageCount = 0;
	/** 总记录数 */
	Long rowCount = 0L;
	/** 前边显示标识 */
	private boolean headView = true;
	/** 后边显示标识 */
	private boolean footView = true;

	public Pager() {
	}

	public Pager(Integer startIndex, Integer pageSize) {
		setStartIndex(startIndex);
		setPageSize(pageSize);
	}

	public Pager(Integer startIndex, Integer pageSize, String sort, String dir) {
		setStartIndex(startIndex);
		setPageSize(pageSize);
		setSort(sort);
		setDir(dir);
	}

	public void calPageCount(Long rowCount) {
		setRowCount(rowCount);

		if (pageSize != 0) {
			pageCount = rowCount.intValue() / pageSize;
			if (pageCount * pageSize < rowCount) {
				pageCount++;
			}
		}
		if (rowCount == 0) {
			pageth = 1;
		}

		calPageths();
	}

	private void calPageths() {
		int first = pageth - SHOW_PAGETH_COUNT / 2;
		if (first < 2) {
			first = 1;
		}
		int length = 0;
		if (first + SHOW_PAGETH_COUNT <= pageCount) {
			length = SHOW_PAGETH_COUNT;
		} else {
			length = pageCount - first + 1;
		}

		List<Integer> pagethsList = new ArrayList<Integer>();

		for (int i = 0; i < length; i++) {
			int pageTemp = first + i;
			if (pageTemp > 1 && pageTemp < pageCount) {
				pagethsList.add(pageTemp);
			}
		}

		if (pagethsList.size() > 0) {
			int cha = SHOW_PAGETH_COUNT - pagethsList.size();
			for (int i = 0; i < cha; i++) {
				if (pagethsList.get(0) - 1 <= 1
						&& pageCount - pagethsList.get(pagethsList.size() - 1) > 1) {
					pagethsList
							.add(pagethsList.get(pagethsList.size() - 1) + 1);
					continue;
				}
				if (pagethsList.get(0) - 1 > 1
						&& pageCount - pagethsList.get(pagethsList.size() - 1) <= 1) {
					pagethsList.add(0, pagethsList.get(0) - 1);
					continue;
				}
			}
		}

		if (pagethsList.size() > 0) {
			if (pagethsList.get(0) - 1 <= 1) {
				headView = false;
			} else {
				headView = true;
			}
			if (pageCount - pagethsList.get(pagethsList.size() - 1) <= 1) {
				footView = false;
			} else {
				footView = true;
			}
		} else {
			headView = false;
			footView = false;
		}

		pageths = new Integer[pagethsList.size()];
		for (int i = 0; i < pagethsList.size(); i++) {
			pageths[i] = pagethsList.get(i);
		}
	}

	public void setPageth(Integer pageth) {
		this.pageth = pageth;
		startIndex = (getPageth() - 1) * getPageSize();
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageth() {
		return pageth;
	}

	public Long getRowCount() {
		return rowCount;
	}

	public void setRowCount(Long rowCount) {
		this.rowCount = rowCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Integer[] getPageths() {
		return pageths;
	}

	public void setPageths(Integer[] pageths) {
		this.pageths = pageths;
	}

	public boolean isHeadView() {
		return headView;
	}

	public void setHeadView(boolean headView) {
		this.headView = headView;
	}

	public boolean getFootView() {
		return footView;
	}

	public void setFootView(boolean footView) {
		this.footView = footView;
	}

	public static int getShowPagethCount() {
		return SHOW_PAGETH_COUNT;
	}

}
