package com.kszubra.image.processing.imageprocessingutilities.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.kszubra.image.processing.imageprocessingutilities.clustering.ClusterRecord;

public class ImageComparisonUtils {

    public static String getImageCode(BufferedImage image, double toleranceLevel) {
        StringBuilder sb = new StringBuilder();
        Integer previousRgb = null;
        Integer previousColorCode = null;
        Map<String, Integer> colorCodes = new HashMap<>();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if(Objects.isNull(previousRgb)) {
                    previousRgb = image.getRGB(x, y);
                    previousColorCode = 1;
                    colorCodes.put(String.valueOf(image.getRGB(x, y)), previousColorCode);
                    sb.append(previousColorCode);
                } else {
                    if (isDifferentPixels(image.getRGB(x, y), previousRgb, toleranceLevel)) {
                        if(colorCodes.containsKey(String.valueOf(image.getRGB(x,y)))) {
                            previousRgb = image.getRGB(x,y);
                            sb.append(previousColorCode);

                        } else {
                            previousColorCode++;
                            previousRgb = image.getRGB(x,y);
                            colorCodes.put(previousRgb.toString(), previousColorCode);
                            sb.append(colorCodes.get(String.valueOf(image.getRGB(x,y))));
                            sb.append(previousColorCode);
                        }
                    }
                }
            }
        }

        return sb.toString();

    }

    public static int[][] getChangedPixels(BufferedImage base, BufferedImage possiblyChanged, double toleranceLevel) {
        long countOfDifferentPixels = 0;
        int[][] matrix = new int[base.getHeight()][base.getWidth()];
        for (int y = 0; y < base.getHeight(); y++) {
            for (int x = 0; x < base.getWidth(); x++) {
                if (isDifferentPixels(base.getRGB(x, y), possiblyChanged.getRGB(x, y), toleranceLevel)) {
                    matrix[y][x] = 1;
                    countOfDifferentPixels++;
                }
            }
        }
        return matrix;
    }

    public static List<ClusterRecord> getClusterRecords(BufferedImage base, BufferedImage possiblyChanged, double toleranceLevel) {
        long countOfDifferentPixels = 0;
        List<ClusterRecord> records = new ArrayList<>();

        for (int y = 0; y < base.getHeight(); y++) {
            for (int x = 0; x < base.getWidth(); x++) {
                if (isDifferentPixels(base.getRGB(x, y), possiblyChanged.getRGB(x, y), toleranceLevel)) {
                    countOfDifferentPixels++;
                    records.add(new ClusterRecord(String.format("Difference_%s", countOfDifferentPixels), Map.of("x", Double.valueOf(String.valueOf(x)), "y",  Double.valueOf(String.valueOf(y)))));
                }
            }
        }

        return records;
    }

    public static boolean isDifferentPixels(int expectedRgb, int actualRgb, double toleranceLevel) {
        double differenceConstant = calculateDifferenceConstant(toleranceLevel);

        if (expectedRgb == actualRgb) {
            return false;
        } else if (toleranceLevel == 0.0) {
            return true;
        }

        int red1 = (expectedRgb >> 16) & 0xff;
        int green1 = (expectedRgb >> 8) & 0xff;
        int blue1 = (expectedRgb) & 0xff;
        int red2 = (actualRgb >> 16) & 0xff;
        int green2 = (actualRgb >> 8) & 0xff;
        int blue2 = (actualRgb) & 0xff;

        return (Math.pow(red2 - red1, 2) + Math.pow(green2 - green1, 2) + Math.pow(blue2 - blue1, 2))
                > differenceConstant;
    }

    private static double calculateDifferenceConstant(double toleranceLevel) {
        return Math.pow(toleranceLevel * Math.sqrt(Math.pow(255, 2) * 3), 2);
    }
}
