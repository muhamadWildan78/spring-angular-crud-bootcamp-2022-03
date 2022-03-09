package com.tabeldata.bootcamp.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Products {
    private Integer id;
    private String name;
    private Integer price;
    private String category;
    private Date create_date;
    private String create_by;

}
