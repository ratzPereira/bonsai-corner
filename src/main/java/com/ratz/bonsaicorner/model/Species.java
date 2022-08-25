package com.ratz.bonsaicorner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "species")
public class Species implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String speciesName;

    @Column(name = "description")
    private String description;

    @Column(name = "watering")
    private String watering;

    @Column(name = "soil")
    private String soil;

    @Column(name = "regions")
    private String regions;

    @Column(name = "pruning")
    private String pruning;

    @Column(name = "transplant")
    private String transplant;

    @Column(name = "propagation")
    private String propagation;

    @Column(name = "fertilizing")
    private String fertilizing;

    @Column(name = "sun_exposure")
    private String sunExposure;

    @Column(name = "common_bonsai_styles")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "species_common_bonsai_styles", joinColumns = @JoinColumn(name = "species_id"))
    Set<String> commonBonsaiStyles;

    @ManyToOne
    @JoinColumn(name = "family_id", nullable = false)
    private Family family;

    @ManyToOne
    @JoinColumn(name = "treeGroup_id", nullable = false)
    private TreeGroup treeGroup;

}
