package com.ratz.bonsaicorner.integration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ratz.bonsaicorner.DTO.InterventionDTO;
import com.ratz.bonsaicorner.model.Species;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class BonsaiDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private int age;
    private String description;
    private Set<String> images;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate bonsaiCreationDate;

    private Species species;
    private List<InterventionDTO> interventionList;
}
