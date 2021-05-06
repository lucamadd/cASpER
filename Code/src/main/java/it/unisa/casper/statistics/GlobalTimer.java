package it.unisa.casper.statistics;

public class GlobalTimer {

    private long timeStart;
    private long timeEnd;

    public GlobalTimer(){
        startTime();
    }

    public void startTime(){
        timeStart = System.currentTimeMillis();
    }

    public void stopViewTime(){
        timeEnd = System.currentTimeMillis();
        long viewTimeElapsed =  timeEnd - timeStart;
        StatsCollection stats = StatsCollection.getInstance();
        stats.setViewTime(viewTimeElapsed);
    }

    public void stopExecutionTime(){
        timeEnd = System.currentTimeMillis();
        long executionTimeElapsed =  timeEnd - timeStart;
        StatsCollection stats = StatsCollection.getInstance();
        stats.setExecutionTime(executionTimeElapsed);
    }

}
