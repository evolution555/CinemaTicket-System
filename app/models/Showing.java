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
    @Id //Primary Key
    private String id;
    private int screenNo;

    @ManyToOne()
    private String title;
    private String time;
    private String date;

    public static Finder<String, Showing> find = new Finder<String, Showing>(Film.class);

    public static List<Showing> findAll(){
        return Showing.find.all();
    }

    public String getId() {
        return id;
    }

    public void Id() {
        this.id = title+id;
    }

    public int getScreenNo() {
        return screenNo;
    }

    public void setScreenNo(int screenNo) {
        this.screenNo = screenNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
