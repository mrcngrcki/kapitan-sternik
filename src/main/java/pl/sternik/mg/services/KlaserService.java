package pl.sternik.mg.services;

import java.util.List;
import java.util.Optional;

import pl.sternik.mg.entities.Znaczek;

public interface KlaserService {
    List<Znaczek> findAll();

    List<Znaczek> findAllToSell();

    Optional<Znaczek> findById(Long id);

    Optional<Znaczek> create(Znaczek Znaczek);

    Optional<Znaczek> edit(Znaczek Znaczek);

    Optional<Boolean> deleteById(Long id);

    List<Znaczek> findLatest3();
}