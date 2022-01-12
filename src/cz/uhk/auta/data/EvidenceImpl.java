package cz.uhk.auta.data;

import java.util.ArrayList;
import java.util.List;


public class EvidenceImpl implements EvidenceAut {
    private List<Polozka> automobil = new ArrayList<>();

    @Override
    public void pridatPolozku(Polozka p) {
        automobil.add(p);
    }

    @Override
    public List<Polozka> getAutomobil() {
        return automobil;
    }

    @Override
    /**
     * celkova porizovaci hodnota vsech automobilu ve firme, na pocatku hodnota nula - pricitam jednotlive hodnoty automobil
     * suma - hodnotao v ucetnictvi, ze ktere se vychazi pri vypoctu odpisu
     */
    public double getHodnotaAutomobilu() {
       double suma  = 0;

       for (Polozka p : automobil) {
           suma += p.getPorizovaciHodnota();
       }
        return suma;
    }

}
