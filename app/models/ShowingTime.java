package models;

import com.avaje.ebean.*;
import javax.persistence.*;

/**
 * Created by evan_ on 22/03/2017.
 */
@Entity
@SequenceGenerator(name = "time_gen", allocationSize=1, initialValue=1)
public class ShowingTime extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_gen")
    private String id;

    private String time;

    @ManyToOne()
    @JoinColumn(name="showingId")
    private Showing showing;




    public ShowingTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Showing getShowing() {
        return showing;
    }

    public void setShowing(Showing showing) {
        this.showing = showing;
    }
}
