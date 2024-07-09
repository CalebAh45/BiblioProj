package Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/homes")
public class HomeController {


    @GetMapping("")
    public String homes() {
        return "templates/home";
    }
}
