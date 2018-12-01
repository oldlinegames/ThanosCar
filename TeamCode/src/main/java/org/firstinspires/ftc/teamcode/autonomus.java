package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous

public class autonomus extends LinearOpMode{
    private Hardware thanosCar;


    @Override
    public void runOpMode() throws InterruptedException {
        thanosCar = new Hardware(hardwareMap, telemetry, this);
        thanosCar.initVuforia();

        waitForStart();
        thanosCar.reverseWheels();
        thanosCar.setWheelEncoderModeAuto();

        //thanosCar.encoderDrive(0.1,20,20,10);


        //thanosCar.encoderDrive(0.1,72,-72,10);
        /*thanosCar.ocLift(1);
        sleep(3900);
        thanosCar.ocLift(0);*/
        /*thanosCar.encoderStrafe(0.3,10,10,10);
        thanosCar.encoderDrive(0.3,10,10,10);
        thanosCar.encoderTurn(90,1);
        thanosCar.encoderDrive(0.3,20,20,10);*/
        thanosCar.setTargetPos(
                OpenGLMatrix.translation(-1430, 0, 0)
                        .multiplied(Orientation.getRotationMatrix(
                                AxesReference.EXTRINSIC, AxesOrder.XYZ,
                                AngleUnit.DEGREES, 0, 0, 90
                        ))
        );

        thanosCar.adjustPosition(this);
        /*
        thanosCar.encoderDrive(0.5,10,10,10);
        thanosCar.mineralSample();
        thanosCar.encoderDrive(0.5,10,10,10);
        sleep(500);
        thanosCar.marker.setPosition(0);
        sleep(500);*/


    }
}
