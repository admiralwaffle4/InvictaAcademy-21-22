package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Mecanum TeleOp",group="Tests")
public class TestBotTeleOP extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    DcMotor[] wheels = new DcMotor[4];
    double[] power = new double[4];
    double[] prePower = new double[4];
    double drive, strafe, turn;
    DcMotor spin;
    TestBotHardware robot = new TestBotHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        wheels[0] = robot.frontLeft;
        wheels[1] = robot.backLeft;
        wheels[2] = robot.frontRight;
        wheels[3] = robot.backRight;
        spin = robot.spin;

        for (DcMotor wheel:wheels) {
            wheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
        spin.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            drive = -gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            strafe = gamepad1.left_stick_x;

            prePower[0] = .7 * drive - .8 * strafe + .75 * turn;
            prePower[1] = -.7 * drive - .8 * strafe - .75 * turn;
            prePower[2] = .7 * drive + .8 * strafe - .75 * turn;
            prePower[3] = -.7 * drive + .8 * strafe + .75 * turn;

            for (int a = 0; a <= 3; a++) {
                power[a] = Range.clip(prePower[a], -1.0,1.0);
            }

            for (int b = 0; b <=3; b++) {
                wheels[b].setPower(power[b]);
            }

            float spinLeft = -gamepad1.left_trigger;
            float spinRight = -gamepad1.right_trigger;

            double spinPower = spinRight - spinLeft;

            spin.setPower(spinPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Inputs", "drive (%.2f), strafe (%.2f), turn (%.2f)", drive, strafe, turn);
            telemetry.addData("Motors", "frontLeft (%.2f), backLeft (%.2f), frontRight (%.2f), backRight (%.2f)", power[0], power[1], power[2], power[3]);
            telemetry.update();
        }
    }
}