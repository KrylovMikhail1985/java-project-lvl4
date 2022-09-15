package hexlet.code.domain;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity(name = "checked_sites")
public class SiteCheck extends Model {
    @Id
    long id;
    @Column(name = "url_id")
    long urlId;
    String title;
    String h1;
    String description;
    @WhenCreated
    Date checkingDate;

    public SiteCheck() {
    }

    public final long getId() {
        return id;
    }

    public final void setId(long id) {
        this.id = id;
    }

    public final long getSiteId() {
        return urlId;
    }

    public final void setSiteId(long urlId) {
        this.urlId = urlId;
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

    public final Date getCheckingDate() {
        return checkingDate;
    }

    public final void setCheckingDate(Date checkingDate) {
        this.checkingDate = checkingDate;
    }
}
