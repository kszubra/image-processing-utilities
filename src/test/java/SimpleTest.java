import com.kszubra.image.processing.imageprocessingutilities.utils.DifferenceColoringUtils;
import com.kszubra.image.processing.imageprocessingutilities.utils.FileManagementUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.time.Instant;

public class SimpleTest {
    public static void main(String[] args) {
        String liku1Url = "src/test/resources/test_images/liku1.jpg";
        String liku2Url = "src/test/resources/test_images/liku2.jpg";
        String likuPixelsUrl = "src/test/resources/test_images/liku_pixels.jpg";
        String likuRecUrl = "src/test/resources/test_images/liku_rectangles.jpg";

        String simba1Url = "src/test/resources/test_images/simba1.jpg";
        String simba2Url = "src/test/resources/test_images/simba2.jpg";
        String simbaPixelsUrl = "src/test/resources/test_images/simba_pixels.jpg";
        String simbaRecUrl = "src/test/resources/test_images/simba_rectangles.jpg";



        BufferedImage liku1 = FileManagementUtils.readImageFromResources(liku1Url);
        BufferedImage liku2 = FileManagementUtils.readImageFromResources(liku2Url);
        File likuPixels = new File( likuPixelsUrl);
        File likuRectangles = new File( likuRecUrl);

        BufferedImage simba1 = FileManagementUtils.readImageFromResources(simba1Url);
        BufferedImage simba2 = FileManagementUtils.readImageFromResources(simba2Url);
        File simbaPixels = new File( simbaPixelsUrl);
        File simbaRectangles = new File( simbaRecUrl);

        //        new DifferenceColoringUtils().markPixels(liku1, liku2, likuPixels, 0.15);

//        Instant start = Instant.now();
//        new DifferenceColoringUtils().markRectangles(liku1, liku2, likuRectangles, 0.15, 60);
//        Instant stop = Instant.now();
//        Duration duration60 = Duration.between(start, stop);


        new DifferenceColoringUtils().markRectangles(simba1, simba2, simbaRectangles, 0.15, 60);


    }
}
