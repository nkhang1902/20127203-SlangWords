import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class SlangDictionary_PA01 {
    public static HashMap<String,List<String>> slang=new HashMap<String,List<String>>();
    public static Scanner word= new Scanner(System.in);

    public static void getFromTxt(){
     try
     {
        File f=new File("./slang.txt");
        FileReader fr=new FileReader(f);
        BufferedReader br=new BufferedReader(fr);
        String line;

        while((line=br.readLine())!=null)
        {
            if (line.contains("`"))
            {
                String[] s=line.split("`");
                String[] tmp=s[1].split("\\|");
                List<String> temp=Arrays.asList(tmp);
                slang.put(s[0],temp);
            }
        }
        fr.close();
        br.close();
    }
    catch (Exception ex)
    {
        System.out.println("ERROR"+ex);
    }
    }

    public static void main(String[] args)
    {
        getFromTxt();
    }
}
