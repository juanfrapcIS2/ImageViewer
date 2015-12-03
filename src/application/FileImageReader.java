package application;

import java.io.File;
import java.io.FilenameFilter;
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
                    if(extension.equals(name)) return true;
                return false;
            }
        };
    }

    @Override
    public Image read() {
        return imageAt(0);
    }

    private Image imageAt(int index) {
        return new Image() {

            @Override
            public void getBitmap() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Image prev() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Image next() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }
    }

}
