package com.spring.codeblog.controller;

import com.spring.codeblog.model.Work;
import com.spring.codeblog.service.WorkblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WorkblogController {

    @Autowired
    WorkblogService workblogService;

    @RequestMapping(value = "/carreira", method = RequestMethod.GET)
    public ModelAndView getWorks(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Work> works = workblogService.findAll();

        if(!(principal instanceof UserDetails)){
            ModelAndView mv = new ModelAndView("carreira");
            mv.addObject("works", works);
            return mv;
        }
        ModelAndView mv = new ModelAndView("carreiraAutenticado");
        mv.addObject("works", works);
        return mv;
    }

    @RequestMapping(value = "/carreira/{id}", method = RequestMethod.GET)
    public ModelAndView getCarreiraDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("carreiraDetails");
        Work work = workblogService.findById(id);
        mv.addObject("work", work);
        return mv;
    }

    @RequestMapping(value = "/newwork", method = RequestMethod.GET)
    public String getWorkForm(){return "carreiraAdicionar";}

    @RequestMapping(value = "/newwork", method = RequestMethod.POST)
    public String saveWork(@Valid Work work, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "verifique se os campos foram preenchidos corretamente!");
            return "redirect:/newwork";
        }
        workblogService.save(work);
        return "redirect:/carreira";
    }

    @RequestMapping(value = "/deletarWork")
    public String deleteWork(long id){
        Work work = workblogService.findById(id);
        workblogService.deleteById(work.getId());
        return "redirect:/carreira";
    }



}
