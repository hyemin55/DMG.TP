package repository;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteRepository extends JDBCTemplate {

    private int pp_id;

    public DeleteRepository(int pp_id) {
        this.pp_id = pp_id;
    }

    public void delete(){
        execute("""
                DELETE FROM purchaseProduct
                WHERE pp_id = ?
                """);
    }

    protected void handleResultSet(ResultSet rs) throws SQLException{

    }

    protected void setParameter(PreparedStatement pstmt) throws SQLException {
        pstmt.setInt(1, pp_id);
    }

    @Override
    protected void setPrint() {
        System.out.println("발주번호 " + pp_id + " 삭제가 정상적으로 처리되었습니다.");
    }
}
