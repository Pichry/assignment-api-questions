package com.example.question4_ecommerce_api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.question4_ecommerce_api.model.Product;

public class ProductController {
    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1L, "Laptop", "Gaming laptop", 1200.0, "Electronics", 5, "Dell"),
            new Product(2L, "Phone", "Smartphone", 800.0, "Electronics", 10, "Samsung"),
            new Product(3L, "Headphones", "Noise cancelling", 150.0, "Accessories", 0, "Sony"),
            new Product(4L, "Shoes", "Running shoes", 90.0, "Fashion", 12, "Nike"),
            new Product(5L, "Watch", "Smart watch", 200.0, "Accessories", 6, "Apple"),
            new Product(6L, "Keyboard", "Mechanical keyboard", 110.0, "Electronics", 8, "Logitech"),
            new Product(7L, "Monitor", "4K display", 400.0, "Electronics", 4, "LG"),
            new Product(8L, "Backpack", "Laptop backpack", 60.0, "Fashion", 15, "HP"),
            new Product(9L, "Tablet", "Android tablet", 300.0, "Electronics", 0, "Lenovo"),
            new Product(10L, "Mouse", "Wireless mouse", 40.0, "Accessories", 20, "Logitech")
    ));

    @GetMapping
    public List<Product> getAll(@RequestParam(required = false) Integer page,
                                @RequestParam(required = false) Integer limit) {

        if (page == null || limit == null) return products;

        int start = page * limit;
        int end = Math.min(start + limit, products.size());
        if (start >= products.size()) return Collections.emptyList();

        return products.subList(start, end);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public List<Product> byCategory(@PathVariable String category) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getCategory().equalsIgnoreCase(category)) result.add(p);
        }
        return result;
    }

    @GetMapping("/brand/{brand}")
    public List<Product> byBrand(@PathVariable String brand) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getBrand().equalsIgnoreCase(brand)) result.add(p);
        }
        return result;
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())
                    || p.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    @GetMapping("/price-range")
    public List<Product> priceRange(@RequestParam double min,
                                    @RequestParam double max) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) result.add(p);
        }
        return result;
    }

    @GetMapping("/in-stock")
    public List<Product> inStock() {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getStockQuantity() > 0) result.add(p);
        }
        return result;
    }

    @PostMapping
    public ResponseEntity<Product> add(@RequestBody Product product) {
        products.add(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,
                                          @RequestBody Product updated) {
        for (Product p : products) {
            if (p.getProductId().equals(id)) {
                p.setName(updated.getName());
                p.setDescription(updated.getDescription());
                p.setPrice(updated.getPrice());
                p.setCategory(updated.getCategory());
                p.setBrand(updated.getBrand());
                p.setStockQuantity(updated.getStockQuantity());
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable Long id,
                                            @RequestParam int quantity) {
        for (Product p : products) {
            if (p.getProductId().equals(id)) {
                p.setStockQuantity(quantity);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = products.removeIf(p -> p.getProductId().equals(id));
        return removed ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }

}
