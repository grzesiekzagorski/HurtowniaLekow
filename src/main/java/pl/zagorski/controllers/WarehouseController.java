package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zagorski.services.DeliveryServiceImpl;
import pl.zagorski.services.EmployeeServiceImpl;
import pl.zagorski.services.PurchaseOrderServiceImpl;
import pl.zagorski.services.WarehouseServiceImpl;

@Controller
public class WarehouseController {

    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    WarehouseServiceImpl warehouseService;

    @RequestMapping(value ="/warehouse/allWarehouses",method = RequestMethod.GET)
    public String findAllDeliveriesInWarehouse(Model model) {
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("warehouses",warehouseService.showWarehouseWhereStatusEqualsInStockOrOnSold());
        return "allWarehouses";
    }

    @RequestMapping(value ="/warehouse/allWarehouses",params = "idSearch", method = RequestMethod.POST)
    public String findDeliveryInWarehouse(Model model, String idSearch, String nameSearch){
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("warehouses",warehouseService.showAllWarehousesOrderByIdOrMedicineName(idSearch,nameSearch));
        return "allWarehouses";
    }
}
