package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("message","Home");
        return "page";
    }

    @RequestMapping("/poly/url0")
    public String method(Model model){
        model.addAttribute("message","url0 => method0");
    return  "page";
    }

    @RequestMapping("/poly/url1")
    public String method1(Model model){
        model.addAttribute("message","url1 => method1");
        return  "page";
    }

    @RequestMapping("/poly/url2")
    public String method2(Model model){
        model.addAttribute("message","url2 => method2");
        return  "page";
    }

    @RequestMapping("/poly/url3")
    public String method3(Model model){
        model.addAttribute("message","url3 => method3");
        return  "page";
    }
}
