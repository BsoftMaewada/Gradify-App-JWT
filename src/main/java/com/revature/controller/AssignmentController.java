package com.revature.controller;

import com.revature.model.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

public class AssignmentController implements Controller{

    private Handler test = (ctx) -> {

        User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("current_user");

        if(currentlyLoggedInUser != null){
            ctx.json(currentlyLoggedInUser);
        } else {
            ctx.json("No user is logged in!");
        }
//        System.out.println(currentlyLoggedInUser);
    };

    //This endpoints should only be accessible by a trainers
    private Handler getAllAssignments = (ctx) -> {
        User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("current_user");

        if(currentlyLoggedInUser != null && currentlyLoggedInUser.getUserRole().equals("trainer")){

            //Execute code inside of here if you are a trainer
            ctx.json("some stuff happening for the endpoints ");

        } else {
//            ctx.status(401);
//            ctx.json("You are either not log in or not authorized to access this endpoints.");
            throw new UnauthorizedResponse("You are not authorized to use the /accounts endpoints.");
        }
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.get("/test", test);
        app.get("/assignments", getAllAssignments);
    }
}
