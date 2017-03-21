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
    private String id;
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

    public Showing(int screen, String date, String time) {
        this.id = genId();
        this.title = getTitle();
        this.screen = screen;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String genId() {
        String id = "999";
        return id;
    }

    public String getTitle(){ return title;
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