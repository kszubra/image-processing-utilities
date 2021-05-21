package com.kszubra.image.processing.imageprocessingutilities.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

public class DifferenceColoringUtils {

    public void markPixels(BufferedImage base, BufferedImage possiblyChanged, File destination, double tolerance) {
        if (isImageSizesNotEqual(base, possiblyChanged)) {
            System.out.println("Not same sizes");
        } else {
            int[][] differentPixels = ImageComparisonUtils.getChangedPixels(base, possiblyChanged, tolerance);

            BufferedImage result = markDifferences(differentPixels, possiblyChanged);

            if (Objects.nonNull(destination)) {
                FileManagementUtils.saveImage(destination, result);
            }

        }
    }

    private BufferedImage markDifferences(int[][] differentPixels, BufferedImage possiblyChanged) {
        BufferedImage result = FileManagementUtils.deepCopy(possiblyChanged);
        Graphics2D graphics = result.createGraphics();
        graphics.setColor(Color.RED);
        for (int y = 0; y < possiblyChanged.getHeight(); y++) {
            for (int x = 0; x < possiblyChanged.getWidth(); x++) {
                if (differentPixels[y][x] == 1) {
                    result.setRGB(x, y, Color.RED.getRGB());
//                    graphics.drawRect(x, y, 100, 100);
                }
            }
        }

        return result;
    }

    private boolean isImageSizesNotEqual(BufferedImage expected, BufferedImage actual) {
        return expected.getHeight() != actual.getHeight() || expected.getWidth() != actual.getWidth();
    }
}
