package pl.edu.wszib.wallet.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name= "texpense")
public class Expense {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double value;
    @Temporal(value = TemporalType.DATE)
    private Date date;
    @Enumerated(EnumType.STRING)
    private Category category;


    public Expense() {
    }

    public Expense(int id, String name, double value, Date date, Category category) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category {
        Rachunki,
        Zakupy,
        Jedzenie,
        Samochod,
        Wakacje,
        Zachcianki,
        Inne
    }
}
