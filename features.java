import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

 class FilterSSCCE extends JPanel
{
    private JTable table;

    public FilterSSCCE()
    {
        setLayout( new BorderLayout() );

        JComboBox<Integer> comboBox = new JComboBox<Integer>();
        comboBox.addItem( new Integer(1) );
        comboBox.addItem( new Integer(2) );
        comboBox.addItem( new Integer(3) );
        comboBox.addItem( new Integer(4) );
        comboBox.addItem( new Integer(5) );
        comboBox.setSelectedIndex(4);

        comboBox.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //System.out.println( table.convertRowIndexToView(4) );
                Integer value = (Integer)comboBox.getSelectedItem();
                newFilter( value );
                //System.out.println( table.convertRowIndexToView(4) );
            }
        });
        add(comboBox, BorderLayout.NORTH);

        table = new JTable(5, 1);

        for (int i = 0; i < table.getRowCount(); i++)
            table.setValueAt(String.valueOf(i+1), i, 0);

        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void newFilter(int numberOfRows)
    {
        System.out.println("Change the Filter to: " + numberOfRows);

        RowFilter<TableModel, Integer> filter = new RowFilter<TableModel, Integer>()
        {
            @Override
            public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry)
            {
                int modelRow = entry.getIdentifier();
                int viewRow = table.convertRowIndexToView(modelRow);

                System.out.println("m" + modelRow + " : v" + viewRow);

                return viewRow < numberOfRows;
            }

        };

        ((TableRowSorter) table.getRowSorter()).setRowFilter(filter);
    }

    private static void createAndShowGUI()
    {
        JPanel panel = new JPanel();

        JFrame frame = new JFrame("FilterSSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new FilterSSCCE());
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}