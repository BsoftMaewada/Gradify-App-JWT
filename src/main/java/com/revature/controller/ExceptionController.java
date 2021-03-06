package com.revature.controller;

import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

import javax.security.auth.login.FailedLoginException;

public class ExceptionController implements  Controller {


    private ExceptionHandler<FailedLoginException> failedlogin = (exception, ctx ) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.exception(FailedLoginException.class, failedlogin);

    }


}
