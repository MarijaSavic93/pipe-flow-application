package com.example.pipeflow.models.fluidflow.model;

import androidx.annotation.NonNull;

import com.example.pipeflow.models.fluids.model.FluidCharacteristics;

public class FluidFlowHeatCapacity extends FluidFlow {
    protected FluidFlowHeatCapacity(@NonNull FluidCharacteristics fluidCharacteristics) {
        super(fluidCharacteristics);
    }

    @Override
    protected void calculate(double value) {
        double density = getFluidCharacteristics().density(),
               specificHeatCapacity = getFluidCharacteristics().specificHeatCapacity(),
               deltaTemperature = getFluidCharacteristics().getTemperature().deltaTemperature();

        setHeatCapacity(value);
        setVolumeFlowM3H(3600 * getHeatCapacity() / (density * specificHeatCapacity * deltaTemperature));
        setVolumeFlowM3S(getVolumeFlowM3H() / 3600);
        setVolumeFlowLH(1000 * getVolumeFlowM3H());
        setVolumeFlowLS(getVolumeFlowLH() / 3600);
        setMassFlowKgH(getVolumeFlowM3H() * density);
        setMassFlowKgS(getMassFlowKgH() / 3600);
    }

    @Override
    protected FluidFlowHeatCapacity init(double value) {
        calculate(value);
        return this;
    }

}
