package com.example.pipeflow.validators;

import androidx.annotation.NonNull;

import com.example.pipeflow.utility.DoubleComparison;
import com.example.pipeflow.models.fluids.Temperature;

public class TemperatureValidator implements RangeValidatable, Validatable{
    public static final double TEMPERATURE_MAX_VALUE = 160;

    private String errorMessage = "";
    private Temperature temperature = new Temperature(0,0);

    public TemperatureValidator(){ }

    public TemperatureValidator(@NonNull Temperature temperature){
        this.temperature = temperature;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

    @Override
    public boolean validateRange(double value){
        if(value > TEMPERATURE_MAX_VALUE){
            errorMessage = "Temperature value must not be greater than 160°C";
            return false;
        }
        return true;
    }

    @Override
    public boolean validate() {
        double deltaTemperature = temperature.deltaTemperature();
        if(new DoubleComparison().equalsForDoubles(deltaTemperature, 0)) {
            errorMessage = "Temperature values must not be equal - delta temperature must not be 0";
            return false;
        }
        return true;
    }
}
