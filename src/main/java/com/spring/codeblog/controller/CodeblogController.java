package com.spring.codeblog.controller;


import com.spring.codeblog.model.Post;
import com.spring.codeblog.service.CodeblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CodeblogController {

    @Autowired
    CodeblogService codeblogService;

    @RequestMapping(value ="/", method= RequestMethod.GET)
    public String getRoot(){
       return "landingPage";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ModelAndView getPosts(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Post> posts = codeblogService.findAll();
        if(!(principal instanceof UserDetails)){
            ModelAndView mv = new ModelAndView("posts");
            mv.addObject("posts", posts);
            return mv;
        }
            ModelAndView mv = new ModelAndView("postsAutenticado");
            mv.addObject("postsAutenticado", posts);
            return mv;
    }

    @RequestMapping(value ="/deletarPost")
    public String deletePost(long id){
        Post post = codeblogService.findById(id);
        codeblogService.deleteById(post.getId());
        return "redirect:/posts";
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = codeblogService.findById(id);
        mv.addObject("post", post);
        return mv;
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String getPostForm(){
        return "postform";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem","verifique se os campos foram preenchidos corretamente!");
            return "redirect:/newpost";
        }
        post.setData(LocalDate.now());
        codeblogService.save(post);
        return "redirect:/posts";
    }

}
