package repository;

import domain.Wholesaler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WholesalerRepository extends JDBCTemplate {
    private static Logger LOG = LoggerFactory.getLogger(WholesalerRepository.class);


    public void selectWholesaler() {
        LOG.info("DB connection success");
        execute("select * from wholesaler");
    }

    @Override
    protected void handleResultSet(ResultSet rs) throws SQLException {
        List<Wholesaler> list = new ArrayList<>();
        while (rs.next()) {
            Wholesaler wholesaler = new Wholesaler();
            wholesaler.setW_id(rs.getInt("w_id"));
            wholesaler.setW_department(rs.getString("w_department"));
            wholesaler.setW_name(rs.getString("w_name"));
            wholesaler.setW_phone(rs.getString("w_phone"));
            wholesaler.setW_jobTitle(rs.getString("w_jobTitle"));
            list.add(wholesaler);

        }
        int i = 0;
        while (true) {
            if (i == list.size()) {
                break;
            }
            System.out.println(list.get(i));
            i++;
        }

    }

    @Override
    protected void setParameter(PreparedStatement pstmt) {

    }

    @Override
    protected void setPrint() {

    }
}
