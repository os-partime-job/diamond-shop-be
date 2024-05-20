package vn.fpt.diamond_shop.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetListJewelryResponse {
    private Integer idJewelry;
    private String jewelryTitle;// name

    private String jewelryCode;

    private String jewelryType;

    private Integer jewelryTypeId;

    private Integer quantity;

    private Double price;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    private String description;

    private Long imageId;

    private Integer typeEnum;

    private Long diamondId;


}
