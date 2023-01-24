package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public class FluidFlowMassKgS extends FluidFlow {
    protected FluidFlowMassKgS(@NonNull FluidCharacteristics fluidCharacteristics) {
        super(fluidCharacteristics);
    }

    @Override
    protected void calculate(double value) {
        double density = getFluidCharacteristics().density(),
                specificHeatCapacity = getFluidCharacteristics().specificHeatCapacity(),
                deltaTemperature = getFluidCharacteristics().getTemperature().deltaTemperature();

        setMassFlowKgS(value);
        setMassFlowKgH(getMassFlowKgS() * 3600);
        setVolumeFlowM3H(getMassFlowKgH() / density);
        setHeatCapacity(getVolumeFlowM3H() * density * specificHeatCapacity * deltaTemperature / 3600);
        setVolumeFlowM3S(getVolumeFlowM3H() / 3600);
        setVolumeFlowLH(getVolumeFlowM3H() * 1000);
        setVolumeFlowLS(getVolumeFlowLH() / 3600);
    }

    @Override
    protected FluidFlowMassKgS init(double value) {
        calculate(value);
        return this;
    }
}
