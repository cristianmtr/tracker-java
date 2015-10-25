package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@XmlRootElement
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique=true, insertable=false)
	private Integer id;

	@Column(name="\"authorId\"")
	private Integer author;

	private String context;

	@Temporal(TemporalType.DATE)
	@Column(name="\"deadlineDate\"")
	private Date deadline;

	private String description;

	@Column(name="\"itemParentId\"")
	private Integer itemParentId;

	@Column(name="\"memberId\"")
	private Integer responsible;

	private Integer priority;

	@Column(name="\"projectId\"")
	private Integer projectId;

	private String title;

	public Item() {
	}

	public Integer getItemId() {
		return this.id;
	}

	public void setItemId(Integer itemId) {
		this.id = itemId;
	}

	public Integer getAuthor() {
		return this.author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
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

	public Integer getResponsible() {
		return responsible;
	}

	public void setResponsible(Integer responsible) {
		this.responsible = responsible;
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