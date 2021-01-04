package catering_project;

public class Waiter implements Runnable {

    private Tray borekTray;
    private Tray cakeTray;
    private Tray drinkTray;  
    
    public Waiter(Tray foodTray, Tray cakeTray, Tray drinkTray){
        this.borekTray=foodTray;
        this.cakeTray=cakeTray;
        this.drinkTray=drinkTray;
    }
    
    @Override
    public void run() {
        while( !(borekTray.getTotalTreat()+borekTray.getOnTable()==0 && cakeTray.getTotalTreat()+cakeTray.getOnTable()==0 && drinkTray.getTotalTreat()+drinkTray.getOnTable()==0) )
            {
                synchronized(this)
                {
                   if(borekTray.getOnTable()<=1 && borekTray.getTotalTreat()>0){
                       borekTray.fillTable();
                   } 
                   else if(cakeTray.getOnTable()<=1 && cakeTray.getTotalTreat()>0){
                       cakeTray.fillTable();
                   } 
                   else if(drinkTray.getOnTable()<=1 && drinkTray.getTotalTreat()>0){
                       drinkTray.fillTable();
                   } 
                } 
                     
            }
        
    }
    
}
