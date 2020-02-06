package com.dimed.kafka.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaModel {
    private String hostname;
    private Long idAssinatura;

}
