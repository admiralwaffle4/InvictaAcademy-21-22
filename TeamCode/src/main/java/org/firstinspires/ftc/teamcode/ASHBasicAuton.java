package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auton Test",group="Tests")
public class ASHBasicAuton extends LinearOpMode {
    ASHardware ASH = new ASHardware();
    DcMotor[] wheels = new DcMotor[3];
    private ElapsedTime runtime = new ElapsedTime();

    // Constants to find the amount of encoder ticks per CM
    static final double COUNTS_PER_MOTOR_REV = 537.7;
    static final double DRIVE_GEAR_REDUCTION = 1.35;
    static final double WHEEL_DIAMETER_CM = 9.60;

    // Finds the amount of encoder ticks per MM
    static final double COUNTS_PER_CM = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_CM * 3.1415);

    @Override
    public void runOpMode() {

        //tells code what each wheel is
        wheels[0] = ASH.Left;
        wheels[1] = ASH.Right;
        wheels[2] = ASH.Middle;

        //tells the motors to stop and reset itself
        for (DcMotor wheel : wheels){
            wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            wheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        // Telemetry to show initial encoder position
        telemetry.addData("Path0",  "Starting at %7d :%7d :%7d :%7d", ASH.Left.getCurrentPosition(), ASH.Right.getCurrentPosition(), ASH.Middle.getCurrentPosition());
        telemetry.update();

        telemetry.update();

        waitForStart();

        encoderDrive(1,10,10,0,5);
    }

    public void encoderDrive(double speed, double leftCM, double rightCM,double middleCM, double timeoutS) {

        // Creates an array for the target positions for each of your motors
        int []newWheelTarget = new int[3];

        if (opModeIsActive()) {

            // Defines the target position for your motor
            newWheelTarget[0] = wheels[0].getCurrentPosition() + (int)(leftCM * COUNTS_PER_CM);
            newWheelTarget[1] = wheels[1].getCurrentPosition() + (int)(rightCM * COUNTS_PER_CM);
            newWheelTarget[2] = wheels[2].getCurrentPosition() + (int)(middleCM * COUNTS_PER_CM);

            for (int i = 0; i < 3; i++) {
                // Sets the target position for the motors
                wheels[i].setTargetPosition(newWheelTarget[i]);

                // Tells the motor to drive until they reach the target position
                wheels[i].setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            // reset the timeout time and start motion.
            runtime.reset();

            // Sets speed (negation indicates direction;
            // in this case negative is forwards because that's how the motor orientation was set up in the Hardware map and I don't wanna change all of my code to make forward positive)
            wheels[0].setPower(-speed);
            wheels[1].setPower(-speed);
            wheels[2].setPower(-speed);

            // Will keep looping until one of the of the motors reach the target position
            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (ASH.Left.isBusy() && ASH.Right.isBusy() && ASH.Middle.isBusy())) {
                // Displays the target position and current position in telemetry
                telemetry.addData("Path1",  "Running to %7d :%7d :%7d :%7d", newWheelTarget[0],  newWheelTarget[1], newWheelTarget[2]);
                telemetry.addData("Path2",  "Running at %7d :%7d :%7d :%7d", ASH.Left.getCurrentPosition(), ASH.Right.getCurrentPosition(), ASH.Middle.getCurrentPosition());
                telemetry.update();
            }

            for (DcMotor wheel : wheels){
                // Stops motors after motors have reached target position
                wheel.setPower(0);

                // Resets encoders
                wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            //  sleep(50);   // optional pause after each move
        }
    }
}
