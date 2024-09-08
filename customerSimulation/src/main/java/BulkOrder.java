import java.sql.Connection;
import java.sql.SQLException;

public class BulkOrder extends OrderTemplate {
    public BulkOrder(int customerId) {
        super(customerId);
    }

    @Override
    protected void processOrder(Connection connection, int selectedProduct) throws SQLException {

    }
}
