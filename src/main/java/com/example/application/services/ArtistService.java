package com.example.application.services;

import com.example.application.data.Artist;
import com.example.application.data.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository repository;

    public ArtistService(ArtistRepository repository) {
        this.repository = repository;
    }

    public List<Artist> getAllArtists() {
        return repository.findAll();
    }

    public Artist saveArtist(Artist artist) {
        return repository.save(artist);
    }

    public void deleteArtist(Long id) {
        repository.deleteById(id);
    }

}
