package models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_filterValues")
public class FilterValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200, nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateCreated;
    private  boolean isDeleted;

    @OneToMany(mappedBy = "filterValue")
    private List<FilterNameGroup> filterValueGroups;
    @OneToMany(mappedBy = "filterValue")
    private List<Filter> valueFilters;
    public FilterValue()
    {
        filterValueGroups = new ArrayList<>();
        valueFilters = new ArrayList<>();
    }
}
