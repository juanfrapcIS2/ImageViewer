package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Image;
import view.ImageReader;

public class FileImageReader implements ImageReader{

    private static final String[] extensions = {".jpg",".png",".gif"};
    private final File[] fileList;

    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.fileList = folder.listFiles(withImageExtension());
    }
    
    private FilenameFilter withImageExtension() {
        return new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                for (String extension : extensions)
                    if(name.endsWith(extension)) return true;
                return false;
            }
        };
    }

    @Override
    public Image read() {
        return imageAt(0);
    }

    private Image imageAt(final int index) {
        return new Image() {

            @Override
            public Object bitmap() {
                BufferedImage read;
                try {
                    return read = ImageIO.read(fileList[index]);
                } catch (IOException ex) {
                    System.out.println("Carpeta no encontrada");
                }
                return null;
            }

            @Override
            public Image prev() {
                return imageAt(index > 0 ? index - 1 : fileList.length);
            }

            @Override
            public Image next() {
                return imageAt(index < fileList.length ? index + 1 : 0);
            }
        };
    }

}
