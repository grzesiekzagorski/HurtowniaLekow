package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zagorski.domain.Employee;
import pl.zagorski.exceptions.ExceptionSample;
import pl.zagorski.services.*;

@Controller
public class MedicineController {

    @Autowired
    private MedicineServiceImpl medicineService;
    @Autowired
    private PrescriptionServiceImpl prescriptionService;
    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private ProducerServiceImpl producerService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private ErrorController errorController;


    @RequestMapping(value ="/medicine/allMedicines",method = RequestMethod.GET)
    public String findAllMedicines(Model model) {
        model.addAttribute("medicines", medicineService.showAllMedicines());
        model.addAttribute("medicinesNotOrdered",medicineService.showMedicinesThatAreNotOrdered());
        model.addAttribute("characters", characterService.findAll());
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("prescriptions", prescriptionService.findAll());
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        return "allMedicines";
    }

    @RequestMapping(value ="/medicine/allMedicines",params = "idPrescription", method = RequestMethod.POST)
    public String addMedicine(Model model, @RequestParam int idPrescription, @RequestParam int idCharacter,
                            @RequestParam int idProducer, @RequestParam String name,
                            @RequestParam double price, @RequestParam int discount,
                            @RequestParam String portion, @RequestParam String wrapping) {
        medicineService.save(idPrescription,idCharacter,idProducer,name,price,discount,portion,wrapping);
        return findAllMedicines(model);
    }

    @RequestMapping(value ="/medicine/allMedicines",params = "idMedicineEdit", method = RequestMethod.POST)
    public String editMedicine(Model model,@RequestParam int idMedicineEdit,@RequestParam int idPrescriptionEdit, @RequestParam int idCharacterEdit,
                              @RequestParam int idProducerEdit, @RequestParam String nameEdit,
                              @RequestParam double priceEdit, @RequestParam int discountEdit,
                              @RequestParam String portionEdit, @RequestParam String wrappingEdit) {
        medicineService.edit(idMedicineEdit,idPrescriptionEdit,idCharacterEdit,idProducerEdit,nameEdit,priceEdit,discountEdit,portionEdit,wrappingEdit);
        return findAllMedicines(model);
    }

    @RequestMapping(value ="/medicine/allMedicines",params = "idSearch", method = RequestMethod.POST)
    public String findMedicine(Model model, String idSearch, String nameSearch){
        model.addAttribute("medicines",medicineService.showMedicineByIdOrName(idSearch,nameSearch));
        model.addAttribute("medicinesNotOrdered",medicineService.showMedicinesThatAreNotOrdered());
        model.addAttribute("characters", characterService.findAll());
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("prescriptions", prescriptionService.findAll());
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        return "allMedicines";
    }
    @RequestMapping(value ="/medicine/allMedicines",params = "idMedicineDelete", method = RequestMethod.POST)
    public String deleteMedicine(Model model,@RequestParam String idMedicineDelete) {
        model.addAttribute("user",employeeService.getEmployeeByLogin(PurchaseOrderController.findLoggedUser()).get());
        try {
            medicineService.delete(Integer.parseInt(idMedicineDelete));
        } catch (ExceptionSample  | NumberFormatException e) {
            return errorController.redirectToErrorPage(model);
        }
        return findAllMedicines(model);
    }



}
