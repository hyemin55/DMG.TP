package repository;

import com.zaxxer.hikari.HikariDataSource;
import config.HikariCP;
import domain.Wholesaler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WholesalerRepository {
    private static Logger LOG = LoggerFactory.getLogger(WholesalerRepository.class);

    private static HikariDataSource dataSource = HikariCP.createDataSource();

    public List<Wholesaler> selectwholesaler() throws ClassNotFoundException {

        // primitive type 기본타입
        // int, double, boolean, float, char
        // reference type 참조타입
        // Integer, String, int[], char[].

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            LOG.info("DB connection success");
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("select * from wholesaler");
            //sql 구문 작성
            ResultSet rs = pstmt.executeQuery();

            List<Wholesaler> list = new ArrayList<>();
            while(rs.next()){
                Wholesaler wholesaler = new Wholesaler();
                wholesaler.setW_id(rs.getInt("w_id"));
                wholesaler.setW_department(rs.getString("w_department"));
                wholesaler.setW_name(rs.getString("w_name"));
                wholesaler.setW_phone(rs.getString("w_phone"));
                wholesaler.setW_jobTitle(rs.getString("w_jobTitle"));

                list.add(wholesaler);
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                pstmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
