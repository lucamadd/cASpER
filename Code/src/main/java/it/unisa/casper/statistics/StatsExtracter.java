package it.unisa.casper.statistics;

import com.opencsv.CSVReader;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class StatsExtracter {

    //Costanti che indicano il tipo di filtro sulle statistiche richiesto
    private static final int ALL = 0;
    private static final int LAST_USAGE = 1;
    private static final int LAST_5_USAGES = 2;
    private static final int LAST_10_USAGES = 3;
    private static final int LAST_20_USAGES = 4;
    private static final int LAST_50_USAGES = 5;



    //cartella dove salvare le statistiche
    private static final String STATS_DIRECTORY = System.getProperty("user.home") + File.separator +
            ".casper" + File.separator + "statistics";

    //legge le righe del file restituendo tutti i dati contenuti
    public String[][] readStatistics(int selectedIndex) throws IOException {

        FileReader inputFile = new FileReader(STATS_DIRECTORY + File.separator + "statistics.csv");
        CSVReader reader = new CSVReader(inputFile);

        List<String[]> dataList = reader.readAll();
        // salto le prime due righe:
        // la prima riga contiene l'istruzione per rendere il file compatibile con MS Excel e altri programmi
        // la seconda riga contiene l'header

        //estraggo i dati in base al filtro richiesto
        String [][] data;
        int index = 2;
        int j=0;
        switch (selectedIndex){
            case ALL:
                data = new String[dataList.size()-2][];
                for (int i=2; i<dataList.size();i++){
                    data[i-2] = dataList.get(i);
                }
                break;
            case LAST_USAGE:
                data = new String[1][];
                for (int i=dataList.size()-1; i<dataList.size();i++){
                    data[0] = dataList.get(i);
                }
                break;
            case LAST_5_USAGES:
                j=0;
                if (dataList.size()>=7)
                    index = dataList.size()-5;
                data = new String[dataList.size()-index][];
                for (int i=index; i<dataList.size();i++){
                    data[j] = dataList.get(i);
                    j++;
                }
                break;
            case LAST_10_USAGES:
                j=0;
                if (dataList.size()>=12)
                    index = dataList.size()-10;
                data = new String[dataList.size()-index][];
                for (int i=index; i<dataList.size();i++){
                    data[j] = dataList.get(i);
                    j++;
                }
                break;
            case LAST_20_USAGES:
                j=0;
                if (dataList.size()>=22)
                    index = dataList.size()-20;
                data = new String[dataList.size()-index][];
                for (int i=index; i<dataList.size();i++){
                    data[j] = dataList.get(i);
                    j++;
                }
                break;
            case LAST_50_USAGES:
                j=0;
                if (dataList.size()>=52)
                    index = dataList.size()-50;
                data = new String[dataList.size()-index][];
                for (int i=index; i<dataList.size();i++){
                    data[j] = dataList.get(i);
                    j++;
                }
                break;
            default:
                data = new String[dataList.size()-2][];
                for (int i=2; i<dataList.size();i++){
                    data[i-2] = dataList.get(i);
                }
                break;
        }
        return data;
    }
}
