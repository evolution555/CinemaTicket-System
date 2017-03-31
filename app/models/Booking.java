package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Booking extends Model{
    @Id //Primary Key
    private String bookingId;
    @ManyToOne()
    private String title;
    private String time;
    private String date;
    private int qty;

}
