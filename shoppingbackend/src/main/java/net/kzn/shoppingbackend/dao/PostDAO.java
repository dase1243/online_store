package net.kzn.shoppingbackend.dao;

import net.kzn.shoppingbackend.dto.Post;

import java.util.List;

public interface PostDAO {

    Post get(int postId);

    List<Post> list();

    boolean add(Post post);

    boolean update(Post post);

    boolean delete(Post post);

    List<Post> getPostsByParam(String param, int count);

    // business methods
    List<Post> listActivePosts();

//    List<Post> listActivePostsByTag(int categoryId);

    List<Post> getLatestActivePosts(int count);
}
