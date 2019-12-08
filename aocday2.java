import java.util.*;
import java.lang.*;
import java.io.*;
public class aocday2
{
    static LinkedList<Integer> codes = new LinkedList<Integer>();
    static int input = 1;
    public static void main(String args[]) throws Exception
    {
        String COMMA_DELIMITER = ",";
        File file = new File("C:\\Users\\joe\\Documents\\aocday2.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        while ((st = reader.readLine()) != null)
        {
            for (String string : st.split(COMMA_DELIMITER))
            {
                codes.add(Integer.parseInt(string));
            }
        };
        System.out.println(codes.get(0));
        codes.set(1,12);
        codes.set(2,2);
        for(int i =0; i < codes.size();)
        {
            if(codes.get(i) == 1)
            {
                add(0, codes.get(i+1), 0, codes.get(i+2), codes.get(i+3));
                i = i+4;
            }
            else if(codes.get(i) == 2)
            {
                multiply(0, codes.get(i+1), 0, codes.get(i+2), codes.get(i+3));
                i = i+4;
            }
            else if(codes.get(i) == 99)
            {
                System.out.println("halt");
                break;
            }
        }
        System.out.println(codes.get(0));
    }
    public static void multiply(int param1, int position1, int param2, int position2, int position3)
    {
        int value1;
        int value2;
        if(param1 == 0)
            value1 = codes.get(position1);
        else
            value1 = position1;
         if(param2 == 0)
             value2 = codes.get(position2);
         else
             value2 = position2;
        codes.set(position3, value1 * value2);
    }
    public static void add(int param1, int position1, int param2, int position2, int position3)
    {
        int value1;
        int value2;
        if(param1 == 0)
            value1 = codes.get(position1);
        else
            value1 = position1;
        if(param2 == 0)
            value2 = codes.get(position2);
        else
            value2 = position2;
        codes.set(position3, value1 + value2);
    }
}
