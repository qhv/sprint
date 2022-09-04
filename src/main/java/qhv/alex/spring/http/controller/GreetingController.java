package qhv.alex.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qhv.alex.spring.dto.UserReadDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RequestMapping("qq")
@SessionAttributes("user")
@Controller
public class GreetingController {

    @GetMapping("/hello")
    public String  hello(Model model, UserReadDto userReadDto) {
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                              HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable("id") Integer id) {

        var ageValue = request.getParameter("age");
        var acceptValue = request.getHeader("accept");
        var cookies = request.getCookies();
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public String bye(ModelAndView modelAndView,
                            @SessionAttribute("user") UserReadDto user) {
        return "greeting/bye";
    }
}
