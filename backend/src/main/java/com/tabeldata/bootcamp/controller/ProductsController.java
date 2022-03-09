package com.tabeldata.bootcamp.controller;
import com.tabeldata.bootcamp.dao.ProductsDao;
import com.tabeldata.bootcamp.model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductsController {

    @Autowired
    private ProductsDao dao;

    @GetMapping("/list")
    public List<Products> list() {
        return dao.list();
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Products data = this.dao.findById(id);
            return ResponseEntity.ok(data);
        } catch (EmptyResultDataAccessException erdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/show")
    public Products showData(
            @RequestParam(name = "id") Integer id,
            @RequestParam(name = "nme") String name,
            @RequestParam(name = "prc") Integer price,
            @RequestParam(name = "ctgr") String category,
            @RequestParam(name = "ctrdate") Date create_date,
            @RequestParam(name = "ctrby") String category_by){
        Products data = new Products();
        data.setId(id);
        data.setName(name);
        data.setPrice(price);
        data.setCategory(category);
        data.setCreate_date(Date.valueOf(LocalDate.now()));
        data.setCreate_by(category_by);
        return data;
    }

    @PostMapping(value = "/input")
    public ResponseEntity<Map<String, Object>>
    insertData(@Valid @RequestBody Products data, BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();
        if (result.hasErrors()) {
            hasil.put("status", result.getFieldErrors() );
            return ResponseEntity.badRequest().body(hasil);
        } else {
            hasil.put("id", dao.insertProducts(data));
            hasil.put("status", "simpan Berhasil");
            return ResponseEntity.ok(hasil);
        }
    }
    @PostMapping("/update")
    public ResponseEntity<Map<String, Object>>
    updateCategory(@RequestBody Products cate){
        Map<String, Object> hasil = new HashMap<>();
        dao.updateProducts(cate);
        hasil.put( "id", 0);
        hasil.put("status", "update Berhasil");
        return ResponseEntity.ok(hasil);
    }

    @DeleteMapping("/products/{kodeid}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        this.dao.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}