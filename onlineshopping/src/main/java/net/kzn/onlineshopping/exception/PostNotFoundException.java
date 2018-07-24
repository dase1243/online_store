package net.kzn.onlineshopping.exception;

import java.io.Serializable;

public class PostNotFoundException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;

    public PostNotFoundException() {
        this("Post is not available!");
    }

    public PostNotFoundException(String message) {
        this.message = System.currentTimeMillis() + ": " + message;
    }

    public String getMessage() {
        return message;
    }
}
