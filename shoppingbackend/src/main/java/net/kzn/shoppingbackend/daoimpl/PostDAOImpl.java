package net.kzn.shoppingbackend.daoimpl;

import net.kzn.shoppingbackend.dao.PostDAO;
import net.kzn.shoppingbackend.dto.Post;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("postDAO")
@Transactional
public class PostDAOImpl implements PostDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //Get
    @Override
    public Post get(int postId) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .get(Post.class, Integer.valueOf(postId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //List
    @Override
    public List<Post> list() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM Post", Post.class)
                .getResultList();
    }

    //INSERT
    @Override
    public boolean add(Post post) {
        try {
            sessionFactory
                    .getCurrentSession()
                    .persist(post);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //UPDATE
    @Override
    public boolean update(Post post) {
        try {
            sessionFactory
                    .getCurrentSession()
                    .update(post);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }


    //DELETE
    @Override
    public boolean delete(Post post) {
        try {
            post.setActive(false);
            // call the update method
            return this.update(post);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Post> listActivePosts() {
        String selectActivePosts = "FROM Post WHERE active = :active";
        return sessionFactory
                .getCurrentSession()
                .createQuery(selectActivePosts, Post.class)
                .setParameter("active", true)
                .getResultList();
    }

//    @Override
//    public List<Post> listActivePostsByTag(int categoryId) {
//        String selectActivePostsByCategory = "FROM Post WHERE active = :active AND categoryTag = :categoryTag";
//        return sessionFactory
//                .getCurrentSession()
//                .createQuery(selectActivePostsByCategory, Post.class)
//                .setParameter("active", true)
//                .setParameter("categoryId", categoryId)
//                .getResultList();
//    }

    @Override
    public List<Post> getLatestActivePosts(int count) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM Post WHERE active = :active ORDER BY id", Post.class)
                .setParameter("active", true)
                .setFirstResult(0)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Post> getPostsByParam(String param, int count) {
        String query = "FROM Post WHERE active = true ORDER BY " + param + " DESC";
        return sessionFactory
                .getCurrentSession()
                .createQuery(query, Post.class)
                .setFirstResult(0)
                .setMaxResults(count)
                .getResultList();
    }
}