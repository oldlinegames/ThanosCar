package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous

public class autonomusOffHook extends LinearOpMode{
    private Hardware thanosCar;


    @Override
    public void runOpMode() throws InterruptedException {
        thanosCar = new Hardware(hardwareMap, telemetry, this);
        //thanosCar.initVuforia();

        waitForStart();
        thanosCar.reverseWheels();
        thanosCar.setWheelEncoderModeAuto();

        //thanosCar.encoderDrive(0.1,20,20,10);


        //thanosCar.encoderDrive(0.1,72,-72,10);
        thanosCar.encoderLift(3000);






        /*thanosCar.toPosition(OpenGLMatrix.translation(-1480, 0, 0)
                        .multiplied(Orientation.getRotationMatrix(
                                AxesReference.EXTRINSIC, AxesOrder.XYZ,
                                AngleUnit.DEGREES, 0, 0, 90
                        )), this);
        thanosCar.encoderTurn(90,-1);
        thanosCar.encoderDrive(0.35,45,45,5);
        thanosCar.encoderTurn(180,1);*/


        /*
        thanosCar.encoderDrive(0.5,10,10,10);
        thanosCar.mineralSample();
        thanosCar.encoderDrive(0.5,10,10,10);
        sleep(500);
        thanosCar.marker.setPosition(0);
        sleep(500);*/


    }
}