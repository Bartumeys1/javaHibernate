package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_filterNames")
public class FilterName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private  boolean isDeleted;
    @OneToMany(mappedBy = "filterName")
    private List<FilterNameGroup> filterNameGroups;

    @OneToMany(mappedBy = "filterName")
    private List<Filter> nameFilters;


    public FilterName()
    {
        filterNameGroups = new ArrayList<>();
        nameFilters = new ArrayList<>();
    }

}
