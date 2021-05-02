package it.unisa.casper.statistics;

import com.opencsv.CSVWriter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsExtracter {

    private File[] files;

    //crea un file csv con tutte le statistiche raccolte
    public void createCSV() throws IOException{

        //crea la cartella statistics se non esiste
        String nameDir = System.getProperty("user.home") + File.separator + ".casper" + File.separator + "statistics";
        File dir = new File(nameDir);
        dir.mkdir();

        //formatto la data per il nome del file
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        String fileName = dateFormat.format(date);

        FileWriter outputFile = new FileWriter(nameDir + File.separator + fileName + ".csv");

        CSVWriter writer = new CSVWriter(outputFile);

        //questa istruzione permette al file di ottimizzare la visualizzazione del file con MS Excel;
        //imposta la virgola (,) come separatore, in modo che i dati in Excel appaiano in colonne separate
        writer.writeNext(new String[] {"sep=,"});

        // aggiungo gli header al csv
        String[] header = { "fileName","viewTime", "executionTime", "noSolutionPromiscuousPackage",
                "noSolutionBlobPage", "noSolutionDivergentChangePage", "refactoring", "blobSmellNum",
                "misplacedClassSmellNum", "divergentChangeSmellNum", "shotgunSurgerySmellNum",
                "parallelInheritanceSmellNum", "promiscuousPackageSmellNum", "featureEnvySmellNum"};
        writer.writeNext(header);

        // leggo tutti i file .txt nella cartella
        files = dir.listFiles();

        for (File file: files){
            if (file.getName().endsWith(".txt")){
                FileReader f = new FileReader(file);
                BufferedReader b = new BufferedReader(f);

                String name = file.getName();
                // l'array 'values' deve contenere i valori scritti nell'header, quindi uso la sua length ù
                String[] values = new String[header.length];
                values[0] = name; //il primo valore dell'array è il nome del file, gli altri li leggo dal file .txt
                int i = 1;
                while(b.ready()) {
                    String line=b.readLine();
                    //prendo il valore dopo il carattere '='
                    line = line.substring(line.lastIndexOf("=") + 1);
                    values[i] = line;
                    i++;
                }
                writer.writeNext(values);
            }
        }

        // chiudo il file
        writer.close();
    }



}
