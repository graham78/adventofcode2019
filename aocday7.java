import java.util.*;
import java.lang.*;
import java.io.*;
public class aocday7 implements Runnable
{
    LinkedList<Long> codes;
    static LinkedList<String> perms = new LinkedList<String>();
    static LinkedList<Thread> threadlist = new LinkedList<Thread>();
    long input = 2;
    long output = 0;
    String jump = "";
    static volatile int currentthread = 0;
    boolean firsttry = true;
    long relativebase = 0;
    int mode;
    static volatile long defaultvalue = 0;
    static volatile long endvalue = 0;
    static volatile long longestvalue = 0;
    Scanner scanner = new Scanner(System.in);
    public aocday7(int mode, long currentvalue)
    {
        this.mode = mode;
        this.codes = new LinkedList<Long>();
        defaultvalue = currentvalue;
    }
    public aocday7(int mode)
    {
        this(mode, 0);
    }
    public static void main(String args[]) throws Exception
    {
        AllPermutn("56789","");
        for(int i = 0; i < perms.size(); i++)
        {
            Thread program1 = new Thread(new aocday7(Integer.parseInt(perms.get(i).substring(0,1)), endvalue));
            //Thread program1 = new Thread(new aocday7(9, endvalue));
            program1.setName("Thread1");
            threadlist.add(program1);
            program1.start();
            Thread program2 = new Thread(new aocday7(Integer.parseInt(perms.get(i).substring(1,2)), endvalue));
            //Thread program2 = new Thread(new aocday7(7, endvalue));
            program2.setName("Thread2");
            threadlist.add(program2);
            program2.start();
            program2.suspend();
            Thread program3 = new Thread(new aocday7(Integer.parseInt(perms.get(i).substring(2,3)), endvalue));
            //Thread program3 = new Thread(new aocday7(8, endvalue));
            program3.setName("Thread3");
            threadlist.add(program3);
            program3.start();
            program3.suspend();
            Thread program4 = new Thread(new aocday7(Integer.parseInt(perms.get(i).substring(3,4)), endvalue));
            //Thread program4 = new Thread(new aocday7(5, endvalue));
            program4.setName("Thread4");
            threadlist.add(program4);
            program4.start();
            program4.suspend();
            Thread program5 = new Thread(new aocday7(Integer.parseInt(perms.get(i).substring(4,5)), endvalue));
            //Thread program5 = new Thread(new aocday7(6, endvalue));
            program5.setName("Thread5");
            threadlist.add(program5);
            program5.start();
            program5.suspend();
            program5.join();
            if(endvalue > longestvalue)
            {
                longestvalue = endvalue;
            }
            endvalue = 0;
            defaultvalue = 0;
            currentthread = 0;
            threadlist.clear();

        }
        System.out.println(longestvalue);
    }
    public void multiply(long position1, long param1, long position2, long param2, long position3, long param3)
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
    public void add(long position1, long param1, long position2, long param2, long position3, long param3)
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
    public void set(long position1, long mode)
    {
        Scanner scanner = new Scanner(System.in);
        if(firsttry)
        {
            input = this.mode;
            firsttry = false;
        }
        else
        {
            input = defaultvalue;
        }
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
    public void print(long position1, long mode)
    {
        if(mode == 0)
        {
            output = codes.get((int) position1);
            endvalue = output;
        }
        else if(mode == 2)
        {
            output = codes.get((int) (relativebase + position1));
            endvalue = output;
        }
        else
            System.out.println("oops");
        switchthread();
    }
    public long jumpiftrue(long parameter1, long mode1, long parameter2, long mode2)
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
    public long jumpiffalse(long parameter1, long mode1, long parameter2, long mode2)
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
    public void lessthen(long parameter1, long mode1, long parameter2, long mode2, long parameter3,long mode3)
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
    public void equals(long parameter1, long mode1, long parameter2, long mode2, long parameter3, long mode3)
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
    public void setrelativebase(long parameter1, long mode)
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
    public long opcodes(long opcode, long i)
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
            stopandswitch();

        }
        else
        {
            System.out.println("oops");
            System.exit(1);
        }
        return 0;
    }
    public void createcodes()
    {
        String COMMA_DELIMITER = ",";
        File file = new File("C:\\Users\\joean\\Documents\\aocday7.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String st;
            while ((st = reader.readLine()) != null)
            {
                for (String string : st.split(COMMA_DELIMITER))
                {
                    codes.add(Long.parseLong(string));

                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        for(long i = 0; i < 100000; i++)
        {
            codes.add((long) 0);
        }
    }
    public void run()
    {
        endvalue = 0;
        createcodes();
        loopover();
        return;

    }
    public void loopover()
    {
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
    static void AllPermutn(String str, String ans)
    {

        // If string is empty
        if (str.length() == 0) {
            perms.add(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {

            // ith character of str
            char ch = str.charAt(i);

            // Rest of the string after excluding
            // the ith character
            String ros = str.substring(0, i) +
                    str.substring(i + 1);

            // Recurvise call
            AllPermutn(ros, ans + ch);
        }
    }
    public synchronized void switchthread()
    {
        try
        {
            if(currentthread + 1 < threadlist.size())
            {
                currentthread++;
            }
            else
            {
                currentthread = 0;
            }
            defaultvalue = output;
            threadlist.get(currentthread).resume();
            Thread.currentThread().suspend();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public synchronized void stopandswitch()
    {
        if(currentthread != 4)
        {
            currentthread++;
            threadlist.get(currentthread).resume();
            Thread.currentThread().stop();
            defaultvalue = output;
        }
        else
        {
            Thread.currentThread().stop();
        }

    }
}
