package models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_filter_name_groups")
@IdClass(FilterNameGroutPK.class)
public class FilterNameGroup {
    @Id
    @ManyToOne
    @JoinColumn(name = "filter_Name_Id", nullable = false)
    private FilterName filterName;
    @Id
    @ManyToOne
    @JoinColumn(name = "filter_Value_Id", nullable = false)
    private FilterValue filterValue;
}
