package com.kszubra.image.processing.imageprocessingutilities.utils;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.kszubra.image.processing.imageprocessingutilities.exception.FileManagementException;

public class FileManagementUtils {

    public static BufferedImage deepCopy(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(image.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static void saveImage(File pathFile, BufferedImage image) {
        File dir = pathFile.getParentFile();
        boolean dirExists = dir == null || dir.isDirectory() || dir.mkdirs();
        if (!dirExists) {
            throw new RuntimeException();
        }
        try {
            ImageIO.write(image, "jpg", pathFile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static BufferedImage readImageFromResources(String path) {
        File imageFile = new File(path);
        if (imageFile.exists()) {
            try {
                return ImageIO.read(imageFile);
            } catch (IOException e) {
                System.out.println(String.format("Cannot read image from the file, path=%s", path));
                throw FileManagementException.cantReadFile();
            }
        } else {
            InputStream inputStream = ImageComparisonUtils.class.getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                try {
                    return ImageIO.read(inputStream);
                } catch (IOException e) {
                    System.out.println(String.format("Cannot read image from the file, path=%s", path));
                    throw FileManagementException.cantReadFile();
                }
            } else {
                System.out.println(String.format("Image with path = %s not found", path));
                throw FileManagementException.imageNotFound();
            }
        }
    }
}
