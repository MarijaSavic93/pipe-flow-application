package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public class FluidFlowVolumeLS extends FluidFlow {
    protected FluidFlowVolumeLS(@NonNull FluidCharacteristics fluidCharacteristics) {
        super(fluidCharacteristics);
    }

    @Override
    protected void calculate(double value) {
        double density = getFluidCharacteristics().density(),
                specificHeatCapacity = getFluidCharacteristics().specificHeatCapacity(),
                deltaTemperature = getFluidCharacteristics().getTemperature().deltaTemperature();

        setVolumeFlowLS(value);
        setVolumeFlowLH(getVolumeFlowLS() * 3600);
        setVolumeFlowM3H(getVolumeFlowLH() / 1000);
        setHeatCapacity(getVolumeFlowM3H() * density * specificHeatCapacity * deltaTemperature / 3600);
        setVolumeFlowM3S(getVolumeFlowM3H() / 3600);
        setMassFlowKgH(getVolumeFlowM3H() * density);
        setMassFlowKgS(getMassFlowKgH() / 3600);
    }

    @Override
    protected FluidFlowVolumeLS init(double value) {
        calculate(value);
        return this;
    }
}
