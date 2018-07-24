package net.kzn.shoppingbackend.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_detail")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Please enter first name!")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "Please enter last name!")
    @Column(name = "last_name")
    private String lastName;
    @NotBlank(message = "Please enter email address!")
    private String email;
    @NotBlank(message = "Please enter contact number!")
    @Column(name = "contact_number")
    private String contactNumber;
    private String role;
    @NotBlank(message = "Please enter password!")
    private String password;
    private boolean enabled = true;
    @Transient
    private String confirmPassword;

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", contactNumber=" + contactNumber + ", role=" + role + ", password=" + password + ", enabled="
                + enabled + "]";
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
