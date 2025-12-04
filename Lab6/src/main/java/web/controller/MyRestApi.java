package web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // [Ghi chú 1]: Đánh dấu là REST Controller
public class MyRestApi { // [Ghi chú 2]: Tên Class theo hướng dẫn

    // [Ghi chú 3]: Xử lý GET /poly/url0 và root path /
    @GetMapping({"/poly/url0", "/"})
    public Object method0(){
        // Trả về: {url:"/poly/url0", method:"method0()"} [cite: 8, 16]
        return Map.of("url", "/poly/url0", "method", "method0()");
    }

    // [Ghi chú 4]: Xử lý GET /poly/url1
    @GetMapping("/poly/url1")
    public Object method1(){
        // Trả về: {url:"/poly/urll", method:"method1()"} [cite: 8, 20]
        return Map.of("url", "/poly/url1", "method", "method1()");
    }

    // [Ghi chú 5]: Xử lý GET /poly/url2
    @GetMapping("/poly/url2")
    public Object method2(){
        // Trả về: {url:"/poly/url2", method:"method2()"} [cite: 8, 24]
        return Map.of("url", "/poly/url2", "method", "method2()");
    }

    // [Ghi chú 6]: Xử lý GET /poly/url3
    @GetMapping("/poly/url3")
    public Object method3(){
        // Trả về: {url:"/poly/url3", method:"method3()"} [cite: 8, 31]
        return Map.of("url", "/poly/url3", "method", "method3()");
    }

    // [Ghi chú 7]: Xử lý GET /poly/url4
    @GetMapping("/poly/url4")
    public Object method4(){
        // Trả về: {url:"/poly/url4", method:"method4()"} [cite: 8, 34]
        return Map.of("url", "/poly/url4", "method", "method4()");
    }
}