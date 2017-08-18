package com.assetcontrol.controller;

import com.assetcontrol.model.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/*use @Controller when methods return Views and send html in response
* use @RestController when domain objects are returned converted into some format
* (so no more ViewResolvers) */
@Controller
public class PollController {

    @Value("${polling.interval}")
    String interval;

    @Autowired
    Results results;

    @RequestMapping("/")
    String exposeResults(ModelMap model) {

        model.addAttribute("errors", results.getErrorCount());
        model.addAttribute("warnings", results.getWarningCount());
        model.addAttribute("infos", results.getInfoCount());
        model.addAttribute("interval", interval);
        return "results";
    }
}