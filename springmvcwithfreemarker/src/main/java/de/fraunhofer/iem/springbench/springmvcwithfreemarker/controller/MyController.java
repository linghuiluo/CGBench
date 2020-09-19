package de.fraunhofer.iem.springbench.springmvcwithfreemarker.controller;

import de.fraunhofer.iem.springbench.springmvcwithfreemarker.model.OrderedItemsInformationDisplayModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MyController {

    @ModelAttribute("userName")
    public String newUser(HttpServletRequest request) {
        return request.getParameter("userName");
    }

    @ModelAttribute
    public void populateCitiesAndLanguages(@ModelAttribute("userName") String userName,
                                           Model model) {
        OrderedItemsInformationDisplayModel orderedItemsInformationDisplayModel = new OrderedItemsInformationDisplayModel();
        orderedItemsInformationDisplayModel.setUserName(userName);
        if (userName != null) {
            orderedItemsInformationDisplayModel.setTime(
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())
            );
            orderedItemsInformationDisplayModel.setMoneyFormat("Euro");

            model.addAttribute("orderedDisplayInformation", new OrderedItemsInformationDisplayModel());
        }

    }

    @RequestMapping("/")
    public String printOrderedItemsInformation(@ModelAttribute("orderedDisplayInformation") OrderedItemsInformationDisplayModel orderedItemsInformationDisplayModel,
                                               Model model) {
        String userName = orderedItemsInformationDisplayModel.getUserName();

        if (userName == null) {
            model.addAttribute("orderedItemInformation", "Invalid user name");
            return "OrderedItemsInformation";
        } else {
            ResultSet res;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mysql";
                Connection conn = DriverManager.getConnection(url, "", "");
                Statement st = conn.createStatement();
                String query = "SELECT * FROM  OrderInformation where userName='" + userName + "'";

                res = st.executeQuery(query);

                model.addAttribute("orderedItemInformation", res.toString());
            } catch (SQLException | ClassNotFoundException e) {
                model.addAttribute("orderedItemInformation", "Something went wrong while retrieving the data. Please try after some time.");
                return "OrderedItemsInformation";
            }
        }
        return "OrderedItemsInformation";
    }
}
