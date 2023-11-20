import Package.*;
import ErrorsPackage.*;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException{
        try {
            transport_vehicle cari = new Car("Marka1", 5);
            transport_vehicle bikei = new Bike("Marka2", 5);

            System.out.println("");
            System.out.println("----------Task1----------");
            System.out.println("toString для Car:"+'\n' + cari.toString());
            System.out.println("toString для Bike:"+'\n' + bikei.toString());

            System.out.println("");
            System.out.println("----------Task4----------");
            Car cariClone = (Car) cari.clone();

            System.out.println("Клонированный Car");
            System.out.println(cariClone);
            try {
                cariClone.setPriceByName("model1", 99999);
            } catch (NoSuchModelNameException | ModelPriceOutOfBoundsException e) {
                System.out.println(e);
            }
            System.out.println("Клонированный Car после изменения");
            System.out.println(cariClone.toString());
            System.out.println(cari.toString());

            Bike bikeiClone = (Bike) bikei.clone();
            System.out.println("Клонированный Bike");
            System.out.println(bikeiClone.toString());
            try {
                bikeiClone.setPriceByName("model1", 99999);
            } catch (NoSuchModelNameException | ModelPriceOutOfBoundsException e) {
                System.out.println(e);
            }
            System.out.println("Клонированный Bike после изменения");
            System.out.println(bikeiClone.toString());
            System.out.println(bikei.toString());
            System.out.println("");
            System.out.println("----------Task2----------");
            boolean logicEquals;
            logicEquals = cari.equals(cariClone);
            if(logicEquals) System.out.println("Автомобили индентичны");
            else System.out.println("Автомобили не индентичны");

            System.out.println("----------Task3----------");
            System.out.println("hashCode Car: " + cari.hashCode());
            System.out.println("hashCode CarClone: " + cariClone.hashCode());
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
    }
}
