package com.example.pipeflow.validators;

import android.text.style.IconMarginSpan;

import androidx.annotation.NonNull;

import com.example.pipeflow.utility.DoubleComparison;
import com.example.pipeflow.models.Pipe;

public class PipeValidator implements RangeValidatable, Validatable{
    public static final double OUTER_DIAMETER_MIN_VALUE = 0.1;

    private Pipe pipe = new Pipe(0,0,0);
    private String errorMessageOuterDiameter = "", errorMessageWallThickness = "", errorMessageRoughness = "";

    public PipeValidator(){ super(); }

    public PipeValidator(@NonNull Pipe pipe){
        this.pipe = pipe;
    }

    public String getErrorMessageOuterDiameter(){
        return this.errorMessageOuterDiameter;
    }
    public String getErrorMessageWallThickness(){
        return this.errorMessageWallThickness;
    }
    public String getErrorMessageRoughness(){
        return this.errorMessageRoughness;
    }

    @Override
    public boolean validateRange(double value){
        if(value < OUTER_DIAMETER_MIN_VALUE){
            errorMessageOuterDiameter = "Outer diameter must be equal to or greater than 0.1mm";
            return false;
        }
        return true;
    }

    @Override
    public boolean validate() {
        double outerDiameter = pipe.getOuterDiameter(),
               wallThickness = pipe.getWallThickness(),
               roughness = pipe.getRoughness();
        DoubleComparison comparison = new DoubleComparison();
        boolean isValid = true;

        if(wallThickness > 0){
            if(comparison.firstGreaterOrEqualToSecondDouble(wallThickness, outerDiameter / 2)){
                errorMessageWallThickness = "Wall thickness must be less than half the value of outer diameter - inner diameter must not be 0";
                isValid = false;
            }
            if(comparison.firstGreaterThanSecondDouble(roughness, wallThickness / 2)){
                errorMessageRoughness = "Roughness must be less than half the value of wall thickness";
                isValid = false;
            }
        }else if(comparison.firstGreaterThanSecondDouble(roughness, outerDiameter / 2)){
            errorMessageRoughness = "In case there is no data for wall thickness, roughness must be less than half the value of outer diameter";
            isValid = false;
        }
        return isValid;
    }
}
