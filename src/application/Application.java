package application;

import control.Command;
import control.NextImageCommand;
import control.PrevImageCommand;
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

    private final Map<String, Command> commands = new HashMap<>();
    private ImagePanel imageDisplay;

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
        this.setLayout(new BorderLayout());
        this.setMinimumSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imagePanel());
        this.getContentPane().add(toolbar(), BorderLayout.SOUTH);
    }

    private ImagePanel imagePanel() {
        imageDisplay = new ImagePanel(image());
        return imageDisplay;
    }

    private Image image() {
        return new FileImageReader("C:\\Users\\Granfran\\Pictures").read();
    }

    private Component toolbar() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(PrevButton());
        panel.add(NextButton());
        return panel;
    }

    private JButton NextButton() {

        JButton button = new JButton(">");
        button.addActionListener(doCommandNext());
        return button;
    }

    private JButton PrevButton() {
        JButton button = new JButton("<");
        button.addActionListener(doCommandPrev());
        return button;
    }

    private void createCommands() {
        commands.put("next", new NextImageCommand(imageDisplay));
        commands.put("prev", new PrevImageCommand(imageDisplay));
    }

    private ActionListener doCommandNext() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("next").execute();
            }
        };
    }

    private ActionListener doCommandPrev() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("prev").execute();
            }
        };
    }

   
}
