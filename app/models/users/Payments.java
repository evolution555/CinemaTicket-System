package models;

import java.util.*;
import javax.persistence.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

/**
 * Created by evan_ on 01/04/2017.
 */
@Entity
public class Payments {
    @Constraints.Required
    private String name;
    @Id
    @Constraints.Required
    private String cardNumber;
    @Constraints.Required
    private int expMonth;
    @Constraints.Required
    private int expYear;

    public Payments(String name, String cardNumber, int expMonth, int expYear) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }
}
