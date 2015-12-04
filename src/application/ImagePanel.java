package application;

import java.awt.Graphics;
import javax.swing.JPanel;
import model.Image;
import view.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay{

    private final Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }
    
    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(){
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage((java.awt.Image) image.bitmap(), 0, 0, this);
    }
}
