package com.ingress.ingress_auto_sales.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class CarFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String featureName;

    private boolean airConditioning;
    private boolean powerWindows;
    private boolean powerLocks;
    private boolean keylessEntry;
    private boolean remoteStart;
    private boolean navigationSystem;
    private boolean bluetoothConnectivity;
    private boolean backupCamera;
    private boolean parkingSensors;
    private boolean adaptiveCruiseControl;
    private boolean laneDepartureWarning;
    private boolean blindSpotMonitoring;
    private boolean leatherSeats;
    private boolean heatedSeats;
    private boolean ventilatedSeats;
    private boolean sunroofMoonroof;
    private boolean alloyWheels;
    private boolean fogLights;
    private boolean ledHeadlights;
    private boolean tractionControl;
    private boolean stabilityControl;
    private boolean antiLockBrakingSystem;
    private boolean electronicStabilityControl;
    private boolean tirePressureMonitoringSystem;
    private boolean automaticEmergencyBraking;

    @ManyToOne
    private Car car;
}

