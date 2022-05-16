package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import javax.servlet.http.HttpSession;

public class AuthenticationController implements Controller{

    private UserService userService;

    public AuthenticationController(){
        this.userService = new UserService();
    }

    //define endpoint
    private Handler login = (ctx) -> {
        //To test if everything is working
        System.out.println("Login Endpoint invoke");

//        User loginInfo = ctx.bodyAsClass(User.class);

        LoginDTO loginInfo = ctx.bodyAsClass(LoginDTO.class);

        User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());
        //if FailedLoginException did not occur, then we will move on to the subsquent lines of code
        //code down here will not run if FailedException happened from the .login(...., ...) method

        HttpSession session = ctx.req.getSession(); //ctx.req is an HttpServletRequest
        //Behind the scenes, Javalin utilizes Java EE servlets to handle HTTP requests. We're making use of the
        //Servlet API to create an HttpSession.

        session.setAttribute("current_user", user); //Set the User object to this session

        //send back the json
        ctx.json(user);

//        System.out.println(loginInfo);
    };

    private  Handler logout = (ctx) -> {
      HttpSession session = ctx.req.getSession();

      session.invalidate(); // Invalidate the HttpSession on the backend
    };


    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
        app.post("/logout", logout);
    }

}
