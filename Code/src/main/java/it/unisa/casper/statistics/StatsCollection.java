package it.unisa.casper.statistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsCollection {

    //Singleton pattern
    private static StatsCollection instance = null;
    private long globalTimer;


    private StatsCollection() {}

    public static StatsCollection getInstance() {
        if (instance == null) {
            instance = new StatsCollection();
        }
        return instance;
    }

    public void setGlobalTimer(long globalTimer) {
        this.globalTimer = globalTimer;
    }


    public void saveAll() {
        //creo la cartella statistics dove salvare i file
        String nameDir = System.getProperty("user.home") + File.separator + ".casper" + File.separator + "statistics";
        File dir = new File(nameDir);
        dir.mkdir();

        //formatto la data per il nome del file
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        String fileName = dateFormat.format(date);

        //creo il file di log
        try {
            FileWriter f = new FileWriter(nameDir + File.separator + fileName + ".txt");
            BufferedWriter out = new BufferedWriter(f);
            out.write("viewTime=" + globalTimer);
            out.flush();
            out.newLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
