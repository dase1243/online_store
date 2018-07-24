package net.kzn.onlineshopping.validator;

import net.kzn.shoppingbackend.dto.Post;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PostValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Post.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Post post = (Post) target;
        if (post.getFile() == null || post.getFile().getOriginalFilename().equals("")) {
            errors.rejectValue("file", null, "Please select a file to upload!");
            return;
        }
        if (!(post.getFile().getContentType().equals("image/jpeg") ||
                post.getFile().getContentType().equals("image/png")) ||
                post.getFile().getContentType().equals("image/gif")
                ) {
            errors.rejectValue("file", null, "Please select an image file to upload!");
            return;
        }
    }
}
