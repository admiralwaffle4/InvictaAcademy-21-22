package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ASHardware {
    DcMotor Left,Right,Middle;

    public void init(HardwareMap hardwareMap) {
        Left = hardwareMap.get(DcMotor.class, "frontLeft");
        Right = hardwareMap.get(DcMotor.class, "frontRight");
        Middle = hardwareMap.get(DcMotor.class, "backLeft");

        Left.setDirection(DcMotor.Direction.FORWARD);
        Right.setDirection(DcMotor.Direction.REVERSE);
        Middle.setDirection(DcMotor.Direction.FORWARD);

        Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Middle.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Left.setPower(0);
        Right.setPower(0);
        Left.setPower(0);


    }
}
