package tracker_java.Models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "memberProject" database table.
 * 
 */
@Entity
@Table(name="\"memberProject\"")
@NamedQuery(name="MemberProject.findAll", query="SELECT m FROM MemberProject m")
public class MemberProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MemberProjectPK id;

	private Integer position;

	public MemberProject() {
	}

	public MemberProjectPK getId() {
		return this.id;
	}

	public void setId(MemberProjectPK id) {
		this.id = id;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

}