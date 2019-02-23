package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.services.EmployeeServiceImpl;
import pl.zagorski.services.SaleServiceImpl;

@Controller
public class SaleController {

    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    SaleServiceImpl saleService;
    @Autowired
    ErrorController errorController;

    @RequestMapping(value = "/sale/allSales", method = RequestMethod.GET)
    public String findAllSalesTransactions(Model model) {
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("sales",saleService.showAllSales());
        return "allSales";
    }

    @RequestMapping(value ="/sale/allSales",params = "idSearch", method = RequestMethod.POST)
    public String findSaleTransaction(Model model, String idSearch, String nameSearch){
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("sales",saleService.showSaleByIdOrMedicineName(idSearch,nameSearch));
        return "allSales";
    }


    @RequestMapping(value ="/sale/allSales",params = "idDeleteTransactionSale", method = RequestMethod.POST)
    public String deleteSaleTransaction(Model model, String idDeleteTransactionSale){
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        try {
            saleService.delete(Integer.parseInt(idDeleteTransactionSale));
        } catch (ExceptionSample | NumberFormatException e) {
            return errorController.redirectToErrorPage(model);
        }
        return findAllSalesTransactions(model);
    }

}
