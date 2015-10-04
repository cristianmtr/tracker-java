package tracker_java;

import javax.persistence.*;

/**
 * Created by cristian on 10/4/15.
 */
@Entity
@Table(name = "frk_member")
public class MemberObject {
    @Id
    @GeneratedValue
    @Column(name="memberId")
    public Long id;

    @Column(name="firstName")
    public String name;

    public MemberObject(String name) {
        this.name = name;
    }

    public MemberObject() {
    }
}
