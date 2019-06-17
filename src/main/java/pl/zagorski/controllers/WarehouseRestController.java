package pl.zagorski.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.domain.WarehouseRest;
import pl.zagorski.services.WarehouseServiceImpl;

import java.util.List;

@RestController
public class WarehouseRestController {

    @Autowired
    private WarehouseServiceImpl warehouseService;

    @RequestMapping(value = "/warehouse/getRestWarehouse", method = RequestMethod.GET)
    public List<WarehouseRest> getWarehouses() {
        return warehouseService.showWarehouseRestObjects();
    }

}
