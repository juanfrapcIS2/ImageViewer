package control;

import view.ImageDisplay;

public class PrevImageCommand implements Command{

    private ImageDisplay imageDisplay;
    
    @Override
    public void execute() {
        this.imageDisplay.image().prev();
    }

}
