import java.io.*;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import java.awt.*;
import java.util.ArrayList;

class SlangDictionary_PA01 {
    public static HashMap<String,List<String>> slang=new HashMap<String,List<String>>();
    public static Scanner word= new Scanner(System.in);
    public static List<String> History = new LinkedList<String>();

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

    public static void UI() {
        // Create and set up a frame window
        JFrame frame = new JFrame("Layout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define new buttons
        String[] columnNames = { "Slang", "Definition"};
        Set<String> keySet = slang.keySet();
        List<String> listOfKeys = new ArrayList<String>(keySet);
         // Getting Collection of values from HashMap
        Collection<List<String>> values = slang.values();
        List<List<String>> listOfValues = new ArrayList<>(values);
        //System.out.println("The Keys of the Map are " + listOfKeys);
        //System.out.println("The Values of the Map are " + listOfValues);
        int i=0;
        String[][] data = new String[listOfKeys.size()+100][2];
        for (Map.Entry<String, List<String>> set : slang.entrySet()) {
             data[i][0]= set.getKey();
             data[i][1]= String.join(",",set.getValue());
             i++;
        }
  

        JTable table = new JTable(data,columnNames);
        table.setEnabled(false);

        //Button
        JButton addButton = new JButton("Add");
        JButton ediButton = new JButton("Edit");
        JButton delButton = new JButton("Delete");
        JButton resetButton = new JButton("Reset to default");
        resetButton.setFocusable(false);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JList historyArea = new JList(History.toArray());
        
        // Define the panel
        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel historyPanel = new JPanel();
        JPanel buttonPanel1 = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setLayout(new GridLayout(1,2));
        leftPanel.setBorder(new TitledBorder("Functions"));
        rightPanel.setBorder(new TitledBorder("Slang List"));
        historyPanel.setBorder(new TitledBorder("History"));
        historyPanel.setLayout(new GridLayout(1,1));
        BoxLayout boxlayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout(boxlayout);
        
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        rightPanel.add(table);
        rightPanel.add(new JScrollPane(table));
        rightPanel.add(resetButton);
        rightPanel.add(historyPanel);
        historyPanel.add(historyArea);
        historyPanel.add(new JScrollPane(historyArea));

        // Set the window to be visible as the default to be false
        frame.add(mainPanel);
        frame.setSize(1000, 700);
        frame.setVisible(true);    
    }

    public static void main(String[] args)
    {
        getFromTxt();
        UI();
    }
}
