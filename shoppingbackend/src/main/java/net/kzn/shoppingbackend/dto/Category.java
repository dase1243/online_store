package net.kzn.shoppingbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "is_active")
    private boolean active = true;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageURL=" + imageURL
                + ", active=" + active + "]";
    }

}
