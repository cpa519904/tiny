package org.tinygroup.bizframedslenterprise.inter.util;

import java.io.Serializable;
import java.util.List;

public class Pager implements Serializable{
private List currentList;//当前页的记录
	
	private int totalCount;//总记录数
	
	private int currentPage;//当前页
	
	private int limit;//每页记录数
	
	private int start;//开始记录数
	
	private int totalPages;//总页数
	
	private static final int DEFAULT_LIMIT=10;

	public Pager(int totalCount,int start,List currentList){
		this(totalCount, start, DEFAULT_LIMIT, currentList);
	}
	
    public Pager(int totalCount,int start,int limit,List currentList){
		this.currentList=currentList;
		init(totalCount, start, limit);
	}
    
    private void init(int totalCount, int start, int limit){
        //设置基本参数
        this.totalCount=totalCount;
        if(limit==0){
        	limit=DEFAULT_LIMIT;
        }
        this.limit=limit;
        this.start=start;
        this.totalPages=totalCount%limit==0?totalCount/limit:totalCount/limit+1;
        this.currentPage=start%limit==0?start/limit:start/limit+1;
        if(currentPage>totalPages){
        	currentPage=totalPages;
        }
    }
	
	

	public List getCurrentList() {
		return currentList;
	}

	public void setCurrentList(List currentList) {
		this.currentList = currentList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
