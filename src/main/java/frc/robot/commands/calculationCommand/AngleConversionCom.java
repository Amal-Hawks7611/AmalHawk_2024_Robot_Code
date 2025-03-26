package frc.robot.commands.calculationCommand;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
//import frc.robot.subsystems.ArmSubsystem;

public class AngleConversionCom extends Command {
    //private ArmSubsystem subsystem = new ArmSubsystem(0, 0, 0, 0);

    double offset = LimelightHelpers.getTY("limelight");
    final double Limelight_Angle = 25.0;

    final double limelightLensHeight = Units.metersToInches(0.325);

    double goalAngleDegrees = Limelight_Angle + offset;
    double goalAngleRadian = Units.degreesToRadians(goalAngleDegrees);

    double Distance(double goalHeight) {
        double distance = (goalHeight - limelightLensHeight) / Math.tan(goalAngleRadian);
        return distance;
    }

    double AngleGiver(double goalHeight) {
        double angle = Math.atan(goalHeight / Distance(goalHeight));
        return angle;
    }

    double angleToTick(double angle) {

        //double arm_angle = subsystem.getEncoder1() * subsystem.EncoderRatio;
        //double setpoint = angle - arm_angle;
        //double error = 23;

        return 0.1;
    }
}
