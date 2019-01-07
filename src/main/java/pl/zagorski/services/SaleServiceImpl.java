package pl.zagorski.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zagorski.domain.Sale;
import pl.zagorski.repositories.SaleDao;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleImpl {

    @Autowired
    SaleDao saleDao;


    @Override
    @Transactional
    public void save(Sale sale) {
        saleDao.save(sale);
    }

    @Override
    @Transactional
    public void edit(Sale sale) {
        saleDao.edit(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleDao.findAll();
    }

    @Override
    public Sale findOne(int id) {
        return saleDao.findOne(id);
    }

    @Override
    public List<Object[]> showAllSales() {
        return saleDao.showAllSales();
    }

    @Override
    public List<Object[]> showAllSalesOrderByMedicineName() {
        return saleDao.showAllSalesOrderByMedicineName();
    }

    @Override
    public List<Object[]> showAllSalesOrderBySaleAmount() {
        return saleDao.showAllSalesOrderBySaleAmount();
    }

    @Override
    public List<Object[]> showSalesByMedicineName(String name) {
        return saleDao.showSalesByMedicineName(name);
    }

    @Override
    public List<Object[]> showSaleById(int id) {
        return saleDao.showSaleById(id);
    }
}
