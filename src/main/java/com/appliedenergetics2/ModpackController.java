package com.appliedenergetics2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appliedenergetics2.model.Modpacks;
import com.appliedenergetics2.model.Modpacks.Item;
import com.appliedenergetics2.model.User;
import com.appliedenergetics2.repository.ModpackRepository;
import com.appliedenergetics2.service.ModpackService;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/index")
public class ModpackController {

    @Autowired
    private ModpackService modpackService;

    @GetMapping
    public ResponseEntity<List<Modpacks>> getAllModpacks() {
        List<Modpacks> modpacks = modpackService.getAllModpacks();
        return ResponseEntity.ok(modpacks);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Item>> searchModpacks(@RequestParam String title) {
        List<Item> items = modpackService.searchModpacksByItemTitle(title);
        if (!items.isEmpty()) {
            return ResponseEntity.ok(items);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable String itemId) {
        Optional<Item> item = modpackService.getItemById(itemId);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{typeId}")
    public ResponseEntity<Modpacks> getTypeById(@PathVariable String typeId) {
        Optional<Modpacks> item = modpackService.getModpackById(typeId);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
