package frc.robot.commands.armCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class dpad extends Command {

  private final ArmSubsystem subsystem;

  private final double speed1;

  public dpad(ArmSubsystem subsystem, double speed1) {
    this.speed1 = speed1;
    this.subsystem = subsystem;

    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("dpad çalıştı!");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    subsystem.move_arm1(speed1);
    subsystem.move_arm2(speed1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    subsystem.move_arm1(0);
    subsystem.move_arm2(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
