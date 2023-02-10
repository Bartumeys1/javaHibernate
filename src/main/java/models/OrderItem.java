package models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_orderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;

    private double priceBuy;
    private int count;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;

}
