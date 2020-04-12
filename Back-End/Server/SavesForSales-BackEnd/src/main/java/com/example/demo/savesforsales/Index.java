
package com.example.demo.savesforsales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.services.Services;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author German le yo
 */
@SpringBootApplication(scanBasePackages="com.example.demo.controllers")
@Controller
@CrossOrigin
public class Index {

    public static boolean REMOTE = true;
    
    @RequestMapping("/")
    @ResponseBody
    String home() {
      return "Bienvenido al servidor de backend de SavesForSales";
    }

    public static void main(String[] args){
       Services.startServices();
       SpringApplication.run(Index.class, args);
    }
}
