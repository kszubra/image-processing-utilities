package com.kszubra.image.processing.imageprocessingutilities.clustering;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Centroid {

    private final Map<String, Double> coordinates;
}
