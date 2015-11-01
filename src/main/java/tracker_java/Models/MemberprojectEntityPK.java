package tracker_java.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by cristian on 11/1/15.
 */
public class MemberprojectEntityPK implements Serializable {
    private Integer memberid;
    private Integer projectid;

    @Column(name = "MEMBERID")
    @Id
    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    @Column(name = "PROJECTID")
    @Id
    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberprojectEntityPK that = (MemberprojectEntityPK) o;

        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (projectid != null ? !projectid.equals(that.projectid) : that.projectid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = memberid != null ? memberid.hashCode() : 0;
        result = 31 * result + (projectid != null ? projectid.hashCode() : 0);
        return result;
    }
}
