package purple.sakura.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import purple.sakura.community.model.CommunityUser;
import purple.sakura.community.service.CommunityUserService;

@Controller
@SessionAttributes("user")
public class TestLogin {
    @Autowired
    private CommunityUserService communityUserService;

    @RequestMapping("/tl")
    public ModelAndView login( ModelAndView modelAndView) {
        CommunityUser communityUser = communityUserService.selectByAccountID("46125951");
        modelAndView.addObject("user",communityUser);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
