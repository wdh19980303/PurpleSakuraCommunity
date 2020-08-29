package purple.sakura.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import purple.sakura.community.model.Message;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {


    @GetMapping("/")
    public String profileIndex() {



        return "profile";
    }


    @GetMapping("/replies" )
    @ResponseBody
    public Message<Map<String, Object>> replies() {
        Message<Map< String, Object>> message = new Message<>();
        Map<String ,Object > messageMap = new HashMap<>();
        messageMap.put("count",12);
        message.setModel(messageMap);
        return message;
    }


}
