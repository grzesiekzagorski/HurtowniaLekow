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

import javax.validation.Valid;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineServiceImpl medicineService;
    @Autowired
    private PrescriptionServiceImpl prescriptionService;
    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private ProducerServiceImpl producerService;

    @GetMapping("/allMedicines")
    public String findAllMedicines(Model model) {
        model.addAttribute("medicines", medicineService.showAllMedicines());
        model.addAttribute("characters", characterService.findAll());
        model.addAttribute("producers", producerService.findAll());
        model.addAttribute("prescriptions", prescriptionService.findAll());
        return "allMedicines";
    }

    @PostMapping("/allMedicines")
    public String addMedicine(Model model, @RequestParam int idPrescription, @RequestParam int idCharacter,
                            @RequestParam int idProducer, @RequestParam String name,
                            @RequestParam double price, @RequestParam double discount,
                            @RequestParam String portion, @RequestParam String wrapping) {
        Medicine medicine = new Medicine();
        medicine.setPrescription(prescriptionService.findOne(idPrescription));
        medicine.setCharacter(characterService.findOne(idCharacter));
        medicine.setProducer(producerService.findOne(idProducer));
        medicine.setName(name);
        medicine.setPrice(price);
        medicine.setDiscount(discount);
        medicine.setPortion(portion);
        medicine.setWrapping(wrapping);
        medicineService.save(medicine);
        findAllMedicines(model);
        return "allMedicines";

    }


}
