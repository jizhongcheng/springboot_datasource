package com.windmill.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.beans.ConstructorProperties;
import javax.persistence.Transient;

public class PagedEntity {
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof PagedEntity)) {
			return false;
		}
		PagedEntity other = (PagedEntity) o;
		if (!other.canEqual(this)) {
			return false;
		}
		this.page = getPage();
		other.page = other.getPage();
		if (this.page == null ? other.page != null : !this.page.equals(other.page)) {
			return false;
		}
		this.rows = getRows();
		other.rows = other.getRows();
		return this.rows == null ? other.rows == null : this.rows.equals(other.rows);
	}

	protected boolean canEqual(Object other) {
		return other instanceof PagedEntity;
	}

	public int hashCode() {
		int PRIME = 59;
		int result = 1;
		this.page = getPage();
		result = result * 59 + (this.page == null ? 43 : this.page.hashCode());
		this.rows = getRows();
		result = result * 59 + (this.rows == null ? 43 : this.rows.hashCode());
		return result;
	}

	public String toString() {
		return "PagedEntity(page=" + getPage() + ", rows=" + getRows() + ")";
	}

	@ConstructorProperties({ "page", "rows" })
	public PagedEntity(Integer page, Integer rows) {
		this.page = page;
		this.rows = rows;
	}

	public Integer getPage() {
		return this.page;
	}

	@JsonIgnore
	@Transient
	private Integer page = Integer.valueOf(1);

	public Integer getRows() {
		return this.rows;
	}

	@JsonIgnore
	@Transient
	private Integer rows = Integer.valueOf(10);

	public PagedEntity() {
	}
}
