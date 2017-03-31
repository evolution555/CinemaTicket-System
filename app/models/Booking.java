package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Booking extends Model{
    @Id //Primary Key
    private int bookingId;
    @ManyToOne()
    private String title;
    private String time;
    private String date;
    private int qty;

    private double total;

    private final double COST = 10.00;

    public Booking(int qty, String time, String date) {
        bookingId = genId();
        this.qty = qty;
        this.time = time;
        this.date = date;
        total = calcTotal(qty);
    }

    public int genId(){
        Random rand = new Random();
       int randNum = rand.nextInt((9999 - 1001) + 1) + 1001;
       return randNum;
    }
    public double calcTotal(int qty){
        total = qty * COST;
        return total;
    }

    public int getBookingId() {
        return bookingId;
    }
}
