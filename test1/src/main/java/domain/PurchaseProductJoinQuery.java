package domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseProductJoinQuery extends PurchaseProduct{

    protected String p_name;
    protected String p_etc;
    protected int p_amount;

    @Override
    public String toString() {
        return "PurchaseProductJoinQuery{" +
                "p_name='" + p_name + '\'' +
                ", p_etc='" + p_etc + '\'' +
                ", p_etc='" + p_amount + '\'' +
                ", pp_id=" + pp_id +
                ", pp_orderDate=" + pp_orderDate +
                ", pp_receivedDate=" + pp_receivedDate +
                ", pp_costPrice=" + pp_costPrice +
                ", pp_sellingPrice=" + pp_sellingPrice +
                ", pp_receivedCount=" + pp_receivedCount +
                ", pp_manufactureDate=" + pp_manufactureDate +
                ", pp_expirationDate=" + pp_expirationDate +
                '}';
    }
}
