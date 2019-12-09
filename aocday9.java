import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;
public class aocday9
{
    static LinkedList<Long> codes = new LinkedList<Long>();
    static long input = 2;
    static String jump = "";
    static boolean phase = true;
    static boolean signal = false;
    static long relativebase = 0;
    static long run = 0;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String args[]) throws Exception
    {
        String COMMA_DELIMITER = ",";
        File file = new File("C:\\Users\\joe\\Documents\\aocday9.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String st;
        while ((st = reader.readLine()) != null)
        {
            for (String string : st.split(COMMA_DELIMITER))
            {
                codes.add(Long.parseLong(string));
            }
        };
        for(long i = 0; i < 100000; i++)
        {
            codes.add((long) 0);
        }
        for(long i =0; i < codes.size();)
        {
            //codes.get(i);
            long addtoi = opcodes( codes.get((int) i),i);
            if(jump.equals("")) {
                i = i + addtoi;
            }
            else {
                i = addtoi;
                jump = "";
            }
        }

    }
    public static void multiply(long position1, long param1, long position2, long param2, long position3, long param3)
    {
        long value1;
        long value2;
        long value3;

        if(param1 == 0)
            value1 = codes.get((int) position1);
        else if(param1 == 2)
            value1 = codes.get((int) relativebase + (int) position1);
        else
            value1 = position1;
        if(param2 == 0)
            value2 = codes.get((int) position2);
        else if(param2 == 2)
            value2 = codes.get((int) relativebase + (int) position2);
        else
            value2 = position2;
        if(param3 == 0) {
            value3 = position3;
        }
        else
            value3 = (int) relativebase + (int) position3;
        codes.set((int) value3, value1 * value2);
    }
    public static void add(long position1, long param1, long position2, long param2, long position3, long param3)
    {
        long value1;
        long value2;
        long value3;
        if(param1 == 0)
            value1 = codes.get((int) position1);
        else if(param1 == 2)
            value1 = codes.get((int) relativebase + (int) position1);
        else
            value1 = position1;
        if(param2 == 0)
            value2 = codes.get((int) position2);
        else if(param2 == 2)
            value2 = codes.get((int) relativebase + (int) position2);
        else
            value2 = position2;
        if(param3 == 0)
            value3 = position3;
        else
            value3 = (int) relativebase + (int) position3;
        codes.set((int) value3, value1 + value2);
    }
    public static void set(long position1, long mode)
    {
        long value1 = 0;
        if(mode == 0)
        {
            value1 = position1;
        }
        else
        {
            value1 = (int) (relativebase +  position1);
        }
        codes.set((int) value1, input);
    }
    public static void print(long position1, long mode)
    {
        if(mode == 0)
            System.out.println(codes.get((int) position1));
        else if(mode == 2)
            System.out.println(codes.get((int) (relativebase + position1)));
        else
            System.out.println("oops");
    }
    public static long jumpiftrue(long parameter1, long mode1, long parameter2, long mode2)
    {
        long value = 0;
        long value2 = 0;
        if(mode1 == 0)
            value = codes.get((int) parameter1);
        else if(mode1 == 2)
            value = codes.get((int) (relativebase + parameter1));
        else
            value = parameter1;
        if(mode2 == 0)
            value2 = codes.get((int) parameter2);
        else if(mode2 == 2)
            value2 = codes.get((int) (relativebase + parameter2));
        else
            value2 = parameter2;
        if(value != 0)
            return value2;
        else
            return 0;

    }
    public static long jumpiffalse(long parameter1, long mode1, long parameter2, long mode2)
    {
        long value = 0;
        long value2 = 0;
        if(mode1 == 0)
            value = codes.get((int) parameter1);
        else if(mode1 == 2)
            value = codes.get((int) (relativebase + parameter1));
        else
            value = parameter1;
        if(mode2 == 0)
            value2 = codes.get((int) parameter2);
        else if(mode2 == 2)
            value2 = codes.get((int) (relativebase + parameter2));
        else
            value2 = parameter2;
        if(value == 0)
            return value2;
        else
            return 0;
    }
    public static void lessthen(long parameter1, long mode1, long parameter2, long mode2, long parameter3,long mode3)
    {
        long value1 = 0;
        long value2 = 0;
        long value3 = 0;
        if(mode1 == 0)
            value1 = codes.get((int) parameter1);
        else if(mode1 == 2)
            value1 = codes.get((int) (relativebase + parameter1));
        else
            value1 = parameter1;
        if(mode2 == 0)
            value2 = codes.get((int) parameter2);
        else if(mode2 == 2)
            value2 = codes.get((int) (relativebase + parameter2));
        else
            value2 = parameter2;
        if (mode3 == 0)
            value3 = parameter3;
        else
            value3 = (int) parameter3 + relativebase;
        if(value1  < value2) {
            codes.set((int) value3, (long) 1);
        } else
            codes.set((int) value3, (long) 0);
    }
    public static void equals(long parameter1, long mode1, long parameter2, long mode2, long parameter3, long mode3)
    {
        long value1 = 0;
        long value2 = 0;
        long value3 = 0;
        if(mode1 == 0)
            value1 = codes.get((int) parameter1);
        else if(mode1 == 2)
            value1 = codes.get((int) (relativebase + parameter1));
        else
            value1 = parameter1;
        if(mode2 == 0)
            value2 = codes.get((int) parameter2);
        else if(mode2 == 2)
            value2 = codes.get((int) (relativebase + parameter2));
        else
            value2 = parameter2;
        if (mode3 == 0)
            value3 = parameter3;
        else
            value3 = (int) parameter3 + relativebase;
        if(value1  == value2) {
            codes.set((int) value3, (long) 1);
        }
        else {
            codes.set((int) value3, (long) 0);
        }
    }
    public static void setrelativebase(long parameter1, long mode)
    {
        long value1 = 0;
        if(mode == 0)
            value1 = codes.get((int) parameter1);
        else if(mode == 2)
            value1 = codes.get((int) (relativebase + parameter1));
        else
            value1 = parameter1;
        relativebase = relativebase + value1;
    }
    public static long opcodes(long opcode, long i)
    {
        long opcodevalue = 0;
        long parameter1mode = 0;
        long parameter2mode = 0;
        long parameter3mode = 0;
        String opcodetostring = Long.toString(opcode);
        if (opcodetostring.length() < 2)
        {
            opcodevalue = opcode;
        }
        if(opcodetostring.length() > 2)
        {
            opcodevalue = Long.parseLong(opcodetostring.substring(opcodetostring.length() - 2));
            parameter1mode = Long.parseLong(opcodetostring.substring(opcodetostring.length() - 3, opcodetostring.length() - 2));
        }
        if(opcodetostring.length() > 3)
            parameter2mode = Long.parseLong(opcodetostring.substring(opcodetostring.length() - 4, opcodetostring.length() - 3));
        if(opcodetostring.length() > 4)
            parameter3mode = Long.parseLong(opcodetostring.substring(opcodetostring.length() - 5, opcodetostring.length() - 4));
        if(opcodevalue == 1)
        {
            add(codes.get((int) (i+1)), parameter1mode, codes.get((int) (i+2)), parameter2mode, codes.get((int) (i+3)), parameter3mode);
            return 4;
        }
        else if(opcodevalue == 2)
        {
            multiply(codes.get((int) (i+1)), parameter1mode, codes.get((int) (i+2)), parameter2mode, codes.get((int) (i+3)), parameter3mode);
            return 4;
        }
        else if(opcodevalue == 3)
        {
            set(codes.get((int) (i+1)), parameter1mode);
            return 2;
        }
        else if(opcodevalue == 4)
        {
            print(codes.get((int) (i+1)), parameter1mode);
            return 2;
        }
        else if(opcodevalue == 5)
        {
            long modechange = jumpiftrue(codes.get((int) (i+1)),parameter1mode, codes.get((int) (i+2)), parameter2mode);
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
            long modechange = jumpiffalse(codes.get((int) (i+1)),parameter1mode, codes.get((int) (i+2)), parameter2mode);
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
            lessthen(codes.get((int) (i+1)),parameter1mode, codes.get((int) (i+2)),parameter2mode, codes.get((int) (i+3)), parameter3mode);
            return 4;

        }
        else if(opcodevalue == 8)
        {
            equals(codes.get((int) (i+1)),parameter1mode, codes.get((int) (i+2)),parameter2mode, codes.get((int) (i+3)), parameter3mode);
            return 4;
        }
        else if(opcodevalue == 9)
        {
            setrelativebase(codes.get((int) (i+1)), parameter1mode);
            return 2;
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

