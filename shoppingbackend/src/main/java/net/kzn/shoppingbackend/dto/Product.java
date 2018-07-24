package net.kzn.shoppingbackend.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.UUID;

@Component
@Entity
@Getter
@Setter
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    // private fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @NotBlank(message = "Please enter the product name!")
    private String name;
    @NotBlank(message = "Please enter the brand name!")
    private String brand;
    @NotBlank(message = "Please enter the description!")
    private String description;
    @Column(name = "unit_price")
    @Min(value = 1, message = "Please select at least one value!")
    private double unitPrice;
    private int quantity;
    @Column(name = "is_active")
    private boolean active;
    @Column(name = "category_id")
    @JsonIgnore
    private int categoryId;
    @Column(name = "supplier_id")
    @JsonIgnore
    private int supplierId;
    private int purchases;
    private int views;

    @Transient
    private MultipartFile file;

    // default constructor
    public Product() {
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    // toString for debugging
    @Override
    public String toString() {
        return "Product [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + ", description="
                + description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active=" + active
                + ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", purchases=" + purchases + ", views="
                + views + "]";
    }
}
