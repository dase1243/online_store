package net.kzn.onlineshopping.controller;

import net.kzn.onlineshopping.util.FileUtil;
import net.kzn.onlineshopping.validator.PostValidator;
import net.kzn.shoppingbackend.dao.PostDAO;
import net.kzn.shoppingbackend.dto.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/manage")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    @Autowired
    private PostDAO postDAO;

    @RequestMapping("/post")
    public ModelAndView managePost(@RequestParam(name = "success", required = false) String success) {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Post Management");
        mv.addObject("userClickManagePost", true);

        Post nPost = new Post();

        // assuming that the user is ADMIN
        // later we will fixed it based on user is SUPPLIER or ADMIN
        nPost.setActive(true);

        mv.addObject("post", nPost);

        if (success != null) {
            if (success.equals("post")) {
                mv.addObject("message", "Post submitted successfully!");
            } else if (success.equals("category")) {
                mv.addObject("message", "Category submitted successfully!");
            }
        }
        return mv;
    }

    @RequestMapping("/{id}/post")
    public ModelAndView managePostEdit(@PathVariable int id) {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Post Management");
        mv.addObject("userClickManagePost", true);

        // Post nPost = new Post();
        mv.addObject("post", postDAO.get(id));

        return mv;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String managePostPost(@Valid @ModelAttribute("post") Post mPost,
                                 BindingResult results, Model model, HttpServletRequest request) {

        // mandatory file upload check
        if (mPost.getId() == 0) {
            new PostValidator().validate(mPost, results);
        } else {
            // edit check only when the file has been selected
            if (!mPost.getFile().getOriginalFilename().equals("")) {
                new PostValidator().validate(mPost, results);
            }
        }

        if (results.hasErrors()) {
            model.addAttribute("message", "Validation fails for adding the post!");
            model.addAttribute("userClickManagePost", true);
            return "page";
        }

        if (mPost.getId() == 0) {
            postDAO.add(mPost);
        } else {
            postDAO.update(mPost);
        }

        //upload the file
        if (!mPost.getFile().getOriginalFilename().equals("")) {
            FileUtil.uploadFile(request, mPost.getFile(), mPost.getCode());
        }

        return "redirect:/manage/post?success=post";
    }

    @RequestMapping(value = "/post/{id}/activation", method = RequestMethod.GET)
    @ResponseBody
    public String managePostPostActivation(@PathVariable int id) {
        Post post = postDAO.get(id);
        boolean isActive = post.isActive();
        post.setActive(!isActive);
        postDAO.update(post);
        return (isActive) ? "Post Dectivated Successfully!" : "Post Activated Successfully";
    }

//    @RequestMapping(value = "/category", method = RequestMethod.POST)
//    public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {
//        categoryDAO.add(mCategory);
//        return "redirect:" + request.getHeader("Referer") + "?success=category";
//    }

//    @ModelAttribute("categories")
//    public List<Category> modelCategories() {
//        return categoryDAO.list();
//    }
//
//    @ModelAttribute("category")
//    public Category modelCategory() {
//        return new Category();
//    }
}