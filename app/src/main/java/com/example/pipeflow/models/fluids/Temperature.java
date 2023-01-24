package com.example.pipeflow.models.fluids;

public class Temperature {
    private final double supplyTemperature, returnTemperature;

    public Temperature(double supplyTemperature, double returnTemperature) {
        this.supplyTemperature = supplyTemperature;
        this.returnTemperature = returnTemperature;
    }

    public double getSupplyTemperature() { return supplyTemperature; }
    public double getReturnTemperature() {
        return returnTemperature;
    }

    public double deltaTemperature(){
        return Math.abs(supplyTemperature - returnTemperature);
    }
    public double averageTemperature(){
        return (supplyTemperature + returnTemperature)/2;
    }
}
