package models;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
public class FilterPK implements Serializable {
    private FilterName filterName;
    private FilterValue filterValue;
    private Product product;
}
