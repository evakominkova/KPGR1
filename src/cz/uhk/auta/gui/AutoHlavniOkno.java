package cz.uhk.auta.gui;

import cz.uhk.auta.data.CsvPersistenceManager;
import cz.uhk.auta.data.EvidenceAut;
import cz.uhk.auta.data.EvidenceImpl;
import cz.uhk.auta.data.PersistenceException;
import cz.uhk.auta.data.PersistenceManager;
import cz.uhk.auta.data.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AutoHlavniOkno extends JFrame {
    private JTextArea taVystup = new JTextArea(25, 40);

    /*
     * trida EvidendceImpl kam prichazeji nove polozky
     */
    private EvidenceAut evidenceAut = new EvidenceImpl();

    private PersistenceManager persistenceManager = new CsvPersistenceManager("dataFormat.CSV");

    public AutoHlavniOkno() {
        super("Evidence aut");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initGui();
    }

    private void initGui() {
        createToolBar();

        add(new JScrollPane(taVystup), BorderLayout.CENTER);

        pack();
    }


    /*
     * tb - zkratka pro tlacitko
     */
    private void createToolBar() {
        JToolBar tb = new JToolBar(JToolBar.HORIZONTAL);
        add(tb, BorderLayout.NORTH);

        JButton btEviduj = new JButton("Novy firemni automobil");
        JButton btVypis = new JButton("Vypis automobilu");
        JButton btEditace = new JButton("Editace automobilu");

        JButton btUlozitData = new JButton("Uloz automobil");
        JButton btNacistData = new JButton("Nacti automobil");

        btEviduj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novaPolozka();
            }
        });

        btVypis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vypisPolozek();
            }
        });

        btUlozitData.addActionListener((e)-> ulozitData()); //ulozitData = nazev metody
        btNacistData.addActionListener((e)-> nacistData());

        btEditace.addActionListener((e) -> editace());

        tb.add(btEviduj);
        tb.add(btVypis);
        tb.add(btEditace);

        tb.add(btUlozitData);
        tb.add(btNacistData);
    }

    private void nacistData() {
        try {
            evidenceAut = persistenceManager.nacistVse();
            taVystup.append("Data byla nactena, vse v poradku\n");
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(AutoHlavniOkno.this, e.getMessage(), "CHYBA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void vypisPolozek() {
        taVystup.setText("");
        for (Polozka p : evidenceAut.getAutomobil()) {
            String dats = p.getDatum().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            taVystup.append(String.format("%s\t %s\t %.2f\n", dats, p.getZnacka(), p.getPorizovaciHodnota()));
        }
    }

    private void novaPolozka() {
        AutoDialog dlg = new AutoDialog(AutoHlavniOkno.this);
        Polozka p = dlg.vytvorZaznamNovehoAuta();
        if (p != null) {
            evidenceAut.pridatPolozku(p);
        }
    }

    private void ulozitData() { //ulozit vse co je v nasi evidenci

        try {
            persistenceManager.ulozitVse(evidenceAut);
            taVystup.append("Data byla ulozena\n");
        } catch (PersistenceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(AutoHlavniOkno.this, e.getMessage(), "CHYBA", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editace() {
        String poradiStr = JOptionPane.showInputDialog("Zadejte cislo automobilu pro upravu");
        try {
            int poradi = Integer.valueOf(poradiStr); //pouzivam lokalni promennou definovanou o par radku vyse
            poradi--; // znak -- zmenseni o jednotku
            if (poradi >= 0 && poradi < evidenceAut.getAutomobil().size()) { // to znamena, ze mam index ve spravnem rozsahu a nepresahuje mez
                Polozka p = new AutoDialog(this).editujZaznamAutomobilu(evidenceAut.getAutomobil().get(poradi));
                if (p != null) {
                    evidenceAut.getAutomobil().set(poradi, p);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Neplatne cislo automobilu", "CHYBA", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
        }
    }

    private void seradit() {
        polo


    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AutoHlavniOkno().setVisible(true);
            }
        });
    }
}
