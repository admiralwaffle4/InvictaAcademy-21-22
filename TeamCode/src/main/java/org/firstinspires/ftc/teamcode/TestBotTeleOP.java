package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="Mecanum TeleOp",group="Tests")
public class TestBotTeleOP extends LinearOpMode {
    DcMotor[] wheels = new DcMotor[4];
    DcMotor spin;
    TestBotHardware robot = new TestBotHardware();

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        wheels[0] = robot.frontLeft;
        wheels[1] = robot.backLeft;
        wheels[2] = robot.frontRight;
        wheels[3] = robot.backRight;
    }
}