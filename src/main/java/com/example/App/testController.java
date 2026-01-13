package com.example.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class testController {

    @GetMapping("/")
    public String home() {
        return "NewFile";   // loads NewFile.html
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String username,
                         @RequestParam String password,
                         Model model) {

        try {
            String url = "jdbc:mysql://localhost:3306/kiran";
            String dbUser = "root";
            String dbPass = "Kiran@2006";

            Connection con = DriverManager.getConnection(url, dbUser, dbPass);

            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);

            pst.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("name", username);
        return "success";
    }
}
