package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "itemStatus" database table.
 * 
 */
@Entity
@Table(name="\"itemStatus\"")
@NamedQuery(name="ItemStatus.findAll", query="SELECT i FROM ItemStatus i")
public class ItemStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"itemStatusId\"")
	private Long itemStatusId;

	@Column(name="\"itemId\"")
	private Integer itemId;

	@Column(name="\"memberId\"")
	private Integer memberId;

	@Column(name="\"statusDate\"")
	private Timestamp statusDate;

	@Column(name="\"statusKey\"")
	private Integer statusKey;

	public ItemStatus() {
	}

	public Long getItemStatusId() {
		return this.itemStatusId;
	}

	public void setItemStatusId(Long itemStatusId) {
		this.itemStatusId = itemStatusId;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Timestamp getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Timestamp statusDate) {
		this.statusDate = statusDate;
	}

	public Integer getStatusKey() {
		return this.statusKey;
	}

	public void setStatusKey(Integer statusKey) {
		this.statusKey = statusKey;
	}

}