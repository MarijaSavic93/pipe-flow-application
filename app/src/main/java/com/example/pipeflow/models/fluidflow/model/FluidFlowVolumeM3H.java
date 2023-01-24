package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public class FluidFlowVolumeM3H extends FluidFlow {
    protected FluidFlowVolumeM3H(@NonNull FluidCharacteristics fluidCharacteristics) {
        super(fluidCharacteristics);
    }

    @Override
    protected void calculate(double value) {
        double density = getFluidCharacteristics().density(),
                specificHeatCapacity = getFluidCharacteristics().specificHeatCapacity(),
                deltaTemperature = getFluidCharacteristics().getTemperature().deltaTemperature();

        setVolumeFlowM3H(value);
        setHeatCapacity(getVolumeFlowM3H() * density * specificHeatCapacity * deltaTemperature / 3600);
        setVolumeFlowM3S(getVolumeFlowM3H() / 3600);
        setVolumeFlowLH(1000 * getVolumeFlowM3H());
        setVolumeFlowLS(getVolumeFlowLH() / 3600);
        setMassFlowKgH(getVolumeFlowM3H() * density);
        setMassFlowKgS(getMassFlowKgH() / 3600);
    }

    @Override
    protected FluidFlowVolumeM3H init(double value) {
        calculate(value);
        return this;
    }
}
