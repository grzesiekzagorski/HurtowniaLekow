package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zagorski.services.*;

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
    public String addOrder(Model model, @RequestParam int idMedicine, @RequestParam int idSupplier,
                           @RequestParam int amount) {
        purchaseOrderService.save(idMedicine, idSupplier, amount, PurchaseOrderController.findLoggedUser());
        return findAllOrders(model);
    }

    @RequestMapping(value = "/orders/allOrders", params = "idOrderEdit", method = RequestMethod.POST)
    public String editOrder(Model model, @RequestParam int idOrderEdit, @RequestParam int idMedicineEdit, @RequestParam int idSupplierEdit,
                            @RequestParam int amountEdit) {
        purchaseOrderService.edit(idOrderEdit, idMedicineEdit, idSupplierEdit, amountEdit, PurchaseOrderController.findLoggedUser());
        return findAllOrders(model);
    }

    @RequestMapping(value = "/orders/allOrders", params = "idOrderDelete", method = RequestMethod.POST)
    public String editOrder(Model model, @RequestParam int idOrderDelete) {
        purchaseOrderService.delete(idOrderDelete);
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
