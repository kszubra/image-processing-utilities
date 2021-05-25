package com.kszubra.image.processing.imageprocessingutilities.utils;

import com.kszubra.image.processing.imageprocessingutilities.clustering.ClusterRecord;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
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

    public void markRectangles(BufferedImage base, BufferedImage possiblyChanged, File destination, double tolerance, int numberOfClusters) {
        if (isImageSizesNotEqual(base, possiblyChanged)) {
            System.out.println("Not same sizes");
        } else {

            List<ClusterRectangle> rectangles = ImageComparisonUtils.getClusters(base, possiblyChanged, tolerance, numberOfClusters);
            BufferedImage result = drawRectangles(rectangles, possiblyChanged);

            if (Objects.nonNull(destination)) {
                FileManagementUtils.saveImage(destination, result);
            }
        }
    }

    private BufferedImage drawRectangles(List<ClusterRectangle> rectangles, BufferedImage possiblyChanged) {
        BufferedImage result = FileManagementUtils.deepCopy(possiblyChanged);
        Graphics2D graphics = result.createGraphics();
        graphics.setColor(Color.RED);
        for(ClusterRectangle rectangle : rectangles) {
            graphics.drawRect((int)rectangle.getX(), (int)rectangle.getY(), (int)rectangle.getWidth(), (int)rectangle.getHeight());
        }

        return result;
    }

    private BufferedImage markDifferences(int[][] differentPixels, BufferedImage possiblyChanged) {
        BufferedImage result = FileManagementUtils.deepCopy(possiblyChanged);
        Graphics2D graphics = result.createGraphics();
        graphics.setColor(Color.RED);
        for (int y = 0; y < possiblyChanged.getHeight(); y++) {
            for (int x = 0; x < possiblyChanged.getWidth(); x++) {
                if (differentPixels[y][x] == 1) {
                    result.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }

        return result;
    }

    private boolean isImageSizesNotEqual(BufferedImage expected, BufferedImage actual) {
        return expected.getHeight() != actual.getHeight() || expected.getWidth() != actual.getWidth();
    }
}
