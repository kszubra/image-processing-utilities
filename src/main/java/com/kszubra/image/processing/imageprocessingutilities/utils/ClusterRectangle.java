package com.kszubra.image.processing.imageprocessingutilities.utils;

import com.kszubra.image.processing.imageprocessingutilities.clustering.Centroid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ClusterRectangle {
    private double x;
    private double y;
    private double width;
    private double height;
    private Centroid centroid;
}
