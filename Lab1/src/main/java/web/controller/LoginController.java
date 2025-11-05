package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("messages", "Vui lòng đăng nhập");
        return "login-form";
    }

    @GetMapping("/success")
    public String success(Model model) {
        model.addAttribute("messages", "Đăng nhập thành công");
        return "login-form";
    }

    @GetMapping("/failure")
    public String fail(Model model) {
        model.addAttribute("messages", "Đăng nhập thất bại");
        return "login-form";
    }

    @GetMapping("/exit")
    public String exit(Model model) {
        model.addAttribute("messages", "Đăng xuất thành công");
        return "login-form";
    }
}
