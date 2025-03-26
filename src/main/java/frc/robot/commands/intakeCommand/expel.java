package frc.robot.commands.intakeCommand;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;

public class expel extends Command {
  private final ShooterSubsystem shooterSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public expel(ShooterSubsystem shooterSubsystem) {
    
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("expel başladi!!!");
    shooterSubsystem.isIntaking = false;
    shooterSubsystem.timer.reset();
    shooterSubsystem.isExpelling = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("expel power", 0.5);
    shooterSubsystem.startExpel();
  
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopExpel();
    shooterSubsystem.isIntaking = false;
    System.out.println("expel bitti!!!!");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !shooterSubsystem.isExpelling;
  }
}