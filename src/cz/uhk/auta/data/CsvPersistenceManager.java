package cz.uhk.auta.data;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CsvPersistenceManager implements PersistenceManager{
    private String fileName;
    private  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public CsvPersistenceManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void ulozitVse(EvidenceAut evidenceAut) throws PersistenceException {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(fileName))) {

            for (Polozka p: evidenceAut.getAutomobil()) { //procházím všechny polozky automobilu
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); //formát ofPattern - tak jak si ho sami zvolime
                out.printf("%s\\n;%s\\n;%f\n", p.getDatum().format(formatter), p.getZnacka(), p.getPorizovaciHodnota());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Chyba pri zapisu souboru" + fileName, e); //e - parametr vyjimky
        }
    }

    @Override
    public EvidenceAut nacistVse() throws PersistenceException {
        EvidenceAut evidenceAut = new EvidenceImpl();


        try (BufferedReader inp = new BufferedReader(new FileReader(fileName))) {
           String radek;

            while ((radek = inp.readLine()) != null) { //prochazime radek po radku, null ´konec souboru
                String[] pole = radek.split(";"); // regex - nesmim pouzit tecku, atd. strednik ok
                LocalDate dat = LocalDate.parse(pole[0],formatter); // datum ziskavam ze Stringu
                int hodnotaAutomobilu = NumberFormat.getInstance().parse(pole[2]).intValue();
                evidenceAut.pridatPolozku(new Polozka(dat, pole[1], hodnotaAutomobilu));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PersistenceException("Soubor se nepodarilo oterit", e); // e - puvodni vyjímka
        } catch (IOException e) {
            e.printStackTrace();
            throw new PersistenceException("Chyba pri cteni souboru", e);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            throw new PersistenceException("Chybny format data", e);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new PersistenceException("Chybny format cisla", e);
        }
        return evidenceAut;
    }
}
