package pl.zagorski.services;


import org.springframework.data.repository.query.Param;
import pl.zagorski.domain.Sale;
import pl.zagorski.domain.Warehouse;

import java.util.List;

public interface SaleImpl {
    void save(int amount,int idClient,String userLogin,Warehouse warehouse);
    void edit(Sale sale);
    List<Sale> findAll();
    Sale findOne(int id);
    List<String[]> showAllSales();
    List<String[]> showAllSalesOrderByMedicineName();
    List<String[]> showAllSalesOrderBySaleAmount();
    List<String[]> showSalesByMedicineName(@Param("name") String name);
    List<String[]> showSaleById(@Param("id") int id);
    List<String[]> convertObjectListToStringList(List<Object[]> objects);
    List<String[]> showSaleByIdOrMedicineName(@Param("id")String id,@Param("id")String name);


}
