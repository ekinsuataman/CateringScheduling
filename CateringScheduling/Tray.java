package catering_project;

public class Tray {
    private final String foodName;
    private volatile int totalTreat;
    private volatile int onTable;
    
    
    public Tray(String foodType,int totalTreat){
        this.foodName=foodType;
        this.totalTreat=totalTreat;
        this.onTable=0;
    }
    
    public synchronized String getFoodName()
    {
        return this.foodName;
    }
    
    public synchronized int getTotalTreat()
    {
        return this.totalTreat;
    }
    
    public synchronized int getOnTable()
    {
        return this.onTable;
    }
    
    public synchronized void fillTable()
    {
        if (totalTreat>=5) {
            int transfer=5-onTable;
            onTable+=transfer;
            totalTreat-=transfer;
        }else 
        {
            onTable+=totalTreat;
            totalTreat-=totalTreat;
        }
        System.out.println(Thread.currentThread().getName() + " filled " + this.foodName + " tray.---> Total treat:" + this.totalTreat + "---> On table:" + this.onTable );
    }
    
    
    public synchronized void take() throws InterruptedException
    {
        if (onTable==0) {
            Thread.sleep(100);
        }else
            this.onTable--;
            System.out.println(Thread.currentThread().getName() + " took one " + this.foodName + " from table.---> Total treat:" + this.totalTreat + "---> On table:" + this.onTable);
    }
}
