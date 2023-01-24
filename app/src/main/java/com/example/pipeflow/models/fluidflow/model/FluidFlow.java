package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public abstract class FluidFlow {
    private final FluidCharacteristics fluidCharacteristics;
    private double heatCapacity, volumeFlowM3H, volumeFlowM3S, volumeFlowLH, volumeFlowLS, massFlowKgH, massFlowKgS;

    protected FluidFlow(@NonNull FluidCharacteristics fluidCharacteristics){
        this.fluidCharacteristics = fluidCharacteristics;
    }

    protected void setHeatCapacity(double heatCapacity){
        this.heatCapacity = heatCapacity;
    }
    protected void setVolumeFlowM3H(double volumeFlowM3H){
        this.volumeFlowM3H = volumeFlowM3H;
    }
    protected void setVolumeFlowM3S(double volumeFlowM3S){
        this.volumeFlowM3S = volumeFlowM3S;
    }
    protected void setVolumeFlowLH(double volumeFlowLH){
        this.volumeFlowLH = volumeFlowLH;
    }
    protected void setVolumeFlowLS(double volumeFlowLS){
        this.volumeFlowLS = volumeFlowLS;
    }
    protected void setMassFlowKgH(double massFlowKgH){
        this.massFlowKgH = massFlowKgH;
    }
    protected void setMassFlowKgS(double massFlowKgS){
        this.massFlowKgS = massFlowKgS;
    }

    public FluidCharacteristics getFluidCharacteristics() {
        return fluidCharacteristics;
    }
    public double getHeatCapacity() {
        return heatCapacity;
    }
    public double getVolumeFlowM3H() {
        return volumeFlowM3H;
    }
    public double getVolumeFlowM3S() {
        return volumeFlowM3S;
    }
    public double getVolumeFlowLH() {
        return volumeFlowLH;
    }
    public double getVolumeFlowLS() {
        return volumeFlowLS;
    }
    public double getMassFlowKgH() {
        return massFlowKgH;
    }
    public double getMassFlowKgS() {
        return massFlowKgS;
    }

    protected abstract void calculate(double value);
    protected abstract FluidFlow init(double value);
}
