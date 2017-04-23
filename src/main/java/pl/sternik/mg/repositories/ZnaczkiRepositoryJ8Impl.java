package pl.sternik.mg.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.mg.entities.Status;
import pl.sternik.mg.entities.Znaczek;

@Service
@Qualifier("lista")
public class ZnaczkiRepositoryJ8Impl implements ZnaczkiRepository {

    private List<Znaczek> znaczki = new ArrayList<Znaczek>() {
        {
            add(Znaczek.produceZnaczek(1L, "Polska", 1L, "zł", "Ładna nowiutka złotóweczka", new Date(), new BigDecimal("1.2"),
                    Status.NOWA));
            add(Znaczek.produceZnaczek(2L, "Polska", 1L, "zł", "First Znaczek", new Date(), new BigDecimal("1.2"),
                    Status.DO_SPRZEDANIA));
            add(Znaczek.produceZnaczek(3L, "Polska", 1L, "zł", "Second Znaczek", new Date(), new BigDecimal("1.2"), Status.DUBLET));
            add(Znaczek.produceZnaczek(4L, "Polska", 1L, "zł", "Forth Znaczek", new Date(), new BigDecimal("1.2"),
                    Status.DO_SPRZEDANIA));
            add(Znaczek.produceZnaczek(5L, "Polska", 1L, "zł", "Znaczek Number 5", new Date(), new BigDecimal("1.2"), Status.NOWA));
            add(Znaczek.produceZnaczek(6L, "Polska", 1L, "zł", "Sixth Znaczek", new Date(), new BigDecimal("1.2"), Status.NOWA));
        }
    };

    @Override
    public List<Znaczek> findAll() {
        return this.znaczki;
    }

    @Override
    public Znaczek readById(Long id) throws NoSuchZnaczekException {
        return this.znaczki.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchZnaczekException::new);
    }

    @Override
    public Znaczek create(Znaczek Znaczek) {
        if (!znaczki.isEmpty()) {
            Znaczek.setNumerKatalogowy(
                    this.znaczki.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            Znaczek.setNumerKatalogowy(1L);
        }
        this.znaczki.add(Znaczek);
        return Znaczek;
    }

    @Override
    public Znaczek update(Znaczek Znaczek) throws NoSuchZnaczekException {
        for (int i = 0; i < this.znaczki.size(); i++) {
            if (Objects.equals(this.znaczki.get(i).getNumerKatalogowy(), Znaczek.getNumerKatalogowy())) {
                this.znaczki.set(i, Znaczek);
                return Znaczek;
            }
        }
        throw new NoSuchZnaczekException("Nie ma takiej znaczki: " + Znaczek.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchZnaczekException {
        for (int i = 0; i < this.znaczki.size(); i++) {
            if (Objects.equals(this.znaczki.get(i).getNumerKatalogowy(), id)) {
                this.znaczki.remove(i);
            }
        }
        throw new NoSuchZnaczekException("Nie ma takiej znaczki: " + id);
    }

}