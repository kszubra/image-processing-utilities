import com.kszubra.image.processing.imageprocessingutilities.utils.DifferenceColoringUtils;
import com.kszubra.image.processing.imageprocessingutilities.utils.FileManagementUtils;

import java.awt.image.BufferedImage;
import java.io.File;

public class SimpleTest {
    public static void main(String[] args) {
        //load images to be compared:
        BufferedImage liku1 = FileManagementUtils.readImageFromResources("src/test/resources/test_images/liku1.jpg");
        BufferedImage liku2 = FileManagementUtils.readImageFromResources("src/test/resources/test_images/liku2.jpg");
        File likuPixels = new File( "src/test/resources/test_images/liku_pixels.jpg" );
        File likuRectangles = new File( "src/test/resources/test_images/liku_rectangles.jpg" );

        new DifferenceColoringUtils().markPixels(liku1, liku2, likuPixels, 0.3);
        new DifferenceColoringUtils().markRectangles(liku1, liku2, likuRectangles, 0.3);


    }
}
