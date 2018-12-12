package Test;

import DataBase.VacationTableEntry;
import Vacation.Vacation;

import java.sql.Date;
import java.util.ArrayList;

public class VacationTest {
public static boolean firstTest = true;
//"YYYY-MM-DD HH:MM"
    public static void main(String[] args) {
        VacationTableEntry vacationTableEntry = new VacationTableEntry();
        if(firstTest) firstTest=false;
        else {
            vacationTableEntry.deletCommand(1);
            vacationTableEntry.deletCommand(2);
            vacationTableEntry.deletCommand(3);
            vacationTableEntry.deletCommand(4);
        }

        Date dateFirst = Date.valueOf("2015-12-30");
        //1 Liad
        Date d1departureTime = Date.valueOf("2018-12-30");
        Date d1launchTime = Date.valueOf("2018-12-30");
        Date d1backDepartureTime = Date.valueOf("2019-01-10");
        Date d1backLaunchTime = Date.valueOf("2019-01-10");
        //2 Itzik
        Date d2departureTime = Date.valueOf("2019-07-10");
        Date d2launchTime = Date.valueOf("2019-07-10");
        Date d2backDepartureTime = Date.valueOf("2019-08-10");
        Date d2backLaunchTime = Date.valueOf("2019-08-10");

        Date dateMiddle = Date.valueOf("2020-02-15");

        //3 Itzik
        Date d3departureTime = Date.valueOf("2020-12-30");
        Date d3launchTime = Date.valueOf("2020-12-30");
        //4 Zina
        Date d4departureTime = Date.valueOf("2021-12-30");
        Date d4launchTime = Date.valueOf("2021-12-30");

        Date dateLast = Date.valueOf("2023-12-30");

        //prices for vacations
        double p1 = 120;
        double p2 = 150;
        double p3 = 70;
        double p4 = 40;

        //vacations
        Vacation v1 = new Vacation("Liad","El-Al",d1departureTime,d1launchTime,d1backDepartureTime,d1backLaunchTime,
                                    20,2,"Israel","Colombia","adult",p1);
        Vacation v2 = new Vacation("Itzik","SwissAirLine",d2departureTime,d2launchTime,d2backDepartureTime,d2backLaunchTime,
                40,2,"Israel","Swiss","adult",p2);
        Vacation v3 = new Vacation("Itzik","Avianka",d3departureTime,d3launchTime,null,null,
                15,1,"Israel","Brazil","adult",p3);
        Vacation v4 = new Vacation("Zina","DogyStyle",d4departureTime,d4launchTime,null,null,
                0,1,"Israel","DogsLand","baby",p4);


        System.out.println();
        System.out.println("**** vacations First To Middle ****");
        ArrayList<Vacation> vacationsFirstToMiddle = vacationTableEntry.selectByDates(dateFirst,dateMiddle);
        if (vacationsFirstToMiddle.size() != 0){
            for (Vacation v : vacationsFirstToMiddle)
                System.out.printf(v.toString());
        }
        System.out.println("**** vacations Middle To Last ****");
        ArrayList<Vacation> vacationsMiddleToLast = vacationTableEntry.selectByDates(dateMiddle,dateLast);
        if (vacationsMiddleToLast.size() != 0){
            for (Vacation v : vacationsFirstToMiddle)
                System.out.printf(v.toString());
        }
        System.out.println();
        System.out.println("**** vacations First To Last ****");
        ArrayList<Vacation> vacationsFirstToLast = vacationTableEntry.selectByDates(dateFirst,dateLast);
        if (vacationsFirstToLast.size() != 0){
            for (Vacation v : vacationsFirstToMiddle)
                System.out.printf(v.toString());
        }
        System.out.println();
        System.out.println("**** Zina vacations ****");
        ArrayList<Vacation> itsiksVacations = vacationTableEntry.getMyVacations("Zina");
        if (itsiksVacations.size() != 0){
            for (Vacation v : vacationsFirstToMiddle)
                System.out.printf(v.toString());
        }
        System.out.println("**** Itzik 1 vacations update avalible ****");
        boolean itzik1 = vacationTableEntry.updateAvailable(2,0);
        if(itzik1) System.out.printf("update itsik1 success!");

        System.out.println("**** Itzik 2 vacations update avalible ****");
        boolean itzik2 = vacationTableEntry.updateAvailable(3,0);
        if(itzik2) System.out.printf("update itsik2 success!");
    }
}
