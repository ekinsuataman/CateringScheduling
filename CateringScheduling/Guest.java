package catering_project;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Guest implements Runnable {
    
    private Tray borekTray;
    private Tray cakeTray;
    private Tray drinkTray;
    private int consumedBorek;
    private int consumedCake;
    private int consumedDrink;
    private boolean keepWaiting;
    private List<Tray> consumableFoodList;
    
    public Guest(Tray borekTray, Tray cakeTray, Tray drinkTray){
        this.borekTray=borekTray;
        this.cakeTray=cakeTray;
        this.drinkTray=drinkTray;
        this.consumedBorek=0;
        this.consumedDrink=0;
        this.consumedCake=0;
        this.keepWaiting=true;
        this.consumableFoodList=new ArrayList<>();
        this.consumableFoodList.add(borekTray);
        this.consumableFoodList.add(cakeTray);
        this.consumableFoodList.add(drinkTray);
    }
    
    @Override
    public void run() {
        
        Random rand = new Random();
        
        try {
            synchronized(this)
            {
                Thread.sleep(rand.nextInt(500));
                takeOneFromAllTray();
                while( !(borekTray.getTotalTreat()+borekTray.getOnTable()==0 && cakeTray.getTotalTreat()+cakeTray.getOnTable()==0 && drinkTray.getTotalTreat()+drinkTray.getOnTable()==0) )
                {
                    if (!(consumedBorek==5 && consumedCake==2 && consumedDrink==5)) {
                       takeSomething();
                       Thread.sleep(rand.nextInt(1000)); 
                    }
                }
                System.out.println(Thread.currentThread().getName() + "------> ConsumedBorek:" + consumedBorek + "--->ConsumedCake:" + consumedCake + "--->ConsumedDrink:" + consumedDrink);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 }
    
    private synchronized void takeOneFromAllTray() throws InterruptedException
    {
        this.take(borekTray);
        this.take(cakeTray);
        this.take(drinkTray);
        
        while (keepWaiting) {            
            if (borekTray.getTotalTreat()<20 && cakeTray.getTotalTreat()<5 && drinkTray.getTotalTreat()<20) {
                keepWaiting=false;
            }
        }
        
    }
    
    private synchronized void takeSomething() throws InterruptedException
    {
        if (consumedBorek>=5 || borekTray.getOnTable()+borekTray.getTotalTreat()==0) {
            consumableFoodList.remove(borekTray);
        }
        if (consumedCake>=2 || cakeTray.getOnTable()+cakeTray.getTotalTreat()==0) {
            consumableFoodList.remove(cakeTray);
        }
        if (consumedDrink>=5 || drinkTray.getOnTable()+drinkTray.getTotalTreat()==0) {
            consumableFoodList.remove(drinkTray);
        }
        
        Random rand=new Random();
        int bound=consumableFoodList.size();
        if (bound>0) {
            int selectOne=rand.nextInt(bound);
            this.take(consumableFoodList.get(selectOne));
        }
    }
    
    private synchronized void take(Tray tray)throws InterruptedException
    {
        if (tray.getOnTable()<=1 && tray.getTotalTreat()!=0) {
            Thread.sleep(100);
        }else{
        try {
                switch (tray.getFoodName()) {
                 case "borek":
                        if (consumedBorek<5) {
                            consumedBorek++;
                            tray.take();
                        }
                         break;
                 case "cake":
                        if (consumedCake<2) {
                            consumedCake++;
                            tray.take();
                         }
                         break;
                 case "drink":
                        if (consumedDrink<5) {
                            consumedDrink++;
                            tray.take();
                         }
                     break;
                 default:
                         break;
                }
        } catch (InterruptedException e) {
             e.printStackTrace();
        } 
        }
    } 
}
