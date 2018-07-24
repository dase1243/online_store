package net.kzn.onlineshopping.controller;

import net.kzn.shoppingbackend.dao.PostDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Post;
import net.kzn.shoppingbackend.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

    @Autowired
    private ProductDAO productDAO;

    @RequestMapping("/admin/all/products")
    @ResponseBody
    public List<Product> getAllProductsList() {
        return productDAO.list();
    }

    @RequestMapping("/all/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productDAO.listActiveProducts();
    }

    @RequestMapping("/category/{id}/products")
    @ResponseBody
    public List<Product> getProductsByCategory(@PathVariable int id) {
        return productDAO.listActiveProductsByCategory(id);
    }

    @RequestMapping("/mv/products")
    @ResponseBody
    public List<Product> getMostViewedProducts() {
        return productDAO.getProductsByParam("views", 5);
    }

    @RequestMapping("/mp/products")
    @ResponseBody
    public List<Product> getMostPurchasedProducts() {
        return productDAO.getProductsByParam("purchases", 5);
    }


    @Autowired
    private PostDAO postDAO;

    @RequestMapping("/admin/all/posts")
    @ResponseBody
    public List<Post> getAllPostsList() {
        return postDAO.list();
    }

    @RequestMapping("/all/posts")
    @ResponseBody
    public List<Post> getAllPosts() {
        return postDAO.listActivePosts();
    }

//    @RequestMapping("/category/{id}/posts")
//    @ResponseBody
//    public List<Post> getPostsByCategory(@PathVariable int id) {
//        return postDAO.listActivePostsByCategory(id);
//    }

    @RequestMapping("/mv/posts")
    @ResponseBody
    public List<Post> getMostViewedPosts() {
        return postDAO.getPostsByParam("views", 5);
    }

    @RequestMapping("/mp/posts")
    @ResponseBody
    public List<Post> getMostPurchasedPosts() {
        return postDAO.getPostsByParam("purchases", 5);
    }



}
