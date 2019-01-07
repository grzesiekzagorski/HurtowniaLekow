package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Sale;

import java.util.List;

public interface SaleImpl {
    void save(Sale sale);
    void edit(Sale sale);
    List<Sale> findAll();
    Sale findOne(int id);
    List<Object[]> showAllSales();
    List<Object[]> showAllSalesOrderByMedicineName();
    List<Object[]> showAllSalesOrderBySaleAmount();
    List<Object[]> showSalesByMedicineName(@Param("name") String name);
    List<Object[]> showSaleById(@Param("id") int id);


}
