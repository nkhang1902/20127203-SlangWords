import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
class ConfirmDialogDemo extends JFrame {
    public ConfirmDialogDemo() {
        setSize(250, 100);
        setVisible(true);
        setLocation(500, 300);
        setResizable(false);
        setTitle("JButton Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create label
        JLabel label = new JLabel("A JLabel");
        add(label);
        // Create button
        JButton button = new JButton("Click me");
        add(button, "North", 1);
        // add ActionListener
        button.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent arg0) {
                 int click = JOptionPane.showConfirmDialog(null, "This is a confirm dialog");
                 if (click==JOptionPane.YES_OPTION) {
                      JOptionPane.showMessageDialog(null, "Click Yes");
                 }
                 if (click==JOptionPane.NO_OPTION) {
                      JOptionPane.showMessageDialog(null, "Click No");
                 }
                 if (click==JOptionPane.CANCEL_OPTION) {
                      JOptionPane.showMessageDialog(null, "Click Cancel");
                 }
                 if (click==JOptionPane.CLOSED_OPTION) {
                      JOptionPane.showMessageDialog(null, "Click Close");
                 }
             }
        });
    }
    public static void main(String[] args) {
        ConfirmDialogDemo confirmDialog = new ConfirmDialogDemo();
    }
}