package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="IDK Tele Op",group="Invicta Academy")
public class AcademyTeleOp extends LinearOpMode {
    //time and robot variables
    private ElapsedTime runtime = new ElapsedTime();
    ASHardware robot = new ASHardware();

    //setting  everything up
    DcMotor right,left,middle;
    float turn,drive,strafe;
    double leftPower,rightPower,middlePower;

    @Override
    public void runOpMode() {
        //getting robot data
        robot.init(hardwareMap);
        right = robot.Right;
        left = robot.Left;
        middle = robot.Middle;

        //wait for the driver to press the play button
        waitForStart();

        while (opModeIsActive()) {
            //get the values from gamepad
            drive = gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
            turn = gamepad1.right_stick_x;

            //make sure the values aren't outside of the range
            leftPower = Range.clip(drive + strafe - turn, -1.0, 1.0) ;
            rightPower = Range.clip(drive - strafe + turn, -1.0, 1.0) ;
            middlePower = Range.clip(strafe, -1.0, 1.0);

            //power the motors
            left.setPower(leftPower);
            right.setPower(rightPower);
            middle.setPower(middlePower);

            //give the telemetry the data
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Inputs", "drive (%.2f), strafe (%.2f), turn (%.2f)", drive, strafe, turn);
            telemetry.addData("Motors", "left (%.2f), right (%.2f), middle (%.2f)", leftPower, rightPower, middlePower);
            telemetry.update();

        }
    }
}
