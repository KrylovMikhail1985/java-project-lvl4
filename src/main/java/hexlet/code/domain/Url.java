package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
//@DbName("development")
@Entity
// point to name of the table. Default name is the class's name
//@Table(name = "urls")
public class Url extends Model {
    @Id
    long id;
    @NotNull
    String name;
    @WhenCreated
    Date createdAt;

    public Url() {
    }
    public Url(String name) {
        this.name = name;
    }

    public final long getId() {
        return id;
    }

    public final String getName() {
        return name;
    }

    public final Date getCreatedAt() {
        return createdAt;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
