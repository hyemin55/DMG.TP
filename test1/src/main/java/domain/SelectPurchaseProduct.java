package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class SelectPurchaseProduct {

    protected int pp_id;
    private Date pp_orderDate;
    private Date pp_receivedDate;
    private String m_name;
    private String w_name;
    private int p_kind;
    private String p_brand;
    private int p_capacity;
    private int pp_costPrice;
    private String p_name;
    private int pp_receivedCount;

}
