package com.tabeldata.bootcamp.dao;

import com.tabeldata.bootcamp.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ProductsDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Products> list() {
        String sql = "select * from products";
        return this.jdbcTemplate.query(sql, new ProductsDao.RowMapperInner());
    }

    public Products findById(Integer id) {
        String sql = "select * from products where id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.jdbcTemplate.queryForObject(sql, map, new ProductsDao.RowMapperInner());
    }


    public Integer insertProducts(Products data) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into products(id, name, price, category, create_date, create_by) values (:id, :name, :price, :category, :create_date, :create_by)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", data.getId());
        map.addValue("name", data.getName());
        map.addValue("price", data.getPrice());
        map.addValue("category", data.getCategory());
        map.addValue("create_date", data.getCreate_date());
        map.addValue("create_by", data.getCreate_by());
        this.jdbcTemplate.update(sql, map, keyHolder);
        return (Integer) keyHolder.getKeys().get("id");
    }

    public void updateProducts(Products data) {
        String sql = "update products set name = :name, price = :price, category = :category, create_date = : create_date, create_by = : create_by where id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", data.getId());
        map.addValue("name", data.getName());
        map.addValue("price", data.getPrice());
        map.addValue("category", data.getCategory());
        map.addValue("create_date", data.getCreate_date());
        map.addValue("create_by", data.getCreate_by());
        this.jdbcTemplate.update(sql, map);
    }

    public void deleteCategory(String id) {
        String sql = "delete from products where id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        this.jdbcTemplate.update(sql, map);
    }

    public class RowMapperInner implements RowMapper<Products> {
        @Override
        public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
            Products data = new Products();
            data.setId(rs.getInt("id"));
            data.setName(rs.getString("name"));
            data.setPrice(rs.getInt("price"));
            data.setCategory(rs.getString("category"));
            Date create_date = rs.getDate("create_date");
            if (create_date != null)
                data.setCreate_date(Date.valueOf(LocalDate.now()));
            data.setCreate_by(rs.getString("create_by"));
            return data;
        }
    }
}