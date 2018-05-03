package com.haka.stepup.controllers;

import com.haka.stepup.connectors.nokia.HealthRequests;
import com.haka.stepup.db.User;
import com.haka.stepup.domain.Steps;
import com.haka.stepup.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@RestController
@EnableAutoConfiguration

public class MainController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HealthRequests healthRequests = new HealthRequests();

    @GetMapping("/steps")
    @ResponseBody
    public Steps getSteps(@RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) throws IOException {
        System.out.println("getSteps");
        return new Steps(healthRequests.getBodyMeasures().getBody().getActivities().get(0).getSteps());
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public ModelAndView authenticate() throws IOException {

        long unixTime = System.currentTimeMillis() / 1000L;
        String authorize = healthRequests.authorize();

        return new ModelAndView("redirect:" + authorize);//"https://developer.health.nokia.com/account/authorize?&oauth_consumer_key="+oAuthConsumer.getConsumerKey()+"\n" +

    }

    @GetMapping("/authentication")
    @ResponseBody
    public String authenticate2(@RequestParam(name="userid", required=true, defaultValue = "") String userId, @RequestParam(name="oauth_token", required=true, defaultValue = "") String authToken) throws IOException {
        String out = healthRequests.getAuthorizationToken(userId);
        return out;


    }

    @GetMapping("/user")
    @ResponseBody
    public User user(@RequestParam(name="id", required=true, defaultValue = "") long id) throws IOException {
        return userService.findUser(id);
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() throws IOException {
        return userService.findAll();


    }

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    @ResponseBody
    public String registerUserAccount(
            @RequestBody User user, HttpServletRequest request) {

        logger.debug("Registering user account with information: {}", user);
        userService.create(user);
////        if (registered == null) {
////            throw new UserAlreadyExistException();
////        }
//        String appUrl = "http://" + request.getServerName() + ":" +
//                request.getServerPort() + request.getContextPath();

//        eventPublisher.publishEvent(
//                new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));

        return "success";
    }
}
