import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class aocday3
{
    public static void main(String args[]) throws Exception
    {
        LinkedList<String> wire1 = new LinkedList<String>();
        LinkedList<String> wire2 = new LinkedList<String>();
        String COMMA_DELIMITER = ",";
        File file = new File("C:\\Users\\joe\\Documents\\aocday3.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        st = reader.readLine();
        Collections.addAll(wire1, st.split(COMMA_DELIMITER));
        st = reader.readLine();
        Collections.addAll(wire2, st.split(COMMA_DELIMITER));
        creategrid(wire1, wire2);


    }
    public static void creategrid(LinkedList<String> wire1, LinkedList<String> wire2)
    {
        int stepstaken = 0;
        int stepstaken2 = 0;
        int currentpoint = 23000;
        int currentpoint2 = 23000;
        LinkedList<String> intersections = new LinkedList<String>();
        LinkedList<String> steps = new LinkedList<String>();
        int[][] array = new int[30000][30000];
        for(String i: wire1)
        {
            String letter = i.substring(0,1);
            String number = i.substring(1);
            if(letter.equals("R"))
            {
                for(int j = 0; j < Integer.parseInt(number); j++)
                {
                    array[currentpoint][currentpoint2+j] = stepstaken;
                    stepstaken++;
                }
                currentpoint2 = currentpoint2 + Integer.parseInt(number);
            }
            else if(letter.equals("D"))
            {
                for(int j = 0; j < Integer.parseInt(number); j++)
                {
                    array[currentpoint+j][currentpoint2] = stepstaken;
                    stepstaken++;

                }
                currentpoint = currentpoint + Integer.parseInt(number);
            }
            else if(letter.equals("U"))
            {
                for(int j = 0; j < Integer.parseInt(number); j++)
                {
                    array[currentpoint-j][currentpoint2] = stepstaken;
                    stepstaken++;

                }
                currentpoint = currentpoint - Integer.parseInt(number);
            }
            else if(letter.equals("L"))
            {
                for(int j = 0; j < Integer.parseInt(number); j++)
                {
                    array[currentpoint][currentpoint2-j] = stepstaken;
                    stepstaken++;

                }
                currentpoint2 = currentpoint2 - Integer.parseInt(number);
            }
            else
            {
                System.out.println("Error");
                System.exit(1);
            }
        }
        currentpoint = 23000;
        currentpoint2 = 23000;
        for(String k: wire2)
        {
            String letter = k.substring(0,1);
            String number = k.substring(1);
            if(letter.equals("R"))
            {
                for(int x = 0; x < Integer.parseInt(number); x++)
                {
                    if(array[currentpoint][currentpoint2+x] != 0)
                    {
                        intersections.add(currentpoint + "," + Integer.toString(currentpoint2+x));
                        steps.add(array[currentpoint][currentpoint2+x] + "," + stepstaken2);
                    }
                    stepstaken2++;
                }
                currentpoint2 = currentpoint2 + Integer.parseInt(number);
            }
            else if(letter.equals("D"))
            {
                for(int x = 0; x < Integer.parseInt(number); x++)
                {
                    if(array[currentpoint+x][currentpoint2] != 0)
                    {
                        intersections.add(Integer.toString(currentpoint+x) + "," + currentpoint2);
                        steps.add(array[currentpoint+x][currentpoint2] + "," + stepstaken2);;

                    }
                    stepstaken2++;
                }
                currentpoint = currentpoint + Integer.parseInt(number);
            }
            else if(letter.equals("U"))
            {
                for(int x = 0; x < Integer.parseInt(number); x++)
                {
                    if(array[currentpoint-x][currentpoint2] != 0)
                    {
                        intersections.add(Integer.toString(currentpoint-x) + "," + currentpoint2);
                        steps.add(array[currentpoint-x][currentpoint2] + "," + stepstaken2);
                    }
                    stepstaken2++;
                }
                currentpoint = currentpoint - Integer.parseInt(number);
            }
            else if(letter.equals("L"))
            {
                for(int x = 0; x < Integer.parseInt(number); x++)
                {
                    if(array[currentpoint][currentpoint2-x] != 0)
                    {
                        intersections.add(currentpoint + "," + Integer.toString(currentpoint2-x));
                        steps.add(array[currentpoint][currentpoint2-x] + "," + stepstaken2);
                    }
                    stepstaken2++;
                }
                currentpoint2 = currentpoint2 - Integer.parseInt(number);
            }
            else
            {
                System.out.println("Error");
                System.exit(1);
            }
        }
        intersections.remove(0);
        findintersection(intersections, 23000, 23000);
        findstepstaken(steps);
    }
    public static void findintersection(LinkedList<String> interactions, int central1, int central2)
    {
            int smallest = Integer.MAX_VALUE;
            for(String i: interactions)
            {
                String[] values = i.split(",");
                int distance = Math.abs(Integer.parseInt(values[0]) - central1) + Math.abs(Integer.parseInt(values[1]) - central2);
                if(distance < smallest) {
                    smallest = distance;
                }
            }
            System.out.println(smallest);
    }
    public static void findstepstaken(LinkedList<String> steps)
    {
        int smallest = Integer.MAX_VALUE;
        for(String i: steps)
        {
            String[] values = i.split(",");
            int distance = Integer.parseInt(values[0]) + Integer.parseInt(values[1]);
            if(distance < smallest) {
                smallest = distance;
            }
        }
        System.out.println(smallest);
    }
}
