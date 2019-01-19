package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zagorski.domain.Medicine;
import pl.zagorski.services.CharacterServiceImpl;
import pl.zagorski.services.MedicineServiceImpl;
import pl.zagorski.services.PrescriptionServiceImpl;
import pl.zagorski.services.ProducerServiceImpl;

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

    @RequestMapping(value ="/medicine/allMedicines",method = RequestMethod.GET)
    public String findAllMedicines(Model model) {
        model.addAttribute("medicines", medicineService.showAllMedicines());
        model.addAttribute("characters", characterService.findAll());
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("prescriptions", prescriptionService.findAll());
        return "allMedicines";
    }

    @RequestMapping(value ="/medicine/allMedicines",params = "idPrescription", method = RequestMethod.POST)
    public String addMedicine(Model model, @RequestParam int idPrescription, @RequestParam int idCharacter,
                            @RequestParam int idProducer, @RequestParam String name,
                            @RequestParam double price, @RequestParam double discount,
                            @RequestParam String portion, @RequestParam String wrapping) {
        medicineService.save(idPrescription,idCharacter,idProducer,name,price,discount,portion,wrapping);
        findAllMedicines(model);
        return "allMedicines";
    }

    @RequestMapping(value ="/medicine/foundMedicines", method = RequestMethod.POST)
    public String findMedicine(Model model, String idSearch, String nameSearch){
        model.addAttribute("medicines",medicineService.showMedicineByIdOrName(idSearch,nameSearch));
        return "foundMedicines";
    }


}