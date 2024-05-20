package vn.fpt.diamond_shop.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;


@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class GetDetailJewelryResponse {
    private Long idJewelry;
    private String jewelryTitle;

    private String jewelryCode;

    private String jewelryType;

    private Long jewelryTypeId;

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
