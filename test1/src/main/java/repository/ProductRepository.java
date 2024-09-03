package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Product;

import java.util.List;

public class ProductRepository {
    private static HikariDataSource dataSource = HikariCP.createDataSource();

    public List<Product> selectproduct(){


    }
}
