package com.jpmc.review06092022.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "published")
    private boolean published;
    @Column(name="quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    public Tutorial() {
    }

    public Tutorial(Long id, String title, String description, boolean published, int quantity, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
        this.quantity = quantity;
        this.price = price;
    }

    public Tutorial(String title, String description, boolean published, int quantity, double price) {
        this.title = title;
        this.description = description;
        this.published = published;
        this.quantity = quantity;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tutorial)) return false;
        Tutorial tutorial = (Tutorial) o;
        return isPublished() == tutorial.isPublished() && getQuantity() == tutorial.getQuantity() && Double.compare(tutorial.getPrice(), getPrice()) == 0 && Objects.equals(getId(), tutorial.getId()) && Objects.equals(getTitle(), tutorial.getTitle()) && Objects.equals(getDescription(), tutorial.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), isPublished(), getQuantity(), getPrice());
    }

    @Override
    public String toString() {
        return "Tutorial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", published=" + published +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
