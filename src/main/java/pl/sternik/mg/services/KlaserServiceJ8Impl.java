package pl.sternik.mg.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import pl.sternik.mg.entities.Status;
import pl.sternik.mg.entities.Znaczek;
import pl.sternik.mg.repositories.NoSuchZnaczekException;
import pl.sternik.mg.repositories.ZnaczekAlreadyExistsException;
import pl.sternik.mg.repositories.ZnaczkiRepository;

@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private ZnaczkiRepository znaczki;

    @Override
    public List<Znaczek> findAll() {
        return znaczki.findAll();
    }

    @Override
    public List<Znaczek> findLatest3() {
        return znaczki.findAll().stream().sorted((a, b) -> b.getDataNabycia().compareTo(a.getDataNabycia())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Znaczek> findById(Long id) {
        try {
            return Optional.of(znaczki.readById(id));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> create(Znaczek Znaczek) {
        try {
            return Optional.of(znaczki.create(Znaczek));
        } catch (ZnaczekAlreadyExistsException e) {
            try {
                return Optional.of(znaczki.readById(Znaczek.getNumerKatalogowy()));
            } catch (NoSuchZnaczekException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Znaczek> edit(Znaczek Znaczek) {
        try {
            return Optional.of(znaczki.update(Znaczek));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            znaczki.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchZnaczekException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Znaczek> findAllToSell() {
        return znaczki.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }
}