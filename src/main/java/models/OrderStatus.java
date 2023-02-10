package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_orderStatuses")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String name;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private boolean isDeleted;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> orders;

    public OrderStatus()
    {
        orders = new ArrayList<>();
    }

}
