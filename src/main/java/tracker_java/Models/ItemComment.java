package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "itemComment" database table.
 * 
 */
@Entity
@Table(name="\"itemComment\"")
@NamedQuery(name="ItemComment.findAll", query="SELECT i FROM ItemComment i")
public class ItemComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"itemCommentId\"")
	private Long itemCommentId;

	private String body;

	@Column(name="\"itemId\"")
	private Integer itemId;

	@Column(name="\"lastChangeDate\"")
	private Timestamp lastChangeDate;

	@Column(name="\"memberId\"")
	private Integer memberId;

	@Column(name="\"postDate\"")
	private Timestamp postDate;

	public ItemComment() {
	}

	public Long getItemCommentId() {
		return this.itemCommentId;
	}

	public void setItemCommentId(Long itemCommentId) {
		this.itemCommentId = itemCommentId;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Timestamp getLastChangeDate() {
		return this.lastChangeDate;
	}

	public void setLastChangeDate(Timestamp lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Timestamp getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

}