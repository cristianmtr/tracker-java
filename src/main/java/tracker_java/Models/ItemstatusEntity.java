package tracker_java.Models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cristian on 11/1/15.
 */
@Entity
@Table(name = "ITEMSTATUS", schema = "public", catalog = "tracker")
public class ItemstatusEntity {
    private Long itemstatusid;
    private Integer itemid;
    private Timestamp statusdate;
    private Integer statuskey;
    private Integer memberid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEMSTATUSID", unique=true, insertable=false)
    public Long getItemstatusid() {
        return itemstatusid;
    }

    public void setItemstatusid(Long itemstatusid) {
        this.itemstatusid = itemstatusid;
    }

    @Basic
    @Column(name = "ITEMID")
    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
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

        ItemstatusEntity that = (ItemstatusEntity) o;

        if (itemstatusid != null ? !itemstatusid.equals(that.itemstatusid) : that.itemstatusid != null) return false;
        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;
        if (statusdate != null ? !statusdate.equals(that.statusdate) : that.statusdate != null) return false;
        if (statuskey != null ? !statuskey.equals(that.statuskey) : that.statuskey != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemstatusid != null ? itemstatusid.hashCode() : 0;
        result = 31 * result + (itemid != null ? itemid.hashCode() : 0);
        result = 31 * result + (statusdate != null ? statusdate.hashCode() : 0);
        result = 31 * result + (statuskey != null ? statuskey.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        return result;
    }
}
