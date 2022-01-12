package cz.uhk.auta.data;

import java.util.List;

/*
 * Rozhranní pro evidenci polozek aut ve firme
 */

public interface EvidenceAut {
    /**
     * Metoda pro pridani automobilu do firemni evidence
     * @param p  nova polozka - novy automobil ve firme
     */
    void pridatPolozku(Polozka p);

    /**
     * Vraci seznam vsech automobilu v evidenci
     * @return seznam firemnich automobilu
     */
    List<Polozka> getAutomobil();


    /** Vraci aktualni hodnotu vsech automobilu ve firme
     * @return aktualni stav v Kc
     */
    double getHodnotaAutomobilu ();
}
