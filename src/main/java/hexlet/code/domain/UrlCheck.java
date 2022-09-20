package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity(name = "checked_sites")
public class UrlCheck extends Model {
    @Id
    private long id;
    private int statusCode;
    private String title;
    private String h1;
    @Lob
    private String description;
    @ManyToOne(optional = false)
    private Url url;
    @WhenCreated
    private Date createdAt;

    public UrlCheck() {
    }

    public final long getId() {
        return id;
    }

    public final int getStatusCode() {
        return statusCode;
    }

    public final void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getH1() {
        return h1;
    }

    public final void setH1(String h1) {
        this.h1 = h1;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final Url getUrl() {
        return url;
    }

    public final void setUrl(Url url) {
        this.url = url;
    }

    public final Date getCreatedAt() {
        return createdAt;
    }

}
