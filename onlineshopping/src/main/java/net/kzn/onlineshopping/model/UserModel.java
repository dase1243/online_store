package net.kzn.onlineshopping.model;

import lombok.Getter;
import lombok.Setter;
import net.kzn.shoppingbackend.dto.Cart;

import java.io.Serializable;

@Getter
@Setter
public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String fullName;
    private String role;
    private Cart cart;
}
