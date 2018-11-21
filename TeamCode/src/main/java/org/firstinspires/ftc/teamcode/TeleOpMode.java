package org.firstinspires.ftc.teamcode;

import android.content.Context;
import android.media.MediaPlayer;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * This is the opmode for the teleop period of the competition.
 * <p>
 * Pressing up, down, and left/right on the {@link #gamepad1} directional pad changes the wheel
 * speed to 1, .75, and .5 respectively.
 * The directional pad on {@link #gamepad2} controls the speed of the linear slides, setting
 * the speed to 1, .5, and .1.
 * Pressing the 'B' button on {@link #gamepad1} toggles the flipper to move up and down.
 * Pressing the 'X' button on {@link #gamepad1} reverses the wheels.
 * The y of {@link #gamepad1}'s sticks control the wheels.
 * The y of {@link #gamepad2}'s sticks control the conveyor belts.
 * The triggers of {@link #gamepad1} control the center wheel of the robot for strafing.
 * The triggers and bumpers of {@link #gamepad2} control the linear slides.
 * The 'Y' and 'A' buttons of {@link #gamepad2} control the claw arm.
 * The 'X' button of {@link #gamepad2} toggles the claw.
 */

@TeleOp
public class TeleOpMode extends OpMode {
    private static final double CLAW_ARM_SPEED = .25;
    private final Hardware thanosCar = new Hardware();
    private OmaeTron omaeWa  = new OmaeTron();
    private apagando sombra = new apagando();
    private double wheelSpeed = 1;
    private boolean lastYPressed = false;
    private boolean slowMode = false;
    //private double slideSpeed = 1;
    //private boolean prevB1, prevX1, prevX2, prevY2, reverse, clawControl;

    @Override
    public void init() {
        thanosCar.setTelemetry(telemetry);
        thanosCar.init(hardwareMap);
    }

    @Override
    public void start() {
        thanosCar.setServoPositions(0);
        thanosCar.reverseWheels();
    }

    @Override
    public void loop() {
        lastYPressed = gamepad1.y;
        thanosCar.ocLift(gamepad1.right_trigger);
        thanosCar.ocDontLift(gamepad1.left_trigger);
        if(slowMode){
            thanosCar.setWheelPower((gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x)/3,
                    (gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x)/3,
                    (gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x)/3,
                    (gamepad1.left_stick_y+ gamepad1.right_stick_x - gamepad1.left_stick_x)/3
            );
        }
        else{
            thanosCar.setWheelPower(gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x,
                    gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x,
                    gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x,
                    gamepad1.left_stick_y+ gamepad1.right_stick_x - gamepad1.left_stick_x
            );
        }

        if(gamepad2.right_bumper){
            thanosCar.ocSlide(1);
        }

        else if(gamepad2.left_bumper){
            thanosCar.ocSlide(-1);
        }
        else
        {
            thanosCar.ocSlide(0);
        }

        if(gamepad1.y && !lastYPressed){
            thanosCar.setDoorJaunt();
        }
        if(gamepad1.a){
            thanosCar.setServoPositions(1);
            //gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
        }

        else if(gamepad1.b){
            thanosCar.setServoPositions(-1);
        }
        
        else{
            thanosCar.setServoPositions(0);
        }
        thanosCar.ocSpin(gamepad2.left_stick_y*0.93);
        thanosCar.ocSpin(gamepad2.right_stick_y);

        while(gamepad2.dpad_up){
            thanosCar.ocSpin(-0.9);
        }

        while(gamepad2.dpad_down){
            thanosCar.ocSpin(0.9);
        }

        if(gamepad1.dpad_down){
            slowMode = true;
            telemetry.addLine("Slow Mode Enabled");
            telemetry.update();
        }
        else if(gamepad1.dpad_up){
            slowMode = false;
            telemetry.addLine("Slow Mode Disabled");
            telemetry.update();
        }


        /*if(gamepad1.x){
            omaeWa.start(this.hardwareMap.appContext);
        }
        if(gamepad1.y){
            sombra.start(this.hardwareMap.appContext);
        }*/
        
        /*thanosCar.setWheelPower(gamepad1.right_stick_y,gamepad1.left_stick_y
                /*(reverse ? gamepad1.right_stick_y : -gamepad1.left_stick_y) * wheelSpeed,
                (reverse ? gamepad1.left_stick_y : -gamepad1.right_stick_y) * wheelSpeed);*/
        /*thanosCar.setCenterPower(
                reverse ? -gamepad1.right_trigger : -gamepad1.left_trigger);
        /*if ((reverse ? gamepad1.left_trigger : gamepad1.right_trigger) != 0) {
            thanosCar.setCenterPower(
                    reverse ? gamepad1.left_trigger : gamepad1.right_trigger);
        }*/

        /*if (gamepad1.dpad_up) {
            wheelSpeed = 1;
        }
        if (gamepad1.dpad_left || gamepad1.dpad_right) {
            wheelSpeed = .5;
        }
        if (gamepad1.dpad_down) {
            wheelSpeed = .25;
        }
        if (!prevB1 && gamepad1.b) {
            thanosCar.toggleFlipper();
        }
        if (!prevX1 && gamepad1.x) {
            reverse ^= true;
        }
        if (gamepad2.y && !prevY2) {
            clawControl ^= true;
            thanosCar.setConveyorPower(0, 0);
        }
        if (clawControl) {
            if (thanosCar.getGetClawArmPos() < -50 || gamepad2.left_stick_y < 0) {
                thanosCar.setClawArmPower(CLAW_ARM_SPEED * gamepad2.left_stick_y);
            }
        } else {
            thanosCar.setConveyorPower(-gamepad2.left_stick_y, -gamepad2.right_stick_y);
        }
        if (gamepad2.x && !prevX2) {
            thanosCar.toggleClaw();
        }

        if (gamepad2.dpad_up) {
            slideSpeed = 1;
        }
        if (gamepad2.dpad_left || gamepad2.dpad_right) {
            slideSpeed = .5;
        }
        if (gamepad2.dpad_down) {
            slideSpeed = .1;
        }

        if (gamepad2.left_trigger > 0) {
            thanosCar.setLeftSlidePower(slideSpeed);
        } else if (gamepad2.left_bumper) {
            thanosCar.setLeftSlidePower(-slideSpeed);
        } else {
            thanosCar.setLeftSlidePower(0);
        }
        if (gamepad2.right_trigger > 0) {
            thanosCar.setRightSlidePower(1);
        } else if (gamepad2.right_bumper) {
            thanosCar.setRightSlidePower(-1);
        } else {
            thanosCar.setRightSlidePower(0);
        }

        thanosCar.setJewelSweeperPosition(.5);

        prevX1 = gamepad1.x;
        prevX2 = gamepad2.x;
        prevB1 = gamepad1.b;
        prevY2 = gamepad2.y;*/
    }


    }

