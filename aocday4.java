import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aocday4
{
    static LinkedList<Integer> codes = new LinkedList<Integer>();
    public static void main(String args[]) throws Exception
    {
        int total = 0;
        String COMMA_DELIMITER = "-";
        File file = new File("C:\\Users\\joe\\Documents\\aocday4.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        while ((st = reader.readLine()) != null)
        {
            for (String string : st.split(COMMA_DELIMITER))
            {
                codes.add(Integer.parseInt(string));
            }
        };
        for(Integer i = codes.get(0); i < codes.get(1); i++)
        {
            int[]digits = Integer.toString(i).chars().map(c -> c-'0').toArray();
            String match = Integer.toString(i);
            Pattern p = Pattern.compile("(?<!0)[0]{2}(?!0)|(?<!1)[1]{2}(?!1)|(?<!2)[2]{2}(?!2)|(?<!3)[3]{2}(?!3)|(?<!4)[4]{2}(?!4)|(?<!5)[5]{2}(?!5)|(?<!6)[6]{2}(?!6)|(?<!7)[7]{2}(?!7)|(?<!8)[8]{2}(?!8)|(?<!9)[9]{2}(?!9)");
            Matcher m = p.matcher(match);
            if(isSorted(digits) && m.find())
            {
                total++;
            }
        }
        System.out.println(total);
    }
    static boolean isSorted(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false; 
            }
        }

        return true; 
    }
    static int[] toIntArray(List<Integer> list){
        int[] ret = new int[list.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = list.get(i);
        return ret;
    }

}
