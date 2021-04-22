package it.unisa.casper.statistics;

public class GlobalTimer {

    private long globalStart;
    private long globalEnd;

    public GlobalTimer(){
        globalStart = System.currentTimeMillis();
    }

    public void setGlobalStart(long globalStart) {
        this.globalStart = globalStart;
    }

    public void stopTimer(){
        globalEnd = System.currentTimeMillis();
        long globalTimer =  globalEnd - globalStart;
        StatsCollection stats = StatsCollection.getInstance();
        stats.setGlobalTimer(globalTimer);
    }

}
