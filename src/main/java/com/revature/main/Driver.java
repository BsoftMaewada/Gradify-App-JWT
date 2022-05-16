package com.revature.main;

import com.revature.controller.AssignmentController;
import com.revature.controller.AuthenticationController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import com.revature.dao.UserDao;
import com.revature.utility.ConnectionUtility;
import io.javalin.Javalin;

import javax.tools.JavaFileObject;
import java.sql.SQLException;

public class Driver {

    public static void main(String [] args) {
        //To first check connection to the database
//        try {
//            ConnectionUtility.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            ;
//        }

//        //Testing the UserDao
//        UserDao dao = new UserDao();
//        try{
//            System.out.println(dao.getUserByUsernameAndPassword("bach_tran","password123"));
//        } catch (SQLException e){
//            e.printStackTrace();
//        }


        Javalin app = Javalin.create();

        map(app, new AuthenticationController(), new ExceptionController(), new AssignmentController());

        app.start(8080);
    }

    public static void map(Javalin app, Controller... controllers){
        for (Controller c : controllers){
            c.mapEndpoints(app);
        }
    }
}
