import java.util.*;
import java.lang.*;
import java.io.*;
public class aocday5
{
    static LinkedList<Integer> codes = new LinkedList<Integer>();
    static int input = 5;
    static String jump = "";
    public static void main(String args[]) throws Exception
    {
        String COMMA_DELIMITER = ",";
        File file = new File("C:\\Users\\joe\\Documents\\aocday6test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        while ((st = reader.readLine()) != null)
        {
            for (String string : st.split(COMMA_DELIMITER))
            {
                codes.add(Integer.parseInt(string));
            }
        };
        for(int i =0; i < codes.size();)
        {
            //codes.get(i);
            int addtoi = opcodes(codes.get(i),i);
            if(jump.equals("")) {
                i = i + addtoi;
            }
            else {
                i = addtoi;
                jump = "";
            }
        }

    }
    public static void multiply(int position1, int param1, int position2, int param2, int position3)
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
    public static void add(int position1, int param1, int position2, int param2, int position3)
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
    public static void set(int position1)
    {
        codes.set(position1, input);
    }
    public static void print(int position1, int mode)
    {
        System.out.println("status code");
        if(mode == 0)
            System.out.println(codes.get(position1));
        else
            System.out.println(position1);
    }
    public static int jumpiftrue(int parameter1, int mode1, int parameter2, int mode2)
    {
        int value = 0;
        int value2 = 0;
        if(mode1 == 0)
            value = codes.get(parameter1);
        else
            value = parameter1;
        if(mode2 == 0)
            value2 = codes.get(parameter2);
        else
            value2 = parameter2;
        if(value != 0)
            return value2;
        else
            return 0;

    }
    public static int jumpiffalse(int parameter1, int mode1, int parameter2, int mode2)
    {
        int value = 0;
        int value2 = 0;
        if(mode1 == 0)
            value = codes.get(parameter1);
        else
            value = parameter1;
        if(mode2 == 0)
            value2 = codes.get(parameter2);
        else
            value2 = parameter2;
        if(value == 0)
            return value2;
        else
            return 0;
    }
    public static void lessthen(int parameter1, int mode1, int parameter2, int mode2, int parameter3)
    {
        int value1 = 0;
        int value2 = 0;
        if(mode1 == 0)
            value1 = codes.get(parameter1);
        else
            value1 = parameter1;
        if(mode2 == 0)
            value2 = codes.get(parameter2);
        else
            value2 = parameter2;
        if(value1  < value2) {
            codes.set(parameter3, 1);
        } else
            codes.set(parameter3, 0);
    }
    public static void equals(int parameter1, int mode1, int parameter2, int mode2, int parameter3)
    {
        int value1 = 0;
        int value2 = 0;
        if(mode1 == 0)
            value1 = codes.get(parameter1);
        else
            value1 = parameter1;
        if(mode2 == 0)
            value2 = codes.get(parameter2);
        else
            value2 = parameter2;
        if(value1  == value2) {
            codes.set(parameter3, 1);
        }
        else {
            codes.set(parameter3, 0);
        }
    }
    public static int opcodes(int opcode, int i)
    {

        int opcodevalue = 0;
        int parameter1mode = 0;
        int parameter2mode = 0;
        int parameter3mode = 0;
        String opcodetostring = Integer.toString(opcode);
        if (opcodetostring.length() < 2)
        {
            opcodevalue = opcode;
        }
        if(opcodetostring.length() > 2)
        {
            opcodevalue = Integer.parseInt(opcodetostring.substring(opcodetostring.length() - 2));
            parameter1mode = Integer.parseInt(opcodetostring.substring(opcodetostring.length() - 3, opcodetostring.length() - 2));
        }
        if(opcodetostring.length() > 3)
            parameter2mode = Integer.parseInt(opcodetostring.substring(opcodetostring.length() - 4, opcodetostring.length() - 3));
        if(opcodetostring.length() > 4)
            parameter3mode = Integer.parseInt(opcodetostring.substring(opcodetostring.length() - 5, opcodetostring.length() - 4));
        if(opcodevalue == 1)
        {
            add(codes.get(i+1), parameter1mode, codes.get(i+2), parameter2mode, codes.get(i+3));
            return 4;
        }
        else if(opcodevalue == 2)
        {
            multiply(codes.get(i+1), parameter1mode, codes.get(i+2), parameter2mode, codes.get(i+3));
            return 4;
        }
        else if(opcodevalue == 3)
        {
            set(codes.get(i+1));
            return 2;
        }
        else if(opcodevalue == 4)
        {
            print(codes.get(i+1), parameter1mode);
            return 2;
        }
        else if(opcodevalue == 5)
        {
            int modechange = jumpiftrue(codes.get(i+1),parameter1mode, codes.get(i+2), parameter2mode);
            if(modechange == 0)
                return 3;
            else
            {
                jump = "JMP";
                return modechange;
            }
        }
        else if(opcodevalue == 6)
        {
            int modechange = jumpiffalse(codes.get(i+1),parameter1mode, codes.get(i+2), parameter2mode);
            if(modechange == 0)
                return 3;
            else
            {
                jump = "JMP";
                return modechange;
            }
        }
        else if(opcodevalue == 7)
        {
            lessthen(codes.get(i+1),parameter1mode, codes.get(i+2),parameter2mode, codes.get(i+3));
            return 4;

        }
        else if(opcodevalue == 8)
        {
            equals(codes.get(i+1),parameter1mode, codes.get(i+2),parameter2mode, codes.get(i+3));
            return 4;
        }
        else if(opcode == 99)
        {
            System.out.println("halt");
            System.exit(0);
        }
        else
        {
            System.out.println("oops");
            System.exit(1);
        }
        return 0;
    }
}

