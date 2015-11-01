package tracker_java.Models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by cristian on 11/1/15.
 */
@Entity
@Table(name = "ITEMCOMMENT", schema = "public", catalog = "tracker")
public class ItemcommentEntity {
    private Integer itemcommentid;
    private Integer itemid;
    private Integer memberid;
    private Timestamp postdate;
    private String body;
    private Timestamp lastchangedate;

    @Id
    @Column(name = "ITEMCOMMENTID")
    public Integer getItemcommentid() {
        return itemcommentid;
    }

    public void setItemcommentid(Integer itemcommentid) {
        this.itemcommentid = itemcommentid;
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
    @Column(name = "MEMBERID")
    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    @Basic
    @Column(name = "POSTDATE")
    public Timestamp getPostdate() {
        return postdate;
    }

    public void setPostdate(Timestamp postdate) {
        this.postdate = postdate;
    }

    @Basic
    @Column(name = "BODY")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    @Column(name = "LASTCHANGEDATE")
    public Timestamp getLastchangedate() {
        return lastchangedate;
    }

    public void setLastchangedate(Timestamp lastchangedate) {
        this.lastchangedate = lastchangedate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemcommentEntity that = (ItemcommentEntity) o;

        if (itemcommentid != null ? !itemcommentid.equals(that.itemcommentid) : that.itemcommentid != null)
            return false;
        if (itemid != null ? !itemid.equals(that.itemid) : that.itemid != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (postdate != null ? !postdate.equals(that.postdate) : that.postdate != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (lastchangedate != null ? !lastchangedate.equals(that.lastchangedate) : that.lastchangedate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemcommentid != null ? itemcommentid.hashCode() : 0;
        result = 31 * result + (itemid != null ? itemid.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        result = 31 * result + (postdate != null ? postdate.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (lastchangedate != null ? lastchangedate.hashCode() : 0);
        return result;
    }
}
