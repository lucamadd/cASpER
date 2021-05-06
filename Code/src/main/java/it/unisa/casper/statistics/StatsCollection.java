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
            //scrivo su file il view time
            out.write("viewTime=" + viewTime);
            out.flush();
            out.newLine();
            //scrivo su file il tempo di esecuzione
            out.write("executionTime=" + executionTime);
            out.flush();
            out.newLine();
            //scrivo su file il numero di volte in cui non sono state trovate soluzioni
            out.write("noSolutionPromiscuousPackage=" + errorPromiscuousPackage);
            out.flush();
            out.newLine();
            out.write("noSolutionBlobPage=" + errorBlobPage);
            out.flush();
            out.newLine();
            /* //DIVERGENT CHANGE NOT INCLUDED IN FIRST 4 SMELLS
            out.write("noSolutionDivergentChangePage=" + errorDivergentChangePage);
            out.flush();
            out.newLine();
             */
            //scrivo su file se l'utente ha effettuato refactoring dopo l'analisi
            if (refactoring){
                out.write("refactoring=" + 1);
                out.flush();
                out.newLine();
            } else {
                out.write("refactoring=" + 0);
                out.flush();
                out.newLine();
            }
            //scrivo su file il numero di smell trovati per ogni tipologia
            out.write("blobSmellNum=" + blobSmellNum);
            out.flush();
            out.newLine();
            out.write("misplacedClassSmellNum=" + misplacedClassSmellNum);
            out.flush();
            out.newLine();
            /*  //DIVERGENT CHANGE NOT INCLUDED IN FIRST 4 SMELLS
            out.write("divergentChangeSmellNum=" + divergentChangeSmellNum);
            out.flush();
            out.newLine();
             */
            /*  //SHOTGUN SURGERY NOT INCLUDED IN FIRST 4 SMELLS
            out.write("shotgunSurgerySmellNum=" + shotgunSurgerySmellNum);
            out.flush();
            out.newLine();
             */
            /*  //PARALLEL INHERITANCE NOT INCLUDED IN FIRST 4 SMELLS
            out.write("parallelInheritanceSmellNum=" + parallelInheritanceSmellNum);
            out.flush();
            out.newLine();
             */
            out.write("promiscuousPackageSmellNum=" + promiscuousPackageSmellNum);
            out.flush();
            out.newLine();
            out.write("featureEnvySmellNum=" + featureEnvySmellNum);
            out.flush();
            out.newLine();
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
