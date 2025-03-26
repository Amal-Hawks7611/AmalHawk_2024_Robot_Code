package frc.robot.commands.armCommand;
import frc.robot.commands.intakeCommand.expel;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;

public class pid extends Command {
  private final ArmSubsystem subsystem; 
  private final ShooterSubsystem shooter_subsys;
  private final PIDController pid = new PIDController(0.5, 0.1, 0.1);
  private expel expel_command;
  public pid(ShooterSubsystem shooter, ArmSubsystem armSubsystem, double setpoint) {
    this.subsystem = armSubsystem;
    this.shooter_subsys = shooter;
    addRequirements(subsystem,shooter_subsys);
    pid.setSetpoint(setpoint);
  }

  @Override
  public void initialize() {
    System.out.print("PID basladi");
  }

  @Override
  public void execute() {
    subsystem.move_arm1(pid.calculate(subsystem.getEncoder1()));
    subsystem.move_arm2(pid.calculate(subsystem.getEncoder2()));
    System.out.println(subsystem.getEncoder1());
  }

  @Override
  public void end(boolean interrupted) {
    subsystem.move_arm1(0);
    subsystem.move_arm2(0);
    expel_command = new expel(shooter_subsys);
    expel_command.schedule();
    System.out.println("Bitti");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
