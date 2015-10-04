package tracker_java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * Created by cristian on 10/4/15.
 */
@Entity
@Table(name = "frk_item")
public class TaskObject {
    @Id
    @GeneratedValue
    @Column(name="itemId")
    public Long DT_RowId;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @Column(name="memberId")
    public int responsible;

    @Column(name="authorId")
    public int author;

    @Column(name="deadlineDate")
    public Date deadline;

    @Column(name="priority")
    public int priority;

    @Column(name="projectId")
    public int tasklist;

    public TaskObject() {

    }

    public TaskObject(String title, String description, int responsible, int author, Date deadline, int priority, int tasklist) {
        this.title = title;
        this.description = description;
        this.responsible = responsible;
        this.author = author;
        this.deadline = deadline;
        this.priority = priority;
        this.tasklist = tasklist;
    }
}
