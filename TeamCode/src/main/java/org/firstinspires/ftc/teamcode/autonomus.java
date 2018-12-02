package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous

public class autonomus extends LinearOpMode{
    private Hardware thanosCar = new Hardware();

    @Override
    public void runOpMode() throws InterruptedException {
<<<<<<< HEAD
        thanosCar.setTelemetry(telemetry);
        thanosCar.init(hardwareMap);

        thanosCar.setWheelEncoderMode();
        
        waitForStart();
=======
        thanosCar = new Hardware(this);
        thanosCar.setTelemetry(telemetry);
        thanosCar.init(hardwareMap);
        thanosCar.reverseWheels();

        thanosCar.setWheelEncoderMode();
        waitForStart();

        thanosCar.ocDontLift(1);
        sleep(1000);
        thanosCar.ocDontLift(0);
        thanosCar.setWheelPower(0.5,-0.5,-0.5,-0.5); // strafe out of the hook
        sleep(500);
        thanosCar.setWheelPower(0,0,0,0);
        thanosCar.encoderDrive(0.5,10,10,10);
        thanosCar.mineralSample();
        thanosCar.encoderDrive(0.5,10,10,10);
        sleep(500);
        thanosCar.marker.setPosition(0);
        sleep(500);
>>>>>>> parent of bd241ab... nj


    }
}
