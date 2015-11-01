package tracker_java.Models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cristian on 11/1/15.
 */
@Entity
@Table(name = "ITEM", schema = "public", catalog = "tracker")
public class ItemEntity {
    private Integer id;
    private Integer projectid;
    private Integer itemparentid;
    private Integer priority;
    private String context;
    private String title;
    private String description;
    private Date deadlinedate;
    private Integer memberid;
    private Integer authorid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique=true, insertable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "ITEMPARENTID")
    public Integer getItemparentid() {
        return itemparentid;
    }

    public void setItemparentid(Integer itemparentid) {
        this.itemparentid = itemparentid;
    }

    @Basic
    @Column(name = "PRIORITY")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "CONTEXT")
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "DEADLINEDATE")
    public Date getDeadlinedate() {
        return deadlinedate;
    }

    public void setDeadlinedate(Date deadlinedate) {
        this.deadlinedate = deadlinedate;
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
    @Column(name = "AUTHORID")
    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (projectid != null ? !projectid.equals(that.projectid) : that.projectid != null) return false;
        if (itemparentid != null ? !itemparentid.equals(that.itemparentid) : that.itemparentid != null) return false;
        if (priority != null ? !priority.equals(that.priority) : that.priority != null) return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (deadlinedate != null ? !deadlinedate.equals(that.deadlinedate) : that.deadlinedate != null) return false;
        if (memberid != null ? !memberid.equals(that.memberid) : that.memberid != null) return false;
        if (authorid != null ? !authorid.equals(that.authorid) : that.authorid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projectid != null ? projectid.hashCode() : 0);
        result = 31 * result + (itemparentid != null ? itemparentid.hashCode() : 0);
        result = 31 * result + (priority != null ? priority.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (deadlinedate != null ? deadlinedate.hashCode() : 0);
        result = 31 * result + (memberid != null ? memberid.hashCode() : 0);
        result = 31 * result + (authorid != null ? authorid.hashCode() : 0);
        return result;
    }
}
