package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("message", "@/ => home()");
        return "page";
    }
    @RequestMapping("/poly/url0")
    public String method(Model model){
        model.addAttribute("message","KHông cần đăng nhập");
        return "page";
    }
    @RequestMapping("/poly/url1")
    public String method1(Model model){
        model.addAttribute("message", "@/poly/url1 => Authenticated");
        return "page";
    }

    @RequestMapping("/poly/url2")
    public String method2(Model model){
        model.addAttribute("message", "@/poly/url2 => USER");
        return "page";
    }

    @RequestMapping("/poly/url3")
    public String method3(Model model){
        model.addAttribute("message", "@/poly/url3 => ADMIN");
        return "page";
    }
    @RequestMapping("/poly/url4")
    public String method4(Model model){
        model.addAttribute("message", "@/poly/url4 =>USER-ADMIN");
        return "page";

    }
}
