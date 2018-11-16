package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Instances of Hardware provide several methods for initializing
 * and controlling the robot.
 */

public class Hardware {
    //private ColorSensor colorSensor;
    private ElapsedTime     runtime = new ElapsedTime();
    private DcMotor spinner, lifter, slider, frontLeft, frontRight, backLeft, backRight;
    private CRServo spinnyLeft, spinnyRight;
    private Servo doorJaunt;
    private boolean closed = true;


    /*private Servo flipper, claw, jewelSweeper;
    private boolean flipperDown, clawClosed;*/
    private Telemetry telemetry;
    /*private final ElapsedTime runtime = new ElapsedTime();

    private VuforiaTrackable relicTemplate;*/

    void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    void init(HardwareMap hardwareMap) {
        spinner = hardwareMap.dcMotor.get("spinner");
        lifter = hardwareMap.dcMotor.get("lifter");
        slider = hardwareMap.dcMotor.get("slider");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        spinnyLeft = hardwareMap.crservo.get("left");
        spinnyRight = hardwareMap.crservo.get("right");
        doorJaunt = hardwareMap.servo.get("door");

        flipper = hardwareMap.servo.get("flipper");
        colorJaunt = hardwareMap.colorSensor.get("colorJaunt");

       /* lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        /*leftSlide     = hardwareMap.dcMotor.get("leftSlide");
        rightSlide    = hardwareMap.dcMotor.get("rightSlide");
        leftConveyor  = hardwareMap.dcMotor.get("leftConveyor");
        rightConveyor = hardwareMap.dcMotor.get("rightConveyor");
        leftWheel     = hardwareMap.dcMotor.get("leftWheel");
        rightWheel    = hardwareMap.dcMotor.get("rightWheel");
        centerWheel   = hardwareMap.dcMotor.get("centerWheel");
        clawArm       = hardwareMap.dcMotor.get("clawArm");
        flipper       = hardwareMap.servo.get("flipper");
        claw          = hardwareMap.servo.get("claw");
        jewelSweeper  = hardwareMap.servo.get("jewelSweeper");
        colorSensor   = hardwareMap.colorSensor.get("colorSensor");

        leftSlide .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawArm   .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSlide .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        clawArm   .setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftSlide    .setDirection(DcMotorSimple.Direction.REVERSE);
        rightConveyor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightWheel   .setDirection(DcMotorSimple.Direction.REVERSE);
        centerWheel  .setDirection(DcMotorSimple.Direction.REVERSE);

        wheelBrake(false);
        leftSlide .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        clawArm   .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);*/

        telemetry.addLine("Initialized Hardware");
        telemetry.update();
    }

    void setWheelEncoderMode(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Encoders Initialized");
        telemetry.update();
    }

    void ocLift(float liftPower) {
        lifter.setPower(liftPower);
    }
    void ocDontLift(float liftPower){
        lifter.setPower(-1*liftPower);
    }

    void ocSlide(float slidePower){
        slider.setPower(slidePower/2);
    }

    void setWheelPower(float frontLeftPower, float frontRightPower, float backLeftPower, float backRightPower) {
        backLeft.setPower(backLeftPower);
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

    void setServoPositions(float powerJaunt) {
        spinnyRight.setPower(powerJaunt);
        spinnyLeft.setPower(-1*powerJaunt);
    }

    void setDoorJaunt(){
        if(closed){
            doorJaunt.setPosition(0);
        }
        else{
            doorJaunt.setPosition(0.5);
        }
        closed = !closed;

    }

    void ocSpin(double power){
        spinner.setPower(power);
    }


    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;


    
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int)(leftInches*COUNTS_PER_INCH);
            newFrontRightTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBackRightTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);


            backLeft.setTargetPosition(newBackLeftTarget);
            frontLeft.setTargetPosition(newFrontLeftTarget);
            backRight.setTargetPosition(newBackRightTarget);
            frontRight.setTargetPosition(newFrontRightTarget);

            // Turn On RUN_TO_POSITION
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontLeft.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            frontRight.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.leftDrive.isBusy() && robot.rightDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.leftDrive.getCurrentPosition(),
                        robot.rightDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }

    float red1;
    float red2;

    public void mineralSample {
        flipper.setPosition(0.6); // lower flipper with color sensor
        sleep(500);
        red1 = colorJaunt.red(); // record red value of the first mineral
        if(red1 < 0.5){
            encoderDrive(0.4, 12, -12, 5.0); //turn the robot, knocking out the 1st mineral 
        }
        else{
            flipper.setPosition(0); // raise the flipper again
            sleep(500);
            encoderDrive(0.5, 6.0, 6.0, 10); // drive forward to next mineral 
            flipper.setPosition(0.6); // lower flipper again
            red2 = colorJaunt.red(); // record red value of the second mineral

            if(red2 < 0.5){
                encoderDrive(0.4, 12, -12, 5.0); //turn the robot, knocking out the 2nd mineral   
            }
            else{
                encoderDrive(0.4, -12, 12, 5.0); //turn the robot in the other direction, knocking out the 3rd mineral   
            }
        }
        
        
    } 

    /*void initVuforia() {
        telemetry.addLine("Initializing Vuforia");
        telemetry.update();
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = "AZLv+a7/////AAAAGdyzndpq4khMnz5IMjSvhiR0XbtOlL7ZfQytGj9s" +
                "4zFCFoa+IqUA1Cjv4ghfSjfRAlRguu6cVbQVM+0Rxladi3AIKhUjIL6v5ToFrK/fxrWdwAzkQfEPM1S" +
                "3ijrTSm1N8DuZ6UoqiKoVmQGzyiWhDpTQoR1zIVkj88rOhBDYwBf0CnW++pxZ0pHlQBbh/bzBjt63AN" +
                "cuI9JyHU3/JLGSBhoIm04G3UnrjVrjKfPFlX9NOwWQLOYjQ+4B1l4M8u9BdihYgmfMST0BHON+MQ7qC" +
                "5dMs/2OSZlSKSZISN/L+x606xzc2Sv5G+ULUpaUiChG7Zlv/rncu337WhZjJ1X2pQGY7gIBcSH+TUw8" +
                "1n2jYKkm";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforiaLocalizer = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = vuforiaLocalizer.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        relicTrackables.activate();
        telemetry.addLine("Initialized Vuforia");
        telemetry.update();
    }

    

    void wheelBrake(boolean brake) {
        if (brake) {
            leftWheel .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            leftWheel .setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            rightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    void runToPos(double speed, int leftPos, int rightPos, double timeoutS) throws InterruptedException {
        leftWheel .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftWheel .setTargetPosition(leftPos);
        rightWheel.setTargetPosition(rightPos);
        leftWheel .setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightWheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();
        leftWheel .setPower(speed);
        rightWheel.setPower(speed);

        while ((leftWheel.isBusy() || rightWheel.isBusy()) && runtime.seconds() < timeoutS) {
            Thread.sleep(2);
//            telemetry.addData("Left Wheel Pos",  leftWheel .getCurrentPosition());
//            telemetry.addData("Right Wheel Pos", rightWheel.getCurrentPosition());
//            telemetry.update();
        }

        leftWheel .setPower(0);
        rightWheel.setPower(0);
        Thread.sleep(500);
        leftWheel .setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void setJewelSweeperPosition(double position) {
        jewelSweeper.setPosition(position);
    }

    RelicRecoveryVuMark getVuMark() {
        return RelicRecoveryVuMark.from(relicTemplate);
    }

    /*boolean moreBlue() {
        return colorSensor.blue() > colorSensor.red();
    }

    String getRGB() {
        return colorSensor.red() + ", " + colorSensor.green() + ", " + colorSensor.blue();
    }*/
}
