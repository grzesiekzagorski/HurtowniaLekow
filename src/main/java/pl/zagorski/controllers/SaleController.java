package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.zagorski.services.EmployeeServiceImpl;

@Controller
public class SaleController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @RequestMapping(value = "/sale/allSales", method = RequestMethod.GET)
    public String findAllSales(Model model) {
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        return "allSales";
    }
}
