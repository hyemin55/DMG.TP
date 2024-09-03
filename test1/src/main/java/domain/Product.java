package domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {
    private int p_id;
    private String p_brand;
    private String p_kind;
    private String p_name;
    private String p_conutry;
    private String p_capacity;
    private String p_etc;
}
