package pl.sternik.mg.repositories;

import java.util.List;

import pl.sternik.mg.entities.Znaczek;

public interface ZnaczkiRepository {
    Znaczek create(Znaczek Znaczek) throws ZnaczekAlreadyExistsException;
    Znaczek readById(Long id) throws NoSuchZnaczekException;
    Znaczek update(Znaczek Znaczek) throws NoSuchZnaczekException;
    void deleteById(Long id) throws NoSuchZnaczekException;
    List<Znaczek> findAll();
}