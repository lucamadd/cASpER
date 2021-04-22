package it.unisa.casper.statistics;

public class GlobalTimer {

    private long viewTimeStart;
    private long viewTimeEnd;

    private long executionTimeStart;
    private long executionTimeEnd;

    public GlobalTimer(){
        startViewTimer();
        startExecutionTimer();
    }

    public void startViewTimer(){
        viewTimeStart = System.currentTimeMillis();
    }

    public void stopViewTimer(){
        viewTimeEnd = System.currentTimeMillis();
        long viewTimeElapsed =  viewTimeEnd - viewTimeStart;
        StatsCollection stats = StatsCollection.getInstance();
        stats.setViewTime(viewTimeElapsed);
    }

    public void startExecutionTimer(){
        executionTimeStart = System.currentTimeMillis();
    }

    public void stopExecutionTimer(){
        executionTimeEnd = System.currentTimeMillis();
        long executionTimeElapsed =  executionTimeEnd - executionTimeStart;
        StatsCollection stats = StatsCollection.getInstance();
        stats.setExecutionTime(executionTimeElapsed);
    }

}
