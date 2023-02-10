package models;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilterNameGroutPK implements Serializable {
    private FilterValue filterValue;
    private FilterName filterName;
}
