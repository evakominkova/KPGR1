package cz.uhk.auta.data;

import cz.uhk.auta.gui.AutoHlavniOkno;

import java.time.LocalDate;

/**
 * Polozka auta ve firemni evidenci
 * Trida ze ktere vychazim
 */

public class Polozka {
    private LocalDate datum;
    private String znacka;
    private int porizovaciHodnota;

    /**
     * Konstruktor bez parametru
     * @param autoHlavniOkno
     */
    public Polozka(AutoHlavniOkno autoHlavniOkno) {}

    /**
     * Konstruktor s parametry
     * @param znacka
     * @param porizovaciHodnota
     */
    public Polozka(LocalDate datum, String znacka, int porizovaciHodnota) {
        this.datum = datum;
        this.znacka = znacka;
        this.porizovaciHodnota = porizovaciHodnota;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate newValue) {
        datum = newValue;
    }

    public void setZnacka(String znacka) {
        /*
         * vlozeni znacky firemniho automobilu
         */
        this.znacka = znacka;
    }

    public String getZnacka() {
        return znacka;
    }

    public void setPorizovaciHodnota(int porizovaciHodnota) {
        if (porizovaciHodnota < 0.0) {
            System.out.println("Nelze vlozit nulovou hodnotu, jedna se o nesmysl");
        }
        /*
         * nacteni samotne porizovanc hodnoty jednotlive polozky
         */
        this.porizovaciHodnota = porizovaciHodnota;
    }

    public double getPorizovaciHodnota() {
        return porizovaciHodnota;
    }

}
