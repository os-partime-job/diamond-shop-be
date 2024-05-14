package vn.fpt.diamond_shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "ORDER")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "order_id", unique = true)
    private UUID orderId;

    @Column(name = "end_user_id")
    private UUID endUserId;

    @Column(name = "clarity_id")
    private UUID jewelryId;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(name = "create_at")
    private OffsetDateTime createAt;

    @Column(name = "update_at")
    private OffsetDateTime updateAt;
}
