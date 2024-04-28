package com.appliedenergetics2.service;

import com.appliedenergetics2.model.Modpacks;
import com.appliedenergetics2.model.Modpacks.Item;
import com.appliedenergetics2.repository.ModpackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ModpackService {

    @Autowired
    private ModpackRepository modpackRepository;

    public Optional<Modpacks> getModpackById(String id) {
        return modpackRepository.findTypeContainingItemId(id);
    }

    public List<Modpacks> getAllModpacks() {
        return modpackRepository.findAll();
    }

    public Optional<Item> searchModpacksByItemTitle(String title) {
    	List<Modpacks> modpacks = modpackRepository.findByItemTitleContaining(title);
    	return modpacks.stream().flatMap(modpack->modpack.getItems().stream()).filter(item->item.getTitle().contains(title)).findAny();
    }
    
    public Optional<Item> getItemById(String itemId) {
        Optional<Modpacks> modpack = modpackRepository.findModpackContainingItemId(itemId);
        if (modpack.isPresent()) {
            return modpack.get().getItems().stream().filter(item -> item.getId().equals(itemId)).findFirst();
        }
        return Optional.empty();
    }
}

