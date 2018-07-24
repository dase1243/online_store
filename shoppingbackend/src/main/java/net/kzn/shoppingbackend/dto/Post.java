package net.kzn.shoppingbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.UUID;

@Component
@Entity
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    @NotBlank(message = "Please enter the post title!")
    private String title;
    @NotBlank(message = "Please enter the annotation!")
    private String annotation;
    @Column(name = "is_active")
    private boolean active;
    @Column(name = "content")
    private boolean content;
//    @Column(name = "category_id")
//    @JsonIgnore
//    private String categoryTag;
    private int views;

    @Transient
    private MultipartFile file;

    // default constructor
    public Post() {
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }
}
