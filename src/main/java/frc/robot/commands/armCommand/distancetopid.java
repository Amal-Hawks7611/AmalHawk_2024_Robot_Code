package frc.robot.commands.armCommand;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.VisionConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
public class distancetopid extends Command {
        private final ArmSubsystem subsystem;
        private final ShooterSubsystem shooter_subsys;
        public double uzaklik;
        private InterpolatingDoubleTreeMap map = new InterpolatingDoubleTreeMap();
        private pid pidCommand;
        public distancetopid(ShooterSubsystem shooter,ArmSubsystem armSubsystem) {
        subsystem = armSubsystem;
        shooter_subsys = shooter;
        addRequirements(armSubsystem,shooter_subsys);
    }
    @Override
    public void initialize() {
        map.put(22.2,-11.67);
        map.put(29.12,-9.87);
        map.put(35.7,-9.45);
        map.put(42.0,-8.99);
        System.out.println("AutoAim initialized");
    }

    @Override
    public void execute() {
        uzaklik = calculatDistanceWithLimelight(VisionConstants.amptarget,VisionConstants.robotheight,25.0);
        if(map.get(uzaklik) != -9.869884278212197){
            if (pidCommand == null || pidCommand.isFinished()) {
                pidCommand = new pid(shooter_subsys,subsystem, map.get(uzaklik));
                pidCommand.schedule();
        }}
    }   
private double calculatDistanceWithLimelight(double targetHeight, double limelightHeight, double limelightAngleDegrees) {
    double ty = LimelightHelpers.getTY("limelight");
    double targetOffsetAngleVertical = ty;
    double totalAngleDegrees = limelightAngleDegrees + targetOffsetAngleVertical;
    double totalAngleRadians = Math.toRadians(totalAngleDegrees);
    double heightDifference = targetHeight - limelightHeight;
    double distanceToTarget = heightDifference / Math.tan(totalAngleRadians);
    return distanceToTarget;
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
