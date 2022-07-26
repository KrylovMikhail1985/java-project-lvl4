package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.NotNull;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

//@DbName("development")
@Entity
// point to name of the table. Default name is the class's name
@Table(name = "url")
public class Url extends Model {
    @Id
    private long id;
    @NotNull
    private String name;
    @WhenCreated
    private Date createdAt;

    @OneToMany(mappedBy = "url")
    private List<UrlCheck> urlChecks;

    public Url() {
    }
    public Url(String name) {
        this.name = name;
    }


//    @Override
//    public final String toString() {
//        return "Url{"
//                + "id=" + id
//                + ", name='" + name
//                + '\''
//                + ", createdAt=" + createdAt
//                + '}';
//    }

    public final long getId() {
        return id;
    }

//    public final void setId(long id) {
//        this.id = id;
//    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final Date getCreatedAt() {
        return createdAt;
    }

    public final Date getLastCheck() {
        if (urlChecks.isEmpty()) {
            return null;
        }
        return urlChecks.get(urlChecks.size() - 1).getCreatedAt();
    }
    public final String getStatusCode() {
        if (urlChecks.isEmpty()) {
            return "";
        }
        return "200";
    }
}
