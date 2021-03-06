package dyrhoi.github.jpa_project.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonTeamId implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long p_id;
    private Long t_id;

    public PersonTeamId() {
    }

    public PersonTeamId(Long p_id, Long t_id) {
        this.p_id = p_id;
        this.t_id = t_id;
    }

    public Long getP_id() {
        return p_id;
    }

    public void setP_id(Long p_id) {
        this.p_id = p_id;
    }

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p_id, t_id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonTeamId other = (PersonTeamId) obj;

        return (Objects.equals(this.p_id, other.p_id) && Objects.equals(this.t_id, other.t_id));
    }

    @Override
    public String toString() {
        return "PersonTeamId{" + "p_id=" + p_id + ", t_id=" + t_id + '}';
    }



}
