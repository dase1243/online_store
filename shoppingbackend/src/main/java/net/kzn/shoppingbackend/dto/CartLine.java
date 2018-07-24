package net.kzn.shoppingbackend.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart_line")
@Getter
@Setter
public class CartLine implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Product product;
    @Column(name = "cart_id")
    private int cartId;
    @Column(name = "product_count")
    private int productCount;
    private double total;
    @Column(name = "buying_price")
    private double buyingPrice;

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    @Column(name = "is_available")
    private boolean available = true;

}
