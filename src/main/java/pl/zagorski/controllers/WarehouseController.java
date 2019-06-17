package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zagorski.domain.Employee;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.services.*;

@Controller
public class WarehouseController {

    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    WarehouseServiceImpl warehouseService;
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    ErrorController errorController;

    @RequestMapping(value ="/warehouse/allWarehouses",method = RequestMethod.GET)
    public String findAllDeliveriesInWarehouse(Model model) {
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("warehouses",warehouseService.showWarehouseWhereStatusEqualsInStockOrOnSold());
        model.addAttribute("clients",clientService.findAll());
        return "allWarehouses";
    }

    @RequestMapping(value ="/warehouse/allWarehouses",params = "idSearch", method = RequestMethod.POST)
    public String findDeliveryInWarehouse(Model model, String idSearch, String nameSearch){
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("warehouses",warehouseService.showAllWarehousesOrderByIdOrMedicineName(idSearch,nameSearch));
        model.addAttribute("clients",clientService.findAll());
        return "allWarehouses";
    }

    @RequestMapping(value = "/warehouse/allWarehouses", params ="idClient", method = RequestMethod.POST)
    public String addSale(Model model,@RequestParam String idWarehouse,@RequestParam String idClient,@RequestParam int amount) {
        Employee employee =  employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get();
        model.addAttribute("user",employee);
        try {
            warehouseService.sell(amount,Integer.parseInt(idClient),employee.getLogin(),Integer.parseInt(idWarehouse));
        } catch (ExceptionSample | NumberFormatException e) {
            return errorController.redirectToErrorPage(model);
        }
        return findAllDeliveriesInWarehouse(model);
    }
}
