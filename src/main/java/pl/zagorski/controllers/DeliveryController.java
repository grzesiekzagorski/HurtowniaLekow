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

@Controller
public class DeliveryController {

    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    DeliveryServiceImpl deliveryService;
    @Autowired
    PurchaseOrderServiceImpl purchaseOrderService;

    @RequestMapping(value ="/delivery/allDeliveries",method = RequestMethod.GET)
    public String findAllDeliveries(Model model) {
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("deliveryOnWarehouse",deliveryService.showDeliveryWhereItIsNotForSale());
        model.addAttribute("ordersNotDelivered",purchaseOrderService.showAllPurchaseOrdersThatAreNotDelivered());
        model.addAttribute("deliveries",deliveryService.showAllDeliveries());
        return "allDeliveries";
    }

    @RequestMapping(value ="/delivery/allDeliveries",params = "idOrderAddDelivery", method = RequestMethod.POST)
    public String addMedicine(Model model,@RequestParam int idOrderAddDelivery, @RequestParam String expirationDate){
        deliveryService.save(expirationDate,idOrderAddDelivery,PurchaseOrderController.findLoggedUser());
        return findAllDeliveries(model);
    }
    @RequestMapping(value ="/delivery/allDeliveries",params = "idSearch", method = RequestMethod.POST)
    public String findDelivery(Model model, String idSearch, String nameSearch){
        model.addAttribute("deliveries",deliveryService.showDeliveryByIdOrName(idSearch,nameSearch));
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("deliveryOnWarehouse",deliveryService.showDeliveryWhereItIsNotForSale());
        model.addAttribute("ordersNotDelivered",purchaseOrderService.showAllPurchaseOrdersThatAreNotDelivered());
        return "allDeliveries";
    }

    @RequestMapping(value ="/delivery/allDeliveries",params = "idDeliveryDelete", method = RequestMethod.POST)
    public String deleteDelivery(Model model,@RequestParam int idDeliveryDelete) {
        deliveryService.delete(idDeliveryDelete);
        return findAllDeliveries(model);
    }




}
