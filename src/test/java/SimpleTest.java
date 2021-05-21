import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import com.kszubra.image.processing.imageprocessingutilities.clustering.ClusterRecord;
import com.kszubra.image.processing.imageprocessingutilities.clustering.KMeansClusterService;
import com.kszubra.image.processing.imageprocessingutilities.utils.DifferenceColoringUtils;
import com.kszubra.image.processing.imageprocessingutilities.utils.FileManagementUtils;
import com.kszubra.image.processing.imageprocessingutilities.utils.ImageComparisonUtils;

public class SimpleTest {
    public static void main(String[] args) {
        //load images to be compared:
        BufferedImage base1 = FileManagementUtils.readImageFromResources("src/test/resources/test_images/jag1.jpg");
        BufferedImage new1 = FileManagementUtils.readImageFromResources("src/test/resources/test_images/jag2.jpg");
        File result1 = new File( "src/test/resources/test_images/jag_result.jpg" );

        new DifferenceColoringUtils().markPixels(base1, new1, result1, 0.2);

        List<ClusterRecord> records = ImageComparisonUtils.getClusterRecords(base1, new1, 0.1);
        KMeansClusterService.generateClusters(records);


    }
}
