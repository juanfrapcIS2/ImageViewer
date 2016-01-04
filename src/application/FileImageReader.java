package application;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Image;
import view.ImageReader;

public class FileImageReader implements ImageReader{

    private static final String[] extensions = {".jpg", ".gif", ".png", ".JPG"};
    private final File[] fileList;

    public FileImageReader(File folder) {
        this.fileList = folder.listFiles(withImageExtension());
    }
    
    public FileImageReader(String path) {
        this(new File(path));
    }
    
    private FilenameFilter withImageExtension() {
        return new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                for (String extension : extensions) {
                    if (name.endsWith(extension)){
                        return true;                        
                    }
                }
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
                try {
                    return ImageIO.read(fileList[index]);
                } catch (IOException ex) {
                    System.out.println("Carpeta no encontrada");
                }
                return null;
            }

            @Override
            public Image next() {
                return index == fileList.length - 1 ? imageAt(0) : imageAt(index + 1);
            }

            @Override
            public Image prev() {
                return index == 0 ? imageAt(fileList.length - 1) : imageAt(index - 1);
            }
        };
    }

}
