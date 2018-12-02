package org.firstinspires.ftc.teamcode;

import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
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
    private Servo doorJaunt, flipper;
    public Servo marker;
    private boolean closed = true;
    private ColorSensor colorJaunt;
    private LinearOpMode OpModeJaunt;

    private VuforiaTrackables navTargets;
    private OpenGLMatrix lastPos;
    /*private Servo flipper, claw, jewelSweeper;
    private boolean flipperDown, clawClosed;*/
    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    /*private final ElapsedTime runtime = new ElapsedTime();

    private VuforiaTrackable relicTemplate;*/
    Hardware(LinearOpMode nojons){
        OpModeJaunt =  nojons;
    }

    Hardware(HardwareMap hardwareMap, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

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
        marker = hardwareMap.servo.get("marker");

        flipper = hardwareMap.servo.get("flipper");
        colorJaunt = hardwareMap.colorSensor.get("colorJaunt");
    }

    void setTelemetry(Telemetry telemetry) {

    }


    void reverseWheels(){
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
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

    void setWheelPower(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
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
            doorJaunt.setPosition(0.8);
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
        newFrontLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        newBackLeftTarget = backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
        newFrontRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
        newBackRightTarget = backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);


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
        while (OpModeJaunt.opModeIsActive() &&
                (runtime.seconds() < timeoutS) &&
                (backLeft.isBusy() && backRight.isBusy())) {

            // Display it for the driver.
            telemetry.addData("Path1", "Running to %7d :%7d", newBackLeftTarget, newBackRightTarget);
            telemetry.addData("Path2", "Running at %7d :%7d",
                    backLeft.getCurrentPosition(),
                    backRight.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);

        // Turn off RUN_TO_POSITION
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        OpModeJaunt.sleep(250);   // optional pause after each move
    }

    float red1;
    float red2;

    public void mineralSample() {
        colorJaunt.enableLed(true);
        flipper.setPosition(0.6); // lower flipper with color sensor
        OpModeJaunt.sleep(500);
        red1 = colorJaunt.red(); // record red value of the first mineral
        if(red1 < 0.5){
            encoderDrive(0.4, 12, -12, 5.0); //turn the robot, knocking out the 1st mineral 
        }
        else{
            flipper.setPosition(0); // raise the flipper again
            OpModeJaunt.sleep(500);
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

    public void placeMarker(){
        marker.setPosition(1);

    }
    public void colorSensorTest(){
        colorJaunt.enableLed(true);
        telemetry.addLine(Integer.toString(colorJaunt.red()));
        telemetry.update();
        /*if(colorJaunt.red()<0.5){
            telemetry.addLine("yellow");
            telemetry.update();
        }
        else if(colorJaunt.red()>0.5){
            telemetry.addLine("white");
            telemetry.update();
        }*/
    }
    
    void initVuforia() {
        telemetry.addLine("Initializing Vuforia");
        telemetry.update();

        int cameraMonitorViewId = hardwareMap.appContext.getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        parameters.vuforiaLicenseKey = "AZLv+a7/////AAAAGdyzndpq4khMnz5IMjSvhiR0XbtOlL7ZfQytGj9s" +
                "4zFCFoa+IqUA1Cjv4ghfSjfRAlRguu6cVbQVM+0Rxladi3AIKhUjIL6v5ToFrK/fxrWdwAzkQfEPM1S" +
                "3ijrTSm1N8DuZ6UoqiKoVmQGzyiWhDpTQoR1zIVkj88rOhBDYwBf0CnW++pxZ0pHlQBbh/bzBjt63AN" +
                "cuI9JyHU3/JLGSBhoIm04G3UnrjVrjKfPFlX9NOwWQLOYjQ+4B1l4M8u9BdihYgmfMST0BHON+MQ7qC" +
                "5dMs/2OSZlSKSZISN/L+x606xzc2Sv5G+ULUpaUiChG7Zlv/rncu337WhZjJ1X2pQGY7gIBcSH+TUw8" +
                "1n2jYKkm";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        VuforiaLocalizer vuforiaLocalizer = ClassFactory.getInstance().createVuforia(parameters);
        navTargets = vuforiaLocalizer.loadTrackablesFromAsset("RoverRuckus");

        VuforiaTrackable frontTarget = navTargets.get(2);
        VuforiaTrackable redTarget   = navTargets.get(1);
        VuforiaTrackable backTarget  = navTargets.get(3);
        VuforiaTrackable blueTarget  = navTargets.get(0);

        frontTarget .setName("FrontWall");
        redTarget   .setName("RedWall");
        backTarget  .setName("BackWall");
        blueTarget  .setName("BlueWall");

        final float mmPerInch = 25.4f;
        final float fieldWidth = 72 * mmPerInch;
        final float targetHeight = 6 * mmPerInch;

        frontTarget.setLocation(OpenGLMatrix
                .translation(-fieldWidth, 0, targetHeight)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, 90, 0, 90
                )));

        redTarget.setLocation(OpenGLMatrix
                .translation(0, -fieldWidth, targetHeight)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, 90, 0, 180
                )));

        backTarget.setLocation(OpenGLMatrix
                .translation(fieldWidth, 0, targetHeight)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, 90, 0, -90
                )));

        blueTarget.setLocation(OpenGLMatrix
                .translation(0, fieldWidth, targetHeight)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, 90, 0, 0
                )));

        for (VuforiaTrackable navTarget : navTargets) {
            telemetry.addData(navTarget.getName(), navTarget.getLocation().formatAsTransform());
        }

        OpenGLMatrix phoneLocation = OpenGLMatrix
                .translation(-140, -140, 345)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.XYZ,
                        AngleUnit.DEGREES, 90, 0, -90
                ));
        telemetry.addData("Phone", phoneLocation.formatAsTransform());

        for (VuforiaTrackable target : navTargets) {
            ((VuforiaTrackableDefaultListener) target.getListener())
                    .setPhoneInformation(phoneLocation, parameters.cameraDirection);
        }

        navTargets.activate();

        telemetry.addLine("Initialized Vuforia");
        telemetry.update();
    }
<<<<<<< HEAD

    void setTargetPos(OpenGLMatrix targetPos) {
        this.targetPos = targetPos;
    }

    boolean toPosition(OpenGLMatrix targetPos, LinearOpMode opMode) {
        ElapsedTime runtime = new ElapsedTime();
        runtime.reset();
        while (runtime.milliseconds() < 500) {
            opMode.idle();
        }
        OpenGLMatrix location = getRobotLocation();

        if (location == null) {
            return false;
        } else {
            telemetry.addLine("Location found!");
            telemetry.update();

            VectorF diff = targetPos.getTranslation().subtracted(location.getTranslation());

            float locationAngle = Orientation.getOrientation(location, AxesReference.EXTRINSIC,
                    AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
            float targetAngle = Orientation.getOrientation(targetPos, AxesReference.EXTRINSIC,
                    AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;

            telemetry.addData("Location Angle", locationAngle);
            telemetry.addData("Target Angle", targetAngle);
            telemetry.addData("Location", location.formatAsTransform());
            telemetry.addData("Target", targetPos.formatAsTransform());
            telemetry.update();

            // Go to x of target
            
            //turnLeft(-locationAngle, .3, 5_000, opMode);
            //driveForward(diff.getData()[0], .3, 5_000, opMode);
            encoderTurn(-locationAngle,-1);
            encoderDrive(0.2, diff.getData()[0], diff.getData()[0], 5);

            // Go to y of target
            //turnLeft(90, .3, 5_000, opMode);
            //driveForward(diff.getData()[1], .3, 5_000, opMode);
            encoderTurn(90, -1);
            encoderDrive(0.2, diff.getData()[1], diff.getData()[1], 5);

            // Go to angle of target
            //turnLeft(targetAngle - 90, .3, 5_000, opMode);
            encoderTurn(targetAngle - 90, -1);

            return true;
        }
    }

    OpenGLMatrix getRobotLocation() {
        VuforiaTrackable visible = null;
        for (VuforiaTrackable trackable : navTargets) {
            if (((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible()) {
                visible = trackable;
                break;
            }
        }

        if (visible == null) {
            return null;
        }

        OpenGLMatrix pos = ((VuforiaTrackableDefaultListener) visible.getListener()).getUpdatedRobotLocation();
        if (pos != null) {
            lastPos = pos;
        }
        return lastPos;
    }
=======
>>>>>>> parent of bd241ab... nj
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
