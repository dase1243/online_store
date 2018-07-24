package net.kzn.onlineshopping.model;

import lombok.Getter;
import lombok.Setter;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.User;

import java.io.Serializable;

@Getter
@Setter
public class RegisterModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private Address billing;
}
