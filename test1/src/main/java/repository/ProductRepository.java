package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends JDBCTemplate{
    private static HikariDataSource dataSource = HikariCP.createDataSource();

    public void selectproduct() {
    execute("select * from product");
    }

    @Override
    protected void handleResultSet(ResultSet rs) throws SQLException {
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Product product = new Product();
                product.setP_id(rs.getInt("p_id"));
                product.setP_kind(rs.getString("p_kind"));
                product.setP_brand(rs.getString("p_brand"));
                product.setP_name(rs.getString("p_name"));
                product.setP_conutry(rs.getString("p_country"));
                product.setP_capacity(rs.getString("p_capacity"));
                product.setP_amount(rs.getInt("p_amount"));
                product.setP_etc(rs.getString("p_etc"));
                list.add(product);
            }
            list.stream().forEach(System.out::println);
    }

    @Override
    protected void setParameter(PreparedStatement pstmt) {

    }
}
