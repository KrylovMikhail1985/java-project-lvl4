package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

//@DbName("development")
@Entity
// point to name of the table. Default name is the class's name
@Table(name = "urls")
public class Url extends Model {
    @Id
    long id;
    @NotNull
    String name;
    @WhenCreated
    Date createdAt;
    @OneToMany(mappedBy = "url")
    List<UrlCheck> urlChecks;

    public Url() {
    }
    public Url(String name) {
        this.name = name;
    }


    @Override
    public final String toString() {
        return "Url{"
                + "id=" + id
                + ", name='" + name
                + '\''
                + ", createdAt=" + createdAt
                + '}';
    }

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Date getCreatedAt() {
        return createdAt;
    }

    public final void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public final void setUrlChecks(LinkedList<UrlCheck> urlChecks) {
        this.urlChecks = urlChecks;
    }
}
