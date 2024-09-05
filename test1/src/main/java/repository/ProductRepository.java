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

public class ProductRepository {
    private static Logger LOG = LoggerFactory.getLogger(ProductRepository.class);

    private static HikariDataSource dataSource = HikariCP.createDataSource();

<<<<<<< HEAD
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
=======
    public List<Product> selectproduct() {
        try (Connection connection = dataSource.getConnection();
        ) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = pstmt.executeQuery();
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
>>>>>>> main
                Product product = new Product();
                product.setP_id(rs.getInt("p_id"));
                product.setP_brand(rs.getString("p_brand"));
                product.setP_kind(rs.getString("p_kind"));
                product.setP_name(rs.getString("p_name"));
<<<<<<< HEAD
                product.setP_conutry(rs.getString("p_conutry"));
                product.setP_capacity(rs.getString("p_capacity"));
                product.setP_etc(rs.getString("p_etc"));

                list.add(product);
            } return list;
        }catch (Exception e){
            e.parameta();
=======
                product.setP_conutry(rs.getString("p_country"));
                product.setP_capacity(rs.getString("pp_capacity"));
                product.setP_etc(rs.getString("pp_etc"));
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
>>>>>>> main
        }
    }
}
