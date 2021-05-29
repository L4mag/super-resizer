package mts.teta.resizer.imageprocessor;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import mts.teta.resizer.ResizerApp;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import net.coobird.thumbnailator.Thumbnails;

import static marvinplugins.MarvinPluginCollection.crop;
import static marvinplugins.MarvinPluginCollection.gaussianBlur;

public class ImageProcessor {
    public void processImage(BufferedImage image, ResizerApp ra) throws IOException, BadAttributesException {
        if(ra.getResizeWidth() < 0 || ra.getResizeHeight() < 0 || ra.getQuality() < 0
                || ra.getCropHeight() < 0 || ra.getCropWidth() < 0 || ra.getBlurRadius() < 0)
        {
            throw new BadAttributesException("Please check params!");
        } else {
            Thumbnails.of(image)
                    .forceSize(ra.getResizeWidth(), ra.getResizeHeight())
                    .outputFormat(ra.getOutputFormat())
                    .outputQuality((float)(ra.getQuality() / 100))
                    .toFile(ra.getOutputFile());
        }

        if(ra.getBlurRadius() > 0 || (ra.getCropWidth() > 0 && ra.getCropHeight() > 0))
        {
            MarvinImage marvinImage = new MarvinImage(image);
            if (ra.getCropWidth() > 0 && ra.getCropHeight() > 0){
                crop(marvinImage.clone(), marvinImage, ra.getCropX(), ra.getCropY(), ra.getCropWidth(), ra.getCropHeight());
            }
            if (ra.getBlurRadius() > 0){
                gaussianBlur(marvinImage.clone(), marvinImage, ra.getBlurRadius());
            }

            MarvinImageIO.saveImage(marvinImage, ra.getOutputFile().getPath());
        }
    }
}
