package it.unisa.casper.statistics;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class StatsExtracter {
    //cartella dove salvare le statistiche
    private static final String STATS_DIRECTORY = System.getProperty("user.home") + File.separator +
            ".casper" + File.separator + "statistics";

    //legge le righe del file restituendo tutti i dati contenuti
    public String[][] readStatistics() throws IOException {

        FileReader inputFile = new FileReader(STATS_DIRECTORY + File.separator + "statistics.csv");
        CSVReader reader = new CSVReader(inputFile);

        List<String[]> dataList = reader.readAll();
        // salto le prime due righe:
        // la prima riga contiene l'istruzione per rendere il file compatibile con MS Excel e altri programmi
        // la seconda riga contiene l'header
        String [][] data = new String[dataList.size()-2][];
        for (int i=2; i<dataList.size();i++){
            data[i-2] = dataList.get(i);
            System.out.println(Arrays.asList(data[i-2]).toString());
        }
        return data;
    }
}
