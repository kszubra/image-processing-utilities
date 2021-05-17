package com.kszubra.image.processing.imageprocessingutilities;

import java.awt.image.BufferedImage;
import java.io.File;

public class SimpleTest {
    public static void main(String[] args) {
        //load images to be compared:
        BufferedImage base1 = ImageComparisonUtil.readImageFromResources("src/test/resources/custom/custom_base_1.jpg");
        BufferedImage new1 = ImageComparisonUtil.readImageFromResources("src/test/resources/custom/custom_new_1.jpg");
        File result1 = new File( "src/test/resources/custom/custom_result_1.jpg" );
        BufferedImage base2 = ImageComparisonUtil.readImageFromResources("src/test/resources/custom/custom_base_2.jpg");
        BufferedImage new2 = ImageComparisonUtil.readImageFromResources("src/test/resources/custom/custom_new_2.jpg");
        File result2 = new File( "src/test/resources/custom/custom_result_2.jpg" );

        BufferedImage spiderBw = ImageComparisonUtil.readImageFromResources("src/test/resources/custom/spider_bw.jpg");
        BufferedImage spiderC = ImageComparisonUtil.readImageFromResources("src/test/resources/custom/spider_c.jpg");

        new RecolorDiffirences().markPixels(base1, new1, result1, 0.1);
        new RecolorDiffirences().markPixels(base2, new2, result2, 0.1);

        boolean imagesAreSame = false;
        double tolerance = 0.53;

        do {
            Integer spiderBwCodeHash = ComparisonUtils.getImageCode(spiderBw, tolerance).hashCode();
            Integer spiderCCodeHash = ComparisonUtils.getImageCode(spiderC, tolerance).hashCode();
            imagesAreSame = spiderBwCodeHash.equals(spiderCCodeHash);
            System.out.println(String.format("Tolerance: [%s], result: [%s]", tolerance, imagesAreSame));
            tolerance = tolerance + 0.01;
        } while (!imagesAreSame);


    }
}
