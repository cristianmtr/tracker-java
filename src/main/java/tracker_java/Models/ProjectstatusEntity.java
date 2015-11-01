package tracker_java.Models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cristian on 11/1/15.
 */
@Entity
@Table(name = "PROJECTSTATUS", schema = "public", catalog = "tracker")
public class ProjectstatusEntity {
    private Integer projectstatusid;
    private Integer projectid;
    private Timestamp statusdate;
    private Integer statuskey;
    private Integer memberid;

    @Id
    @Column(name = "PROJECTSTATUSID")
    public Integer getProjectstatusid() {
        return projectstatusid;
    }

    public void setProjectstatusid(Integer projectstatusid) {
        this.projectstatusid = projectstatusid;
    }

    @Basic
    @Column(name = "PROJECTID")
    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    @Basic
    @Column(name = "STATUSDATE")
    public Timestamp getStatusdate() {
        return statusdate;
    }

    public void setStatusdate(Timestamp statusdate) {
        this.statusdate = statusdate;
    }

    @Basic
    @Column(name = "STATUSKEY")
    public Integer getStatuskey() {
        return statuskey;
    }

    public void setStatuskey(Integer statuskey) {
        this.statuskey = statuskey;
    }

    @Basic
    @Column(name = "MEMBERID")
    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectstatusEntity that = (ProjectstatusEntity) o;

        if (projectstatusid != null ? !projectstatusid.equals(that.projectstatusid) : that.projectstatusid != null)
            return false;
        if (projectid != null ? !projectid.equals(that.projectid) : that.projectid != null) return false;
        if (statusdate != null ? !statusdate.equals(that.statusdate) : that.statusdate != null) return false;
        if (statuskey != null ? !statuskey.equals(that.statuskey) : that.statuskey != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = projectstatusid != null ? projectstatusid.hashCode() : 0;
        result = 31 * result + (projectid != null ? projectid.hashCode() : 0);
        result = 31 * result + (statusdate != null ? statusdate.hashCode() : 0);
        result = 31 * result + (statuskey != null ? statuskey.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        return result;
    }
}
