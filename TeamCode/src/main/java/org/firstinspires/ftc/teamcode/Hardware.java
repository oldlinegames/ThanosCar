package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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
    private DcMotor lifter, frontLeft, frontRight, backLeft, backRight;

    /*private Servo flipper, claw, jewelSweeper;
    private boolean flipperDown, clawClosed;*/
    private Telemetry telemetry;
    /*private final ElapsedTime runtime = new ElapsedTime();

    private VuforiaTrackable relicTemplate;*/

    void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    void init(HardwareMap hardwareMap) {
        lifter = hardwareMap.dcMotor.get("lifter");
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

    void setServoPositions() {
        /*flipper     .setPosition(.9);
        claw        .setPosition(0);
        jewelSweeper.setPosition(.5);*/
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

    int getGetClawArmPos() {
        return clawArm.getCurrentPosition();
    }*/

    /*void setWheelPower(double leftPower, double rightPower) {
        backLeft.setPower(leftPower);
        frontLeft.setPower(leftPower);
        frontRight.setPower(rightPower);
        backRight.setPower(rightPower);
    }*/

    void ocLift(float liftPower) {
        lifter.setPower(liftPower);
    }
    void ocDontLift(float liftPower){
        lifter.setPower(-1*liftPower);
    }
    /*void setCenterPower(double power) {
        centerWheel.setPower(power);
    }

    void setClawArmPower(double power) {
        clawArm.setPower(power);
    }

    void toggleFlipper() {
        flipperDown ^= true;
        if (flipperDown) {
            flipper.setPosition(.25);
        } else {
            flipper.setPosition(.9);
        }
        telemetry.addData("Flipper Down", flipperDown);
        telemetry.update();
    }

    void toggleClaw() {
        clawClosed = !clawClosed;
        if (clawClosed) {
            claw.setPosition(.5);
        } else {
            claw.setPosition(0);
        }
    }

    void setLeftSlidePower(double power) {
        leftSlide.setPower(power);
    }

    void setRightSlidePower(double power) {
        rightSlide.setPower(power);
    }

    void setConveyorPower(double leftPower, double rightPower) {
        leftConveyor .setPower(leftPower);
        rightConveyor.setPower(rightPower);
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
