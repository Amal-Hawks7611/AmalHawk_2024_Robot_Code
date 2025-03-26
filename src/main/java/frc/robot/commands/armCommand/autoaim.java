package frc.robot.commands.armCommand;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.VisionConstants;
import edu.wpi.first.wpilibj2.command.Command;

public class autoaim extends Command {
        private final double armLengthFeet = 2.426; 
        private final ArmSubsystem subsystem;
        private final ShooterSubsystem shooter_subsys;
        public double hedef_setpoint;
        private pid pidCommand;
    public autoaim(ShooterSubsystem shooter,ArmSubsystem armSubsystem) {
        subsystem = armSubsystem;
        shooter_subsys = shooter;
        addRequirements(armSubsystem,shooter_subsys);
    }
    @Override
    public void initialize() {
        System.out.println("AutoAim initialized");
    }

    @Override
    public void execute() {
        hedef_setpoint = calculateEncoderTicksFromAngle(calculateRequiredAngleWithLimelight(VisionConstants.amptarget,VisionConstants.robotheight,25.0));
        System.out.println("Setpoint: " + hedef_setpoint);
        if (pidCommand == null || pidCommand.isFinished()) {
            pidCommand = new pid(shooter_subsys,subsystem, hedef_setpoint);
            pidCommand.schedule();
            System.out.println("PID Başlayck");
        }
    }   
private double calculateRequiredAngleWithLimelight(double targetHeight, double limelightHeight, double limelightAngleDegrees) {
    double ty = LimelightHelpers.getTY("limelight");
    double targetOffsetAngleVertical = ty;
    double totalAngleDegrees = limelightAngleDegrees + targetOffsetAngleVertical;
    double totalAngleRadians = Math.toRadians(totalAngleDegrees);
    double heightDifference = targetHeight - limelightHeight;
    double distanceToTarget = heightDifference / Math.tan(totalAngleRadians);
    double requiredAngleRadians = Math.atan(heightDifference / distanceToTarget);
    double requiredAngleDegrees = Math.toDegrees(requiredAngleRadians);
    return requiredAngleDegrees;
}
private double calculateEncoderTicksFromAngle(double angleDegrees) {
    double requiredDistanceFeet = armLengthFeet * Math.tan(Math.toRadians(angleDegrees));
    return requiredDistanceFeet;
}
    @Override
    public void end(boolean interrupted) {
        System.out.println("AutoAim ended");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
