import java.io.*;
import java.awt.event.*;
import java.util.regex.*;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.TitledBorder;
import javax.swing.Action;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;

class SlangDictionary_PA01 {
    public static HashMap<String, List<String>> slang = new HashMap<String, List<String>>();
    public static Scanner word = new Scanner(System.in);
    public static List<String> History = new LinkedList<String>();

    public static void getFromTxt() {
        try {
            File f = new File("./slang.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("`")) {
                    String[] s = line.split("`");
                    String[] tmp = s[1].split("\\|");
                    List<String> temp = Arrays.asList(tmp);
                    slang.put(s[0], temp);
                }
            }
            fr.close();
            br.close();
        } catch (Exception ex) {
            System.out.println("ERROR" + ex);
        }
    }

    public static JLabel slangLabel = new JLabel("Slang       ");
    public static JTextField slangField = new JTextField("", 20);
    public static JLabel defLabel = new JLabel("Definition");
    public static JTextField defField = new JTextField("", 20);
    public static JLabel slangLabel1 = new JLabel("Slang       ");
    public static JTextField slangField1 = new JTextField("", 20);
    public static JLabel defLabel1 = new JLabel("Definition");
    public static JTextField defField1 = new JTextField("", 20);
    public static JLabel keywordLabel = new JLabel("Keyword");
    public static JComboBox keyBox;
    public static JTextField keywordField = new JTextField("", 20);
    public static JButton searchButton = new JButton("Search");
    public static JButton addButton = new JButton("Add");
    public static JButton editButton = new JButton("Edit");
    public static JButton delButton = new JButton("Delete");
    public static JButton ranButton = new JButton("Random");
    public static JButton resetButton = new JButton("Reset to default");
    public static JButton historyButton = new JButton("Searching history");
    public static JButton aButton = new JButton("A");
    public static JButton bButton = new JButton("B");
    public static JButton cButton = new JButton("C");
    public static JButton dButton = new JButton("D");
    public static JTextField aField = new JTextField("", 20);
    public static JTextField bField = new JTextField("", 20);
    public static JTextField cField = new JTextField("", 20);
    public static JTextField dField = new JTextField("", 20);
    public static JComboBox quizBox;
    public static JButton letsquiz = new JButton("Let's Quiz");
    public static DefaultTableModel model;
    public static int rowsize;
    public static List<String> listOfKeys;
    public static String[][] data;
    public static int answer;
    public static String a,b,c,d, ans, question;
    public static JLabel ques = new JLabel("Press button to play!!");


    public static JTable table;

    public static void getdataToJtable(HashMap<String, List<String>> sl) {
        Set<String> keySet = sl.keySet();
        listOfKeys = new ArrayList<String>(keySet);
        rowsize = listOfKeys.size();
        // Getting Collection of values from HashMap
        // Collection<List<String>> values = slang.values();
        // List<List<String>> listOfValues = new ArrayList<>(values);
        // System.out.println("The Keys of the Map are " + listOfKeys);
        // System.out.println("The Values of the Map are " + listOfValues);
        int i = 0;
        data = new String[rowsize][2];
        for (Map.Entry<String, List<String>> set : sl.entrySet()) {
            data[i][0] = set.getKey();
            data[i][1] = String.join(",", set.getValue());
            i++;
        }
        String[] columnNames = { "Slang", "Definition" };
        model = new DefaultTableModel(data, columnNames) {
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
    }

    public static void UI_layout() {
        // Create and set up a frame window
        JFrame frame = new JFrame("Slang Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFrame historyFrame = new JFrame("Search History");

        table = new JTable(model);

        // table.setEnabled(false);
        String[] combobox = { "Slang", "Definition" };
        keyBox = new JComboBox<>(combobox);
        quizBox = new JComboBox<>(combobox);
        // Button
        searchButton.setFocusable(false);
        addButton.setFocusable(false);
        ranButton.setFocusable(false);
        editButton.setFocusable(false);
        delButton.setFocusable(false);
        resetButton.setFocusable(false);
        historyButton.setFocusable(false);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        slangField1.setEditable(false);
        defField1.setEditable(false);

        // Define the panel
        JPanel mainPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel historyPanel = new JPanel();
        JPanel rightFlow = new JPanel();
        JPanel leftFlow1 = new JPanel();
        JPanel leftFlow2 = new JPanel();
        JPanel leftFlow3 = new JPanel();
        JPanel quiz = new JPanel();
        JPanel flow2 = new JPanel();
        JPanel typeIn1 = new JPanel();
        JPanel typeIn2 = new JPanel();
        JPanel typeIn3 = new JPanel();
        JPanel typeIn4 = new JPanel();
        JPanel typeIn5 = new JPanel();
        JPanel typeIn6 = new JPanel();
        JPanel typeIn7 = new JPanel();
        JPanel typeIn8 = new JPanel();
        JPanel typeIn9 = new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.setLayout(new GridLayout(1, 2));
        leftPanel.setBorder(new TitledBorder("Functions"));
        rightPanel.setBorder(new TitledBorder("Slang List"));
        historyPanel.setBorder(new TitledBorder("History"));
        historyPanel.setLayout(new GridLayout(1, 1));
        leftFlow1.setBorder(new TitledBorder("Add, edit, delete"));
        // leftFlow1.setLayout(new GridLayout(3,1));
        leftFlow1.setLayout(gridBagLayout);
        leftFlow2.setBorder(new TitledBorder("Search"));
        leftFlow2.setLayout(gridBagLayout);
        leftFlow3.setBorder(new TitledBorder("On this day slang word"));
        leftFlow3.setLayout(gridBagLayout);
        quiz.setBorder(new TitledBorder("Quiz"));
        quiz.setLayout(gridBagLayout);
        BoxLayout boxlayout = new BoxLayout(rightPanel, BoxLayout.Y_AXIS);
        rightPanel.setLayout(boxlayout);
        leftPanel.setLayout(new GridLayout(4, 1));
        // leftPanel.setLayout(new BoxLayout());
        // BoxLayout boxlayout2 = new BoxLayout(leftPanel, BoxLayout.Y_AXIS);
        // leftPanel.setLayout(boxlayout2);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        typeIn1.add(slangLabel);
        typeIn1.add(slangField);
        typeIn2.add(defLabel);
        typeIn2.add(defField);
        typeIn3.add(addButton);
        typeIn3.add(editButton);
        typeIn3.add(delButton);
        leftFlow1.add(typeIn1, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow1.add(typeIn2, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow1.add(typeIn3, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        flow2.add(keywordLabel);
        flow2.add(keywordField);
        flow2.add(keyBox);
        flow2.add(searchButton);
        leftFlow2.add(flow2, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        typeIn4.add(slangLabel1);
        typeIn4.add(slangField1);
        typeIn5.add(defLabel1);
        typeIn5.add(defField1);
        typeIn6.add(ranButton);
        leftFlow3.add(typeIn4, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow3.add(typeIn5, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        leftFlow3.add(typeIn6, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        typeIn7.add(letsquiz);
        typeIn7.add(quizBox);
        typeIn7.add(ques);
        typeIn8.add(aButton);
        typeIn8.add(aField);
        typeIn8.add(bButton);
        typeIn8.add(bField);
        typeIn9.add(cButton);
        typeIn9.add(cField);
        typeIn9.add(dButton);
        typeIn9.add(dField);
        quiz.add(typeIn7, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        quiz.add(typeIn8, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
        quiz.add(typeIn9, new GridBagConstraints(0, 5, 1, 1, 1.0, 1.0, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        leftPanel.add(leftFlow2);
        leftPanel.add(leftFlow1);
        leftPanel.add(leftFlow3);
        leftPanel.add(quiz);

        rightFlow.add(resetButton);
        rightFlow.add(historyButton);

        rightPanel.add(table);
        rightPanel.add(new JScrollPane(table));
        rightPanel.add(rightFlow);

        JList historyList;
        DefaultListModel listModel = new DefaultListModel();

        // 1. Search
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = keywordField.getText();
                listModel.add(0, text);
                String s = (String) keyBox.getSelectedItem();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        if (s == "Slang") {
                            text = text.toUpperCase();
                            sorter.setRowFilter(RowFilter.regexFilter(text, 0));
                            keywordField.setText("");
                        }
                        if (s == "Definition") {
                            sorter.setRowFilter(RowFilter.regexFilter(text, 1));
                            keywordField.setText("");
                        }
                    } catch (PatternSyntaxException pse) {
                        System.out.println("Bad regex pattern");
                    }
                }
            }
        });

        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        // 2. Add
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sl = slangField.getText().toUpperCase();
                String def = defField.getText();
                if (sl.length() == 0 || def.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please fill out the information!");
                } else {

                    dtm.addRow(new Object[] { sl, def });
                    slangField.setText("");
                    defField.setText("");
                    JOptionPane.showMessageDialog(frame, "Slang added successfully!");

                }
            }
        });

        // 3. Delete
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sl = slangField.getText().toUpperCase();
                int selectedRow;
                if (sl.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please insert slang to delete!");
                } else {

                    int click = JOptionPane.showConfirmDialog(null, "You want to delete " + sl + " ?");
                    if (click == JOptionPane.YES_OPTION) {
                        selectedRow = getRowByValue(dtm, sl);
                        if (selectedRow < 0) {
                            JOptionPane.showMessageDialog(frame, "Can't find slang");
                        }
                        dtm.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(frame, "Slang deleted successfully!");
                    }

                }
            }
        });

        // 4. Edit
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sl = slangField.getText().toUpperCase();
                String def = defField.getText();
                int selectedRow;
                if (sl.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Please insert slang to edit!");
                } else {

                    selectedRow = getRowByValue(dtm, sl);
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(frame, "Can't find slang");
                    }
                    dtm.setValueAt(sl, selectedRow, 0);
                    dtm.setValueAt(def, selectedRow, 1);
                    JOptionPane.showMessageDialog(frame, "Slang edited successfully!");
                }
            }
        });

        // 5.Random
        Random rand = new Random();
        int rand1 = rand.nextInt(data.length);
        slangField1.setText(data[rand1][0]);
        defField1.setText(data[rand1][1]);
        ranButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int rand2 = rand.nextInt(data.length);
                slangField1.setText(data[rand2][0]);
                defField1.setText(data[rand2][1]);
            }
        });

        // 6. Reset
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sorter.setRowFilter(null);
                while (dtm.getRowCount() > 0) {
                    dtm.removeRow(0);
                }
                int i = 0;
                while (i < rowsize) {
                    dtm.addRow(new Object[] { data[i][0], data[i][1] });
                    i++;
                }
            }
        });

        // 7. History
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JList historyList = new JList(listModel);
                historyFrame.add(historyList);
                historyFrame.setSize(300, 300);
                historyFrame.setVisible(true);
                // System.out.println(History);
            }
        });

        // 8. Quiz
        
        letsquiz.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = (String) quizBox.getSelectedItem();
                
                if (s == "Slang") {
                    answer = rand.nextInt(3);
                    answer +=1;
                    int rand1 = rand.nextInt(data.length);
                    int rand2 = rand.nextInt(data.length);
                    int rand3 = rand.nextInt(data.length);
                    int rand4 = rand.nextInt(data.length);
                    aField.setText(data[rand1][1]);
                    a = data[rand1][0];
                    String a1 = data[rand1][1];
                    bField.setText(data[rand2][1]);
                    b = data[rand2][0];
                    String b1 = data[rand2][1];
                    cField.setText(data[rand3][1]);
                    c = data[rand3][0];
                    String c1 = data[rand2][1];
                    dField.setText(data[rand4][1]);
                    d = data[rand4][0];
                    String d1 = data[rand2][1];
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    list.add(d);
                    ArrayList<String> list1 = new ArrayList<String>();
                    list1.add(a1);
                    list1.add(b1);
                    list1.add(c1);
                    list1.add(d1);
                    ans = list.get(answer);
                    ques.setText("What is the definition of " + list1.get(answer) + " ?");
                }
                if (s == "Definition") {
                    answer = rand.nextInt(3);
                    answer +=1;
                    int rand1 = rand.nextInt(data.length);
                    int rand2 = rand.nextInt(data.length);
                    int rand3 = rand.nextInt(data.length);
                    int rand4 = rand.nextInt(data.length);
                    aField.setText(data[rand1][0]);
                    a = data[rand1][1];
                    String a1 = data[rand1][0];
                    bField.setText(data[rand2][0]);
                    b = data[rand2][1];
                    String b1 = data[rand1][0];
                    cField.setText(data[rand3][0]);
                    c = data[rand3][1];
                    String c1 = data[rand1][1];
                    dField.setText(data[rand4][0]);
                    d = data[rand4][1];
                    String d1 = data[rand1][1];
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(a);
                    list.add(b);
                    list.add(c);
                    list.add(d);
                    ArrayList<String> list1 = new ArrayList<String>();
                    list1.add(a1);
                    list1.add(b1);
                    list1.add(c1);
                    list1.add(d1);
                    ans = list.get(answer);
                    ques.setText("What is the slang of " + list1.get(answer) + " ?");
                }
            }
            

        });
        aButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ans == a){
                    JOptionPane.showMessageDialog(frame, "Correct");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Incorrect");
                }
            }
        });
        bButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ans == b){
                    JOptionPane.showMessageDialog(frame, "Correct");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Incorrect");
                }
            }
        });
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ans == c){
                    JOptionPane.showMessageDialog(frame, "Correct");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Incorrect");
                }
            }
        });
        dButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (ans == d){
                    JOptionPane.showMessageDialog(frame, "Correct");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Incorrect");
                }
            }
        });
        // Set the window to be visible as the default to be false
        frame.add(mainPanel);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    public static int getRowByValue(TableModel model, Object value) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if (model.getValueAt(i, j).equals(value)) {
                    // what if value is not unique?
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        getFromTxt();
        getdataToJtable(slang);
        UI_layout();
    }

}