package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class autonomus extends LinearOpMode{
    private Hardware thanosCar = new Hardware();

    @Override
    public void runOpMode() throws InterruptedException {
        thanosCar.setTelemetry(telemetry);
        thanosCar.init(hardwareMap);

        thanosCar.setWheelEncoderMode();
        
        waitForStart();


    }
}
