package Package;

import java.io.*;
import ErrorsPackage.*;
public class StaticClass {
    public static double averagePrice(transport_vehicle transport){
        double s = 0;

        for (double i: transport.getPriceMass()) {
            s += i;
        }
        return s / transport.getLenModelMass();
    }

    public static void printNameModels(transport_vehicle transport){
        for (String s: transport.getModelNameMass()) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    public static void printPriseModels(transport_vehicle transport){
        double[] db = transport.getPriceMass();
        for (int i = 0; i < db.length; i ++){
            System.out.print(db[i] + " ");
        }
        System.out.println();
    }
    public static void outputTransportVehicle(transport_vehicle v, OutputStream out) throws IOException, NoSuchModelNameException{
        DataOutputStream stream = new DataOutputStream(out);//getClass

        /*ДОБАВЛЕН getClass*/
        byte[] arrayClass = v.getClass().getName().getBytes();
        stream.writeInt(arrayClass.length);
        for (int i = 0; i < arrayClass.length; i++)
        {
            stream.writeByte(arrayClass[i]);
        }

        byte[] arrayBrand = v.getBrand().getBytes();
        stream.writeInt(arrayBrand.length);
        for (int i = 0; i < arrayBrand.length; i++)
        {
            stream.writeByte(arrayBrand[i]);
        }

        stream.writeInt(v.getLenModelMass());
        String[] modelNameMass = v.getModelNameMass();
        for (int i = 0; i < modelNameMass.length; i++)
        {
            byte[] arrayModel = modelNameMass[i].getBytes();
            stream.writeInt(arrayModel.length);
            for (int j = 0; j < arrayModel.length; j++)
            {
                stream.writeByte(arrayModel[j]);
            }
            stream.writeDouble(v.getPriceByName(modelNameMass[i]));
        }
    }
    public static transport_vehicle inputTransportVehicle(InputStream in) throws IOException, DuplicateModelNameException, NoSuchModelNameException {
        DataInputStream stream = new DataInputStream(in);

        int lenClass = stream.readInt();
        byte[] arrayClass = new byte[lenClass];
        for (int i = 0; i < arrayClass.length; i++)
        {
            arrayClass[i] = stream.readByte();
        }
        String nameClass = new String(arrayClass);



        int lenBrand = stream.readInt();
        byte[] arrayBrand = new byte[lenBrand];
        for(int i = 0; i < lenBrand; ++i) {
            arrayBrand[i] = stream.readByte();
        }

        String nameBrand = new String(arrayBrand);
        int models = stream.readInt();

        // if ДОБАВЛЕН
        transport_vehicle v;
        if (nameClass.equals("Car")){
            v = new Car(nameBrand, 0);
        } else{
            v = new Bike(nameBrand, 0);
        }
        for(int i = 0; i < models; ++i) {
            int lenModels = stream.readInt();
            byte[] arrayModel = new byte[lenModels];

            for(int j = 0; j < lenModels; ++j) {
                arrayModel[j] = stream.readByte();
            }
            double price = stream.readDouble();
            String model = new String(arrayModel);
            v.addNamePriceModel(model, price);
        }
        return v;
    }
    public static void writeTransportVehicle(transport_vehicle v, Writer out) {
        PrintWriter pW = new PrintWriter(out);
        pW.println(v.getClass().getName());
        pW.println(v.getBrand());//getClass
        pW.println(v.getLenModelMass());

        for(int i = 0; i < v.getLenModelMass(); ++i) {
            pW.println(v.getModelNameMass()[i]);
            pW.println(v.getModelPriceMass()[i]);
        }
        pW.flush();
    }
    public static transport_vehicle readTransportVehicle(Reader in) throws IOException, DuplicateModelNameException, NoSuchModelNameException
    {
        BufferedReader bfReader = new BufferedReader(in);
        String nameClass = bfReader.readLine();
        String nameBrand = bfReader.readLine();
        double price;
        String modelName;
        int size = Integer.parseInt(bfReader.readLine());
        transport_vehicle vehicle;
        if (nameClass.equals("Car")){
            vehicle = new Car(nameBrand, 0);
        } else{
            vehicle = new Bike(nameBrand, 0);
        }
        for (int i = 0; i < size; i++)
        {
            modelName = bfReader.readLine();
            price = Double.parseDouble(bfReader.readLine());
            vehicle.addNamePriceModel(modelName, price);
        }
        return vehicle;
    }

}
