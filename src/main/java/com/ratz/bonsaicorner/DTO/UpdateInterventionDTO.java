package com.ratz.bonsaicorner.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ratz.bonsaicorner.enums.InterventionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInterventionDTO {

    private Long id;

    @Size(min = 4, max = 255)
    private String job;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate interventionDate;

    private InterventionStatus interventionStatus;

}
