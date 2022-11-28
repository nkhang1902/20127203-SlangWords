import java.io.*;

import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.TitledBorder;

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
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnNames = { "Slang", "Definition"};
        Set<String> keySet = slang.keySet();
        List<String> listOfKeys = new ArrayList<String>(keySet);
        // Getting Collection of values from HashMap
        //Collection<List<String>> values = slang.values();
        //List<List<String>> listOfValues = new ArrayList<>(values);
        //System.out.println("The Keys of the Map are " + listOfKeys);
        //System.out.println("The Values of the Map are " + listOfValues);
        int i=0;
        String[][] data = new String[listOfKeys.size()+50][2];
        for (Map.Entry<String, List<String>> set : slang.entrySet()) {
             data[i][0]= set.getKey();
             data[i][1]= String.join(",",set.getValue());
             i++;
        }
  

        JTable table = new JTable(data,columnNames);
        table.setEnabled(false);

        String[] combobox = {"Slang" , "Definition"};

        //Button
        JLabel slangLabel = new JLabel("Slang       ");
        JTextField slangField = new JTextField("",20);
        JLabel defLabel = new JLabel("Definition");
        JTextField defField = new JTextField("",20);
        JLabel slangLabel1 = new JLabel("Slang       ");
        JTextField slangField1 = new JTextField("",20);
        JLabel defLabel1 = new JLabel("Definition");
        JTextField defField1 = new JTextField("",20);
        JLabel keywordLabel = new JLabel("Keyword");
        JComboBox keyBox = new JComboBox<>(combobox);
        JTextField keywordField = new JTextField("",20);
        JButton searchButton = new JButton("Search");
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton delButton = new JButton("Delete");
        JButton ranButton = new JButton("Random");
        JButton resetButton = new JButton("Reset to default");
        JButton historyButton = new JButton("Searching history");
        addButton.setFocusable(false);
        ranButton.setFocusable(false);
        editButton.setFocusable(false);
        delButton.setFocusable(false);
        resetButton.setFocusable(false);
        historyButton.setFocusable(false);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JList historyList = new JList(History.toArray());
        
        // Define the panel
        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel historyPanel = new JPanel();
        JPanel rightFlow = new JPanel();
        JPanel leftFlow1 = new JPanel();
        JPanel leftFlow2 = new JPanel();
        JPanel leftFlow3 = new JPanel();
        JPanel flow2 = new JPanel();
        JPanel typeIn1 = new JPanel();
        JPanel typeIn2 = new JPanel();
        JPanel typeIn3 = new JPanel();
        JPanel typeIn4 = new JPanel();
        JPanel typeIn5 = new JPanel();
        JPanel typeIn6 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setLayout(new GridLayout(1,2));
        leftPanel.setBorder(new TitledBorder("Functions"));
        rightPanel.setBorder(new TitledBorder("Slang List"));
        historyPanel.setBorder(new TitledBorder("History"));
        historyPanel.setLayout(new GridLayout(1,1));
        leftFlow1.setBorder(new TitledBorder("Add, edit, delete"));
        //leftFlow1.setLayout(new GridLayout(3,1));
        leftFlow1.setLayout(gridBagLayout);
        leftFlow2.setBorder(new TitledBorder("Search"));
        leftFlow2.setLayout(gridBagLayout);
        leftFlow3.setBorder(new TitledBorder("On this day slang word"));
        leftFlow3.setLayout(gridBagLayout);
        BoxLayout boxlayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout(boxlayout);
        leftPanel.setLayout(new GridLayout(5,1));
        //leftPanel.setLayout(new BoxLayout());
        //BoxLayout boxlayout2 = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        //leftPanel.setLayout(boxlayout2);
        
        
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        typeIn1.add(slangLabel);
        typeIn1.add(slangField);
        typeIn2.add(defLabel);
        typeIn2.add(defField);
        typeIn3.add(addButton);
        typeIn3.add(editButton);
        typeIn3.add(delButton);
        leftFlow1.add(typeIn1, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow1.add(typeIn2, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow1.add(typeIn3, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        
        flow2.add(keywordLabel);
        flow2.add(keywordField);
        flow2.add(keyBox);
        flow2.add(searchButton);
        leftFlow2.add(flow2, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        typeIn4.add(slangLabel1);
        typeIn4.add(slangField1);
        typeIn5.add(defLabel1);
        typeIn5.add(defField1);
        typeIn6.add(ranButton);
        leftFlow3.add(typeIn4, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow3.add(typeIn5, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow3.add(typeIn6, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        leftPanel.add(leftFlow2);
        leftPanel.add(leftFlow1);
        leftPanel.add(leftFlow3);

        rightFlow.add(resetButton);
        rightFlow.add(historyButton);

        rightPanel.add(table);
        rightPanel.add(new JScrollPane(table));
        rightPanel.add(rightFlow);
        

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
