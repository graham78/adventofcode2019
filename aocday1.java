import java.util.*;
import java.lang.*;
import java.io.*;
public class aocday1 {
    static int total = 0;
    public static void main(String args[]) throws Exception
    {
        int totalmass = 0;
        File file = new File("C:\\Users\\joe\\Documents\\aocday1.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        while ((st= reader.readLine()) != null)
        {
            total = 0;
           totalmass += fuelclass(Integer.parseInt(st));
        }
        System.out.println(totalmass);
    }
    public static int fuelclass(int mass)
    {
        int floored = Math.floorDiv(mass, 3);
        if(floored <= 0)
            return total;
        if(floored - 2 <= 0)
            return total;
        else
            total += floored;
            total -= 2;
            return fuelclass(floored -2);
    }
}
