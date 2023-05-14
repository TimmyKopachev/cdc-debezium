package org.dzmitry.kapachou.everest.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String asset;
    private String cusip;
    private String maturityBucket;
}
