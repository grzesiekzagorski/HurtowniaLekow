package pl.zagorski.repositories;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Sale;
import pl.zagorski.domain.Warehouse;

import java.util.List;

public interface SaleDao {
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
