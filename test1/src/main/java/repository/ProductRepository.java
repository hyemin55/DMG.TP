package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static Logger LOG = LoggerFactory.getLogger(ProductRepository.class);

    private static HikariDataSource dataSource = HikariCP.createDataSource();

    public List<Product> selectproduct(){

        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            LOG.info("DB connection success");
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("select * from product");
            ResultSet rs = pstmt.executeQuery();

            List<Product> list = new ArrayList<>();
            while(rs.next()){
                Product product = new Product();
                product.setP_id(rs.getInt("p_id"));
                product.setP_brand(rs.getString("p_brand"));
                product.setP_kind(rs.getString("p_kind"));
                product.setP_name(rs.getString("p_name"));
                product.setP_conutry(rs.getString("p_conutry"));
                product.setP_capacity(rs.getString("p_capacity"));
                product.setP_etc(rs.getString("p_etc"));

                list.add(product);
            } return list;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
