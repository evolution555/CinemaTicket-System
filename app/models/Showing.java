package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Created by evan_ on 20/03/2017.
 */
@Entity
public class Showing extends Model{
    @Id
    private Long id;
    @ManyToOne()
    private String title;
    @Constraints.Required
    private int screen;
    @Constraints.Required
    private String date;
    @Constraints.Required
    private String time;

    public Showing(){
    }
    public static Finder<Long, Showing> find = new Finder<Long, Showing>(Showing.class);

    public static List<Showing> findAll(){
        return Showing.find.all();
    }

    public static List<Showing> findMovieShowings(String movieName){
        return Showing.find.where().like("title", movieName).findList();
    }

    public Showing(Long id, String title, int screen, String date, String time) {
        this.id = id;
        this.title = title;
        this.screen = screen;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}