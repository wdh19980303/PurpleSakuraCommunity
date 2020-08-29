package purple.sakura.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WebsiteComponentController {

    @GetMapping("/head")
    public String  head() {
        return "component/head";
    }


    @GetMapping("/ti")
    @ResponseBody
    public String index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.getRequestDispatcher("/").forward(req,res);
        return  null;
    }
}

