import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class aocday6
{
    static Map<String, LinkedList<String>> orbits = new HashMap<>();
    public static void main(String args[]) throws Exception
    {
        makelist();
        String younode = makemap();
        findorbits(orbits);
        findsanta(orbits, younode);
    }
    public static void findorbits(Map<String, LinkedList<String>> orbits)
    {
        int totalorbits = 0;
        LinkedList<String> iterator = new LinkedList<String>();
        for(String key: orbits.keySet())
        {
            LinkedList<String> values = orbits.get(key);
            iterator.addAll(values);
            while(!iterator.isEmpty())
            {
                String current = iterator.get(0);
                if(orbits.get(current) != null)
                {
                    for(String keytoadd: orbits.get(current))
                    {
                        iterator.add(keytoadd);
                    }
                }
                totalorbits++;
                iterator.remove(0);
            }
        }
        System.out.println(totalorbits);
    }
    public static void findsanta(Map<String, LinkedList<String>> orbits, String younode)
    {
       LinkedList<String> searchnodes = new LinkedList<String>();
       LinkedList<String> alreadysearched = new LinkedList<String>();
       LinkedList<String> holder = new LinkedList<String >();
       int hops = -1;
       alreadysearched.add("YOU");
       searchnodes.add(younode);
       while(true)
       {
           for(String node: searchnodes)
           {
               if(orbits.get(node) != null)
               {
                    for (String child : orbits.get(node))
                    {
                        if(!alreadysearched.contains(child))
                        {
                            holder.add(child);
                        }
                    }
               }
               for(String parent: orbits.keySet())
               {
                   if(orbits.get(parent).contains(node))
                   {
                       if(!alreadysearched.contains(parent))
                       {
                           holder.add(parent);
                       }
                   }
               }
           }
           if(searchnodes.contains("SAN"))
           {
               System.out.println(hops);
               return;
           }
           hops++;
           alreadysearched.addAll(searchnodes);
           searchnodes.clear();
           searchnodes.addAll(holder);
           holder.clear();
       }
    }
    @NotNull
    public static LinkedList<String> makelist()
    {
        LinkedList<String> orbit = new LinkedList<String>();
        String BRACKETSEPERATOR = "\\)";
        File file = new File("C:\\Users\\joe\\Documents\\aocday6.txt");
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            String[] split;
            while ((line = reader.readLine()) != null)
            {
                orbit.add(line);
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
        return orbit;
    }
    public static String makemap()
    {
        String BRACKETSEPERATOR = "\\)";
        String[] split;
        String younode = "";
        LinkedList<String> items = makelist();
        for(String sorteditem: items)
        {
            split = sorteditem.split(BRACKETSEPERATOR);
            if(orbits.containsKey(split[0]))
            {
                items = orbits.get(split[0]);
                items.add(split[1]);
                orbits.put(split[0], items);
            }
            else
            {
                items = new LinkedList<String>();
                items.add(split[1]);
                orbits.put(split[0], items);
            }
            if(split[1].equals("YOU"))
            {
                younode = split[0];
            }
        }
        return younode;
    }
}
