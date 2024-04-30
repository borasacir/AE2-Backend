package com.appliedenergetics2.service;

import com.appliedenergetics2.model.Modpacks;
import com.appliedenergetics2.model.Modpacks.Item;
import com.appliedenergetics2.repository.ModpackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private String formatTitle(String title) {
        return Arrays.stream(title.split(" "))
                     .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                     .collect(Collectors.joining(" "));
    }


    public List<Item> searchModpacksByItemTitle(String title) {
        String formattedTitle = formatTitle(title);
        List<Modpacks> modpacks = modpackRepository.findByItemTitleContaining(formattedTitle);
        return modpacks.stream()
                      .flatMap(modpack -> modpack.getItems().stream())
                      .filter(item -> item.getTitle().contains(formattedTitle))
                      .collect(Collectors.toList());
    }

    
    
    /*public List<Item> searchModpacksByItemTitle(String title) {
        List<Modpacks> modpacks = modpackRepository.findByItemTitleContaining(title);
        return modpacks.stream()
                      .flatMap(modpack -> modpack.getItems().stream())
                      .filter(item -> item.getTitle().contains(title))
                      .collect(Collectors.toList());
    }
    */
    public Optional<Item> getItemById(String itemId) {
        Optional<Modpacks> modpack = modpackRepository.findModpackContainingItemId(itemId);
        if (modpack.isPresent()) {
            return modpack.get().getItems().stream().filter(item -> item.getId().equals(itemId)).findFirst();
        }
        return Optional.empty();
    }
}

