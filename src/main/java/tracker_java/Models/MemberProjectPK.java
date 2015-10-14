package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "memberProject" database table.
 * 
 */
@Embeddable
public class MemberProjectPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="\"memberId\"")
	private Integer memberId;

	@Column(name="\"projectId\"")
	private Integer projectId;

	public MemberProjectPK() {
	}
	public Integer getMemberId() {
		return this.memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getProjectId() {
		return this.projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MemberProjectPK)) {
			return false;
		}
		MemberProjectPK castOther = (MemberProjectPK)other;
		return 
			this.memberId.equals(castOther.memberId)
			&& this.projectId.equals(castOther.projectId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.memberId.hashCode();
		hash = hash * prime + this.projectId.hashCode();
		
		return hash;
	}
}