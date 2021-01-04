package catering_project;

public class Catering_project {

    public static void main(String[] args) throws InterruptedException {
       
        
        Tray borekTray = new Tray("borek",30); 
        Tray cakeTray = new Tray("cake",15); 
        Tray drinkTray = new Tray("drink",30); 
        
        Thread guest1=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest1");
        Thread guest2=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest2");
        Thread guest3=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest3");
        Thread guest4=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest4");
        Thread guest5=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest5");
        Thread guest6=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest6");
        Thread guest7=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest7");
        Thread guest8=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest8");
        Thread guest9=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest9");
        Thread guest10=new Thread(new Guest(borekTray,cakeTray,drinkTray),"Guest10");
        Thread waiter=new Thread(new Waiter(borekTray,cakeTray,drinkTray),"Waiter");
        
        waiter.setPriority(10);
        
        waiter.start();
        guest1.start();
        guest2.start();
        guest3.start();
        guest4.start();
        guest5.start();
        guest6.start();
        guest7.start();
        guest8.start();
        guest9.start();
        guest10.start();
        
        waiter.join();
        guest1.join();
        guest2.join();
        guest3.join();
        guest4.join();
        guest5.join();
        guest6.join();
        guest7.join();
        guest8.join();
        guest9.join();
        guest10.join();
        
        System.out.println("Borek: " + borekTray.getTotalTreat() + " --- On table: " + borekTray.getOnTable() );
        System.out.println("Cake: " + cakeTray.getTotalTreat() + " --- On table: " + cakeTray.getOnTable());
        System.out.println("Drink: " + drinkTray.getTotalTreat() + " --- On table: " + drinkTray.getOnTable());
        
    }
    
}
