package tracker_java.Models;

import javax.persistence.*;

/**
 * Created by cristian on 11/1/15.
 */
@Entity
@Table(name = "MEMBERPROJECT", schema = "public", catalog = "tracker")
@IdClass(MemberprojectEntityPK.class)
public class MemberprojectEntity {
    private Integer memberid;
    private Integer projectid;
    private Integer position;

    @Id
    @Column(name = "MEMBERID")
    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    @Id
    @Column(name = "PROJECTID")
    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "POSITION")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberprojectEntity that = (MemberprojectEntity) o;

        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (projectid != null ? !projectid.equals(that.projectid) : that.projectid != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberid != null ? memberid.hashCode() : 0;
        result = 31 * result + (projectid != null ? projectid.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
