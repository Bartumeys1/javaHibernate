package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 500 , nullable = false)
    private String name;
    @Column(length = 4000 )

    private String description;
    private double price;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private  boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "category_id" , nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Basket> userProducts;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;
    @OneToMany(mappedBy = "product")
    private List<Filter> productFilters;

    public Product() {
        userProducts = new ArrayList<>();
        productImages = new ArrayList<>();
        orderItems = new ArrayList<>();
        productFilters = new ArrayList<>();
    }

}
