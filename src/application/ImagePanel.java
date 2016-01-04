package application;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay{

    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }
    
    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paintComponent(Graphics g){
        g.drawImage((BufferedImage) image.bitmap(), 0, 0, this);
    }
    
    @Override
    public void show(Image image) {
        this.image = image;
        this.paintComponent(this.getGraphics());
    }
}
