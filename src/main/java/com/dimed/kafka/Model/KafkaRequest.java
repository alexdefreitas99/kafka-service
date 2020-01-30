package com.dimed.kafka.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaRequest {
    private Integer filial;
    private int idAssinatura;
    private String hostname;

}
