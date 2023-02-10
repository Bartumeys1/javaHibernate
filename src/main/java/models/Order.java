package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "orderStatus_id" , nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;
    public Order()
    {
        orderItems= new ArrayList<>();
    }


}
