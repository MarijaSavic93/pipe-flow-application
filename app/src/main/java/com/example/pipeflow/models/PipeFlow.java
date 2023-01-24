package com.example.pipeflow.models;

import androidx.annotation.NonNull;

import com.example.pipeflow.utility.DoubleComparison;
import com.example.pipeflow.models.fluidflow.model.FluidFlow;

public class PipeFlow {
    private final Pipe pipe;
    private final FluidFlow fluidFlow;

    public PipeFlow(@NonNull Pipe pipe, @NonNull FluidFlow fluidFlow) {
        this.pipe = pipe;
        this.fluidFlow = fluidFlow;
    }

    public double velocity(){
        double heatCapacity = fluidFlow.getHeatCapacity(),
               pipeArea = pipe.pipeArea(),
               density = fluidFlow.getFluidCharacteristics().density(),
               specificHeatCapacity = fluidFlow.getFluidCharacteristics().specificHeatCapacity(),
               deltaTemperature = fluidFlow.getFluidCharacteristics().getTemperature().deltaTemperature();

        return heatCapacity / (pipeArea * density * specificHeatCapacity * deltaTemperature);
    }
    private double reynoldsNumber(){
        return velocity() * pipe.innerPipeDiameterInMeters() / fluidFlow.getFluidCharacteristics().viscosity();
    }
    public double frictionCoefficient(){
        DoubleComparison comparison = new DoubleComparison();
        double re = reynoldsNumber();

        if(comparison.firstGreaterOrEqualToSecondDouble(2200, re)){
            return 64 / re;
        }

        double du = pipe.innerPipeDiameterInMeters();
        double error = 100;
        double kk = pipe.getRoughness() / (1000 * du);
        double lambda1 = 0.0072 + 0.61 / Math.pow(re, 0.35) + (0.000029 / du) * Math.pow(re, 0.108),
               lambda2 = 0.1 * Math.pow(1.46 * pipe.getRoughness() / (1000 * du) + 100 / re, 0.25);
        double aa = Math.pow(2.457 * Math.log(1 / (Math.pow(7 / re, 0.9) + 0.27 * kk)), 16),
               bb = Math.pow(37530 / re, 16);
        double lambda10 = 8 * Math.pow(Math.pow(8 / re, 12) + 1 / Math.pow(aa + bb, 1.5), 0.0833333333);

        if(comparison.firstGreaterOrEqualToSecondDouble(2400, re)){
            double l = 1;
            while(Math.abs(error) > DoubleComparison.EPSILON){
                double lambda5 = 1 / Math.pow(-2.01 * Math.log10(kk / 3.71) + 2.51 / (re * Math.pow(l, 0.5)), 2);
                error = l - lambda5;
                l = lambda5;
            }
            double lambda3 = 64 / re + (re - 2200) * l / 200; // 200 is from endpoints 2400 and 2200 (2400 - 2200)
            return (lambda1 + lambda2 + lambda3 + lambda10)/4;
        }
        if(comparison.firstGreaterOrEqualToSecondDouble(4000, re)){
            double lambda3 = 0.0025 * Math.pow(re, 0.33);
            return (lambda1 + lambda2 + lambda3 + lambda10)/4;
        }
        if(comparison.firstGreaterThanSecondDouble(23 / kk, re)){
            double l = 1;
            while(Math.abs(error) > DoubleComparison.EPSILON){
                double lambda5 = 1 / Math.pow(2.01 * Math.log10(re / 2.51 * Math.pow(l, 0.5)), 2);
                error = l - lambda5;
                l = lambda5;
            }
            return (lambda1 + lambda2 + l + lambda10)/4;
        }
        if(comparison.firstGreaterThanSecondDouble(560 / kk, re)){
            double l = 1;
            while(Math.abs(error) > DoubleComparison.EPSILON){
                double lambda5 = 1 / Math.pow(-2.01 * Math.log10(kk / 3.71) + 2.51 / (re * Math.pow(l, 0.5)), 2);
                error = l - lambda5;
                l = lambda5;
            }
            return (lambda1 + lambda2 + l + lambda10)/4;
        }

        double lambda3 = 1 / Math.pow(1.14 - 2 * Math.log10(kk), 2);
        return (lambda1 + lambda2 + lambda3 + lambda10) / 4;
    }
    public double pressureDrop(){
        if(new DoubleComparison().equalsForDoubles(fluidFlow.getHeatCapacity(), 0)){
            return 0;
        }
        return 0.5 * fluidFlow.getFluidCharacteristics().density() / (pipe.innerPipeDiameterInMeters() / frictionCoefficient()) * Math.pow(velocity(), 2);
    }

}
