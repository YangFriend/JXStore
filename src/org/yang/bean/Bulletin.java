package org.yang.bean;

import java.util.Date;

/**
 * t_bulletin 持久化类
 * 
 * @author Administrator
 * 
 */
public class Bulletin {
	private Long id;
	private Long managerid;
	private String content;
	private Date releaseDate;
	private String title;

	public Bulletin() {

	}

	/**
	 * 构建一个公告所必需的信息
	 * 
	 * @param managerid
	 * @param content
	 * @param releaseDate
	 * @param title
	 */
	public Bulletin(Long managerid, String content, Date releaseDate,
			String title) {
		this.managerid = managerid;
		this.content = content;
		this.releaseDate = releaseDate;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getManagerid() {
		return managerid;
	}

	public void setManagerid(Long managerid) {
		this.managerid = managerid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Bulletin [id=" + id + ", managerid=" + managerid + ", content="
				+ content + ", releaseDate=" + releaseDate + ", title=" + title
				+ "]";
	}

}
