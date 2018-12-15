package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous

public class autonomusMiddleMineral extends LinearOpMode{
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
        thanosCar.ocLift(1);
        sleep(3650);
        thanosCar.ocLift(0);
        thanosCar.encoderStrafe(0.2,10,10,10);
        thanosCar.encoderDrive(0.2,14,14,10);
        thanosCar.encoderTurn(90,-1);
        thanosCar.encoderDrive(0.2,28,28,10);
        thanosCar.encoderTurn(20,1);
        thanosCar.encoderDrive(0.1,5,5,5);
        thanosCar.encoderTurn(95,1);
        //thanosCar.encoderTurn(110,1);
        thanosCar.encoderDrive(0.2,45,45,5);
        //thanosCar.encoderTurn(5,1);
        thanosCar.placeMarker();
        sleep(1200);
        thanosCar.encoderStrafe(0.2,-10,-10,5);
        thanosCar.encoderDrive(0.2,-10,-10,5);
        thanosCar.encoderStrafe(0.2,10,10,5);
        thanosCar.encoderDrive(0.2,-60,-60,7);
        //thanosCar.encoderDrive(0.2,-10,-15,5);
        thanosCar.ocSpin(0.6);
        thanosCar.ocSlide(1);
        thanosCar.encoderTurn(180,1);
        sleep(1000);
        thanosCar.ocSpin(0);
        sleep(1000);
        thanosCar.ocSlide(0);





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