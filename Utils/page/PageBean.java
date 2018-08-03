package com.huarui.entity;

import java.util.List;

/**
 * 分页bean对象
 * @author thnk
 *
 * @param <T>
 */
public class PageBean<T> {
	
	private int pageSize = 5;//页大小
	private int currentPage;//当前页
	private int pageCounts;//总共页数
	private int totalCounts;//总共记录数
	private int start; //sql查询语句起点
	private List<T> data;
	
	public int getStart() {
	    start = (getCurrentPage() - 1) * pageSize;
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
	    if(currentPage > getPageCounts()) {
            currentPage = pageCounts;
        }else if(currentPage <= 0) {
            currentPage = 1;
        }
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCounts() {
	    pageCounts = (totalCounts % pageSize) == 0 ? totalCounts/pageSize : totalCounts/pageSize + 1;
		return pageCounts;
	}

	public void setPageCounts(int pageCounts) {
		this.pageCounts = pageCounts;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	
	
	
	
}
