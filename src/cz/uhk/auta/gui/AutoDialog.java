package cz.uhk.auta.gui;

import cz.uhk.auta.data.Polozka;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class AutoDialog extends JDialog {
    private JTextField tfDatum = new JTextField(10);
    private JTextField tfAuto = new JTextField(20);
    private JTextField tfHodnotaAutomobilu = new JTextField(10);
    private JButton btOk;
    private JButton btStorno;

    private boolean ok = false;


    public AutoDialog(Frame owner) {
        super(owner, "Editace automobilu", true);

        initGui();
    }

    private void initGui() {
        JPanel pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel labDatum = new JLabel("Datum");
        labDatum.setLabelFor(tfDatum);
        labDatum.setDisplayedMnemonic('D');
        pnlCenter.add(labDatum);
        pnlCenter.add(tfDatum);

        JLabel labAuto = new JLabel("Auto");
        labAuto.setLabelFor(tfAuto);
        labAuto.setDisplayedMnemonic('A');
        pnlCenter.add(labAuto);
        pnlCenter.add(tfAuto);

        JLabel labHodnotaAutomobilu = new JLabel("Hodnota automobilu");
        labHodnotaAutomobilu.setLabelFor(tfHodnotaAutomobilu);
        labHodnotaAutomobilu.setDisplayedMnemonic('H');
        pnlCenter.add(labHodnotaAutomobilu);
        pnlCenter.add(tfHodnotaAutomobilu);

        add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btOk = new JButton("OK");
        btStorno = new JButton("STORNO");

        pnlButtons.add(btOk);
        pnlButtons.add(btStorno);

        ActionListener listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ok = e.getSource() == btOk;
                /*
                Pokud nedopadne dobře validace je pro mne SETVISIBLE false
                 */

                if (ok && validace()) {
                    setVisible(false);

                } else if (!ok) {
                    setVisible(false);
                }
            }
        };


        btOk.addActionListener(listener);
        btStorno.addActionListener(listener);

        /*
         * stisknuti enteru znamena potvrzeni
         */
        getRootPane().

                setDefaultButton(btOk);

        add(pnlButtons, BorderLayout.SOUTH);

        pack();

    }

    private boolean validace() {
        boolean valid = true;

        try {
            LocalDate datum = LocalDate.parse(tfDatum.getText(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Zadal jsi chybny format datumu", "CHYBA - ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        try {
            double castka = Double.parseDouble(tfHodnotaAutomobilu.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Zadal jsi chybny format hodnoty automobilu",
                    "CHYBA", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        return valid;
    }


        public Polozka vytvorZaznamNovehoAuta () {

            /*
             * vycisteni polozek
             */
            tfHodnotaAutomobilu.setText("0");
            tfAuto.setText("");
            tfDatum.setText(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

            /*
             * zobrazeni okna
             */
            setVisible(true);
            if (ok) {
                LocalDate dat = LocalDate.parse(tfDatum.getText(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                Polozka p = new Polozka(dat, tfAuto.getText(), int.ValueOf(tfHodnotaAutomobilu.getText()));

                return p;
            } else {
                return null;
            }
        }

        public Polozka editujZaznamAutomobilu (Polozka p){
            tfAuto.setText(p.getZnacka());
            tfHodnotaAutomobilu.setText(String.valueOf(p.getPorizovaciHodnota()));
            tfDatum.setText(p.getDatum().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
            setVisible(true);
            if (ok) {
                LocalDate dat = LocalDate.parse(tfDatum.getText(), DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
                Polozka pNew = new Polozka(dat, tfAuto.getSelectedText(), Double.valueOf(tfHodnotaAutomobilu.getText()));
                return pNew;
            } else {
                return null;
            }
        }

//    public static void main(String[] args) {
//        AutoDialog dlg = new AutoDialog(null);
//
//        Polozka p = dlg.vytvorZaznamNovehoAuta();
//        if (p != null) {
//            System.out.printf("%s %.2f\n", p.getZnacka(), p.getPorizovaciHodnota());
//            Polozka p2 = dlg.editujZaznamAutomobilu(p);
//            if (p2 != null) {
//                System.out.printf("%s %.2f\n", p2.getZnacka(), p2.getPorizovaciHodnota());
//            }
//        }
//    }
    }
