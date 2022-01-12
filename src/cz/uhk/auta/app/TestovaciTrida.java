package cz.uhk.auta.app;

import cz.uhk.auta.data.EvidenceAut;
import cz.uhk.auta.data.EvidenceImpl;
import cz.uhk.auta.data.Polozka;

import java.time.LocalDate;

public class TestovaciTrida {

    public static void main(String[] args) {
        EvidenceAut evidenceAut = new EvidenceImpl();

        evidenceAut.pridatPolozku(new Polozka(LocalDate.of(2020,1,1), "Trabant", 120000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(), "Audi", 1250000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(),"Ford", 1250000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(),"Ferari", 10000000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(),"Trabant", 120000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(),"Opel", 500000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(),"Skoda", 1200000));
        evidenceAut.pridatPolozku(new Polozka(LocalDate.now(),"Opel", 750000));

        System.out.printf("Aktualni hodnota automobilu ve firmě je: %.2f", evidenceAut.getHodnotaAutomobilu());
    }
}
