package com.kszubra.image.processing.imageprocessingutilities.clustering;

import java.util.List;
import java.util.Map;

public class Errors {

    public static double sse(Map<Centroid, List<ClusterRecord>> clustered, Distance distance) {
        double sum = 0;
        for (Map.Entry<Centroid, List<ClusterRecord>> entry : clustered.entrySet()) {
            Centroid centroid = entry.getKey();
            for (ClusterRecord record : entry.getValue()) {
                double d = distance.calculate(centroid.getCoordinates(), record.getFeatures());
                sum += Math.pow(d, 2);
            }
        }

        return sum;
    }
}
