package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"itemId\"")
	private Integer itemId;

	@Column(name="\"authorId\"")
	private Integer authorId;

	private String context;

	@Temporal(TemporalType.DATE)
	@Column(name="\"deadlineDate\"")
	private Date deadlineDate;

	private String description;

	@Column(name="\"itemParentId\"")
	private Integer itemParentId;

	@Column(name="\"memberId\"")
	private Integer memberId;

	private Integer priority;

	@Column(name="\"projectId\"")
	private Integer projectId;

	private String title;

	public Item() {
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getDeadlineDate() {
		return this.deadlineDate;
	}

	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getItemParentId() {
		return this.itemParentId;
	}

	public void setItemParentId(Integer itemParentId) {
		this.itemParentId = itemParentId;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}