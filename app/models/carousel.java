package models;

import java.util.*; 
import javax.persistence.*; 
import play.data.format.*; 
import play.data.validation.*;
 
import com.avaje.ebean.*;

/**
 * Created by evan_ on 24/03/2017.
 */
@Entity
public class carousel extends Model{
    @Id //Primary Key
    private String title;
    private String description;

    public static Finder<String, carousel> find = new Finder<String, carousel>(carousel.class);

    public static List<carousel> findAll(){
        return carousel.find.all();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static Finder<String, carousel> getFind() {
        return find;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
