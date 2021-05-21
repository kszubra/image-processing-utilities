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
public class ClusterRecord {
    private final String description;
    private final Map<String, Double> features;
}
