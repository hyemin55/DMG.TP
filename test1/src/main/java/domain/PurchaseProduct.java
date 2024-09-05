package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class PurchaseProduct {

    protected int pp_id;
    protected Date pp_orderDate;
    protected Date pp_receivedDate;
    protected int pp_costPrice;
    protected int pp_sellingPrice;
    protected int pp_receivedCount;
    protected Date pp_manufactureDate;
    protected Date pp_expirationDate;

}
