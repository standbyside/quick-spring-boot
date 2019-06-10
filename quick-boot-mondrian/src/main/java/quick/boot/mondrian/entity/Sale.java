package quick.boot.mondrian.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    private int saleId;
    private int proId;
    private int cusId;
    private float unitPrice;
    private int number;

}
