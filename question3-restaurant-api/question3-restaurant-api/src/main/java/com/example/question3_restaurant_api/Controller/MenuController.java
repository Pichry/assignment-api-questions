package com.example.question3_restaurant_api.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.question3_restaurant_api.model.MenuItem;

public class MenuController {
    

    private List<MenuItem> menu = new ArrayList<>(Arrays.asList(
            new MenuItem(1L, "Burger", "Beef burger", 8.5, "Main Course", true),
            new MenuItem(2L, "Fries", "Crispy fries", 3.0, "Appetizer", true),
            new MenuItem(3L, "Cake", "Chocolate cake", 4.5, "Dessert", false)
    ));

    @GetMapping
    public List<MenuItem> getAll() {
        return menu;
    }

    @GetMapping("/{id}")
    public MenuItem getById(@PathVariable Long id) {
        return menu.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> byCategory(@PathVariable String category) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem m : menu) {
            if (m.getCategory().equalsIgnoreCase(category)) {
                result.add(m);
            }
        }
        return result;
    }

    @PostMapping
    public MenuItem add(@RequestBody MenuItem item) {
        menu.add(item);
        return item;
    }

    @PutMapping("/{id}/availability")
    public void toggle(@PathVariable Long id) {
        for (MenuItem m : menu) {
            if (m.getId().equals(id)) {
                m.setAvailable(!m.isAvailable());
            }
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        menu.removeIf(m -> m.getId().equals(id));
    }
}



