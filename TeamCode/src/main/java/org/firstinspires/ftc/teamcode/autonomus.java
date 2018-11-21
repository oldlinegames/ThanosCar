package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class autonomus extends LinearOpMode{
    private Hardware thanosCar;

    @Override
    public void runOpMode() throws InterruptedException {
        thanosCar = new Hardware(this);
        thanosCar.setTelemetry(telemetry);
        thanosCar.init(hardwareMap);
        thanosCar.reverseWheels();

        thanosCar.setWheelEncoderMode();
        waitForStart();

        thanosCar.encoderDrive(0.5,10,10,10);


    }
}
