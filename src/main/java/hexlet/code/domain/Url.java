package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
// point to name of the table. Default name is the class's name
//@Table(name = "urls")
public class Url extends Model {
    @Id
    long id;
    @NotNull
    String name;
    @WhenCreated
    Instant createdAt;

    public Url() {
    }
    public Url(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
