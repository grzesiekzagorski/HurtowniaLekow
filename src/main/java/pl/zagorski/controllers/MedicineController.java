package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zagorski.services.CharacterServiceImpl;
import pl.zagorski.services.MedicineServiceImpl;
import pl.zagorski.services.PrescritpionServiceImpl;
import pl.zagorski.services.ProducerServiceImpl;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineServiceImpl medicineService;
    @Autowired
    private CharacterServiceImpl characterService;
    @Autowired
    private PrescritpionServiceImpl prescriptionService;
    @Autowired
    private ProducerServiceImpl producerService;

    @GetMapping("/allMedicines")
    public String orderByName(Model model) {
        model.addAttribute("medicines",medicineService.findAll());
        model.addAttribute("characters",characterService.findAll());
        model.addAttribute("prescriptions",prescriptionService.findAll());
        model.addAttribute("producers",producerService.findAll());
        return "homeMedicine";
    }

}
