package it.unisa.casper.statistics;

import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StatsCollection {

    //Singleton pattern
    private static StatsCollection instance = null;

    //cartella dove salvare le statistiche
    private static final String STATS_DIRECTORY = System.getProperty("user.home") + File.separator +
            ".casper" + File.separator + "statistics";

    private long viewTime;
    private long executionTime;

    private int errorDivergentChangePage = 0;
    private int errorBlobPage = 0;
    private int errorPromiscuousPackage = 0;

    //variabile che salva se l'utente ha fatto o meno refactoring dopo l'analisi
    private boolean refactoring = false;

    //variabili che indicano il numero di smell trovati
    private int blobSmellNum = 0;
    private int misplacedClassSmellNum = 0;
    private int divergentChangeSmellNum = 0;
    private int shotgunSurgerySmellNum = 0;
    private int parallelInheritanceSmellNum = 0;
    private int promiscuousPackageSmellNum = 0;
    private int featureEnvySmellNum = 0;



    private StatsCollection() {}

    public static StatsCollection getInstance() {
        if (instance == null) {
            instance = new StatsCollection();
        }
        return instance;
    }

    public void setViewTime(long viewTime) {
        this.viewTime = viewTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }


    //crea il file csv se non esiste
    public void createCSV() throws IOException {

        //crea la cartella statistics se non esiste
        File dir = new File(STATS_DIRECTORY);
        dir.mkdir();

        FileWriter outputFile = new FileWriter(STATS_DIRECTORY + File.separator + "statistics.csv");

        CSVWriter writer = new CSVWriter(outputFile);

        //questa istruzione permette al file di ottimizzare la visualizzazione del file con MS Excel;
        //imposta la virgola (,) come separatore, in modo che i dati in Excel appaiano in colonne separate
        writer.writeNext(new String[] {"sep=,"});

        // aggiungo gli header al csv
        String[] header = { "executionDate","viewTime", "executionTime", "noSolutionPromiscuousPackage",
                "noSolutionBlobPage", "refactoring", "blobSmellNum", "misplacedClassSmellNum",
                "promiscuousPackageSmellNum", "featureEnvySmellNum"};

        /*  //questi header contengono anche i 3 smell aggiuntivi
        String[] header = { "executionDate","viewTime", "executionTime", "noSolutionPromiscuousPackage",
                "noSolutionBlobPage", "noSolutionDivergentChangePage", "refactoring", "blobSmellNum",
                "misplacedClassSmellNum", "divergentChangeSmellNum", "shotgunSurgerySmellNum",
                "parallelInheritanceSmellNum", "promiscuousPackageSmellNum", "featureEnvySmellNum"};
         */
        writer.writeNext(header);


        // chiudo il file
        writer.close();
    }

    public void saveAll() throws IOException{

        //crea il file csv se non esiste
        System.out.println("TEST" + !new File(STATS_DIRECTORY + File.separator + "statistics.csv").isFile());
        if (!new File(STATS_DIRECTORY + File.separator + "statistics.csv").isFile()){
            createCSV();
        }


        //formatto la data per L'identificativo dell'esecuzione
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        Date date = new Date();
        String executionDate = dateFormat.format(date);

        //aggiungo una riga al file
        try {
            FileWriter f = new FileWriter(STATS_DIRECTORY + File.separator + "statistics.csv", true);
            CSVWriter writer = new CSVWriter(f);

            String[] nextLine = {executionDate, String.valueOf(viewTime), String.valueOf(executionTime),
                    String.valueOf(errorPromiscuousPackage), String.valueOf(errorBlobPage), (refactoring ? "1" : "0"),
                    String.valueOf(blobSmellNum), String.valueOf(misplacedClassSmellNum),
                    String.valueOf(promiscuousPackageSmellNum), String.valueOf(featureEnvySmellNum)};

            /*   //queste righe contengono anche i 3 smell aggiuntivi
            String[] nextLine = {executionDate, String.valueOf(viewTime), String.valueOf(executionTime),
                    String.valueOf(errorPromiscuousPackage), String.valueOf(errorBlobPage), errorDivergentChangePage,
                    (refactoring ? "1" : "0"), String.valueOf(blobSmellNum), String.valueOf(misplacedClassSmellNum),
                    divergentChangeSmellNum, shotgunSurgerySmellNum, parallelInheritanceSmellNum,
                    String.valueOf(promiscuousPackageSmellNum), String.valueOf(featureEnvySmellNum)};
             */

            writer.writeNext(nextLine);

            writer.flush();


            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //resetto le variabili quando chiudo il tool
        resetAll();
    }

    //incrementa il contatore di soluzioni non trovate per DivergentChangePage
    public void addErrorDivergentChangePage(){
        errorDivergentChangePage += 1;
    }
    //incrementa il contatore di soluzioni non trovate per BlobPage
    public void addErrorBlobPage(){
        errorBlobPage += 1;
    }
    //incrementa il contatore di soluzioni non trovate per PromiscuousPackage
    public void addErrorPromiscuousPackage(){
        errorPromiscuousPackage += 1;
    }

    //imposta al valore di default le variabili interessate
    private void resetAll(){
        errorDivergentChangePage = 0;
        errorBlobPage = 0;
        errorPromiscuousPackage = 0;
        misplacedClassSmellNum = 0;
        divergentChangeSmellNum = 0;
        shotgunSurgerySmellNum = 0;
        parallelInheritanceSmellNum = 0;
        promiscuousPackageSmellNum = 0;
        featureEnvySmellNum = 0;
    }

    //imposta refactoring a true se l'utente esegue il refactoring del codice dopo l'analisi
    public void doRefactoring(){
        this.refactoring = true;
    }

    public void setBlobSmellNum(int blobSmellNum) {
        this.blobSmellNum = blobSmellNum;
    }

    public void setMisplacedClassSmellNum(int misplacedClassSmellNum) {
        this.misplacedClassSmellNum = misplacedClassSmellNum;
    }

    public void setDivergentChangeSmellNum(int divergentChangeSmellNum) {
        this.divergentChangeSmellNum = divergentChangeSmellNum;
    }

    public void setShotgunSurgerySmellNum(int shotgunSurgerySmellNum) {
        this.shotgunSurgerySmellNum = shotgunSurgerySmellNum;
    }

    public void setParallelInheritanceSmellNum(int parallelInheritanceSmellNum) {
        this.parallelInheritanceSmellNum = parallelInheritanceSmellNum;
    }

    public void setPromiscuousPackageSmellNum(int promiscuousPackageSmellNum) {
        this.promiscuousPackageSmellNum = promiscuousPackageSmellNum;
    }

    public void setFeatureEnvySmellNum(int featureEnvySmellNum) {
        this.featureEnvySmellNum = featureEnvySmellNum;
    }
}
