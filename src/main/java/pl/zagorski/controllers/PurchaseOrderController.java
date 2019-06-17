package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.services.EmployeeServiceImpl;
import pl.zagorski.services.MedicineServiceImpl;
import pl.zagorski.services.PurchaseOrderServiceImpl;
import pl.zagorski.services.SupplierServiceImpl;

@Controller
public class PurchaseOrderController {

    @Autowired
    EmployeeServiceImpl employeeService;
    @Autowired
    PurchaseOrderServiceImpl purchaseOrderService;
    @Autowired
    MedicineServiceImpl medicineService;
    @Autowired
    SupplierServiceImpl supplierService;
    @Autowired
    ErrorController errorController;


    public static String findLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();

        } else {
            username = principal.toString();
        }
        return username;
    }

    @RequestMapping(value = "/orders/allOrders", method = RequestMethod.GET)
    public String findAllOrders(Model model) {
        model.addAttribute("orders", purchaseOrderService.showAllPurchaseOrders());
        model.addAttribute("ordersNotDelivered", purchaseOrderService.showAllPurchaseOrdersThatAreNotDelivered());
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("medicines", medicineService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "allOrders";
    }

    @RequestMapping(value = "/orders/allOrders", params = "idMedicine", method = RequestMethod.POST)
    public String addOrder(Model model, @RequestParam String idMedicine, @RequestParam String idSupplier,
                           @RequestParam int amount) {
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        try {
            purchaseOrderService.save(Integer.parseInt(idMedicine),Integer.parseInt(idSupplier),amount,PurchaseOrderController.findLoggedUser());
        } catch (ExceptionSample | NumberFormatException e) {
           return errorController.redirectToErrorPage(model);
        }
        return findAllOrders(model);
    }

    @RequestMapping(value = "/orders/allOrders", params = "idOrderEdit", method = RequestMethod.POST)
    public String editOrder(Model model, @RequestParam String idOrderEdit, @RequestParam String idMedicineEdit, @RequestParam String idSupplierEdit,
                            @RequestParam int amountEdit) {
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        try {
            purchaseOrderService.edit(Integer.parseInt(idOrderEdit),Integer.parseInt(idMedicineEdit),Integer.parseInt(idSupplierEdit),amountEdit, PurchaseOrderController.findLoggedUser());
        } catch (ExceptionSample | NumberFormatException e) {
            return errorController.redirectToErrorPage(model);
        }
        return findAllOrders(model);
    }

    @RequestMapping(value = "/orders/allOrders", params = "idOrderDelete", method = RequestMethod.POST)
    public String deleteOrder(Model model, @RequestParam String idOrderDelete) {
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        try {
            purchaseOrderService.delete(Integer.parseInt(idOrderDelete));
        } catch (ExceptionSample | NumberFormatException e) {
            return errorController.redirectToErrorPage(model);
        }
        return findAllOrders(model);
    }

    @RequestMapping(value = "/orders/allOrders", params = "idSearch", method = RequestMethod.POST)
    public String findOrder(Model model, String idSearch, String nameSearch) {
        model.addAttribute("orders", purchaseOrderService.showPurchaseOrderByIdOrName(idSearch, nameSearch));
        if (purchaseOrderService.showPurchaseOrderByIdOrName(idSearch, nameSearch).size() != 0) {
            model.addAttribute("ordersNotDelivered", purchaseOrderService.showAllPurchaseOrdersThatAreNotDelivered());
        }
        model.addAttribute("user", employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        model.addAttribute("medicines", medicineService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "allOrders";
    }


}
