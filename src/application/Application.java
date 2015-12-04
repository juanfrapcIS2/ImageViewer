package application;

import control.Command;
import control.NextImageCommand;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Image;

public class Application extends JFrame {

    private final Map<String,Command> commands = new HashMap<>();

    
    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application(){
        this.deployUI();
        this.createCommands();
    }  
    
    private void deployUI() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imagePanel());
        this.getContentPane().add(toolbar(),BorderLayout.SOUTH);
    }

    private ImagePanel imagePanel() {
        return new ImagePanel(image());
    }

    private Image image() {
        return new FileImageReader("C:\\Users\\Granfran\\Pictures\\").read();
    }

    private Component toolbar() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(PrevButton());
        panel.add(NextButton());
    }

    private JButton NextButton() {

        JButton button = new JButton(">");
        button.addActionListener(doCommandNext());
        return button;
    }

    private ActionListener doCommandNext() {

        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new NextImageCommand().execute();
            }
        };
    }
    
}
