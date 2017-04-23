package pl.sternik.mg.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.mg.entities.Znaczek;
import pl.sternik.mg.repositories.NoSuchZnaczekException;
import pl.sternik.mg.repositories.ZnaczekAlreadyExistsException;
import pl.sternik.mg.repositories.ZnaczkiRepository;

@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private ZnaczkiRepository bazaDanych;

    @Override
    public List<Znaczek> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Znaczek> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Znaczek> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> create(Znaczek Znaczek) {
        try {
            return Optional.of(bazaDanych.create(Znaczek));
        } catch (ZnaczekAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> edit(Znaczek Znaczek) {
        try {
            return Optional.of(bazaDanych.update(Znaczek));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchZnaczekException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Znaczek> findLatest3() {
        return Collections.emptyList();
    }

}