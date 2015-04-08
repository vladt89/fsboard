package main.java.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class MessageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getThis(Map<String, String> map) {
        map.put("message", "Hello WorldX");
        return "hello";
    }
}
