package mts.teta.resizer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

import static picocli.CommandLine.*;

@Command(name = "resizer",
        version = "resizer 0.0.1",
        headerHeading = "Version: resizer 0.0.1 Available formats: jpeg png\n",
        header = "Available formats: jpeg png",
        description = "",
        customSynopsis = "Usage: convert input-file [options ...] output-file",
        optionListHeading = "Options Settings:\n",
        separator = " ",
        sortOptions = false,
        usageHelpAutoWidth = true)
class ConsoleAttributes{
    @Parameters(paramLabel = "Input File", index = "0", hidden = true)
    File inputFile;

    @Option(names = "--resize", description = "resize the image", paramLabel = "width height", split = " ")
    private int[] resize = new int[2];

    @Option(names = "--quality", description = "JPEG/PNG compression level", paramLabel = "value")
    private int quality = 100;

    @Option(names = "--crop", description = "@|fg(red) cut|@ out one rectangular area of the image", paramLabel = "width height x y", split = " ")
    private int[] crop = new int[4];

    @Option(names = "--blur", paramLabel = "{radius}", description = "reduce image noise detail levels")
    private int radius = 0;

    @Option(names = "--format", paramLabel = "\"outputFormat\"", description = "the image @|fg(red) format type|@")
    private String outputFormat = "jpg";

    @Parameters(paramLabel = "Output File", hidden = true)
    File outputFile;

    @Option(names = {"-h", "--help"}, hidden = true, usageHelp = true)
    private boolean showHelp;

    public void setResizeWidth(int width){
        this.resize[0] = width;
    }
    public void setResizeHeight(int height){
        this.resize[1] = height;
    }

    public int getResizeWidth() throws IOException {
        if(resize[0] == 0){
            return ImageIO.read(this.inputFile).getWidth();
        } else {
            return this.resize[0];
        }
    }

    public int getResizeHeight() throws IOException {
        if(resize[1] == 0)
        {
            return ImageIO.read(this.inputFile).getHeight();
        }else {
            return this.resize[1];
        }
    }

    public void setQuality(int quality){
        this.quality = quality;
    }

    public int getQuality(){
        return this.quality;
    }

    public int getCropWidth(){
        return this.crop[0];
    }

    public void setCropWidth(int width){
        this.crop[0] = width;
    }

    public int getCropHeight(){
        if(this.crop[1] == 0){
            return this.crop[0];
        } else {
            return this.crop[1];
        }
    }

    public void setCropHeight(int height){
        this.crop[1] = height;
    }

    public int getCropX(){
        return this.crop[2];
    }

    public void setCropX(int x){
        this.crop[2] = x;
    }

    public int getCropY(){
        return this.crop[3];
    }

    public void setCropY(int y){
        this.crop[3] = y;
    }

    public File getInputFile(){
        return this.inputFile;
    }

    public File getOutputFile(){
        return this.outputFile;
    }

    public void setInputFile(File file){
        this.inputFile = file;
    }

    public void setOutputFile(File file){
        this.outputFile = file;
    }

    public int getBlurRadius() {
        return radius;
    }

    public void setBlurRadius(int radius) {
        this.radius = radius;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }


}
