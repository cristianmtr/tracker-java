package tracker_java.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by cristian on 10/4/15.
 */
@Entity
@Table(name="frk_project")
public class TasklistObject {
    @Id
    @GeneratedValue
    @Column(name="projectId")
    public Long id;

    @Column(name="name")
    public String name;
}
