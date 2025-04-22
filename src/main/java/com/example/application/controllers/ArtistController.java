package com.example.application.controllers;

import com.example.application.data.Artist;
import com.example.application.data.Location;
import com.example.application.services.ArtistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Artist> getArtists() {
        return service.getAllArtists();
    }

    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return service.saveArtist(artist);
    }

    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        service.deleteArtist(id);
    }
}