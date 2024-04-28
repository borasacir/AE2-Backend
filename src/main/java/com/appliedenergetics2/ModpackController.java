package com.appliedenergetics2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/modpacks")
public class ModpackController {

    @Autowired
    private ModpackService modpackService;

    @GetMapping
    public ResponseEntity<List<Modpacks>> getAllModpacks() {
        List<Modpacks> modpacks = modpackService.getAllModpacks();
        return ResponseEntity.ok(modpacks);
    }

    @GetMapping("/search")
    public ResponseEntity<Item> searchModpacks(@RequestParam String title) {
        Optional<Item> item = modpackService.searchModpacksByItemTitle(title);
        return item.map(ResponseEntity::ok).orElseGet(() ->ResponseEntity.notFound().build());
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable String itemId) {
        Optional<Item> item = modpackService.getItemById(itemId);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{itemId}")
    public ResponseEntity<Modpacks> getTypeById(@PathVariable String itemId) {
        Optional<Modpacks> item = modpackService.getModpackById(itemId);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
