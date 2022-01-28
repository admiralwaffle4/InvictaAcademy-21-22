package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="IDK Tele Op",group="Invicta Academy")
public class AcademyTeleOp extends LinearOpMode {
    ASHardware robot = new ASHardware();
    DcMotor right,left,middle;
    float turn,drive,strafe;
    double leftPower,rightPower,middlePower;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        right = robot.Right;
        left = robot.Left;
        middle = robot.Middle;

        waitForStart();

        while (opModeIsActive()) {
            drive = gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            turn = -gamepad1.right_stick_x;

            leftPower = drive - strafe + turn;
            rightPower = drive + strafe + turn;
            middlePower = strafe;

            double max;
            max = Math.max(Math.abs(leftPower), max);
            max = Math.max(Math.abs(rightPower), max);
            max = Math.max(Math.abs(middlePower), max);

            if (max > 1) {
                leftPower /= max;
                rightPower /= max;
                middlePower /= max;
            }

            left.setPower(leftPower * .5);
            right.setPower(rightPower * .5);
            middle.setPower(middlePower * .5);

        }
    }
}
