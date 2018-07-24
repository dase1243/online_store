package net.kzn.onlineshopping.model;

import lombok.Getter;
import lombok.Setter;
import net.kzn.shoppingbackend.dto.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CheckoutModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private Address shipping;
    private Cart cart;
    private List<CartLine> cartLines;
    private OrderDetail orderDetail;
    private double checkoutTotal;
}
