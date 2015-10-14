package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the "projectStatus" database table.
 * 
 */
@Entity
@Table(name="\"projectStatus\"")
@NamedQuery(name="ProjectStatus.findAll", query="SELECT p FROM ProjectStatus p")
public class ProjectStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"projectStatusId\"")
	private Integer projectStatusId;

	@Column(name="\"memberId\"")
	private Integer memberId;

	@Column(name="\"projectId\"")
	private Integer projectId;

	@Column(name="\"statusDate\"")
	private Timestamp statusDate;

	@Column(name="\"statusKey\"")
	private Integer statusKey;

	public ProjectStatus() {
	}

	public Integer getProjectStatusId() {
		return this.projectStatusId;
	}

	public void setProjectStatusId(Integer projectStatusId) {
		this.projectStatusId = projectStatusId;
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