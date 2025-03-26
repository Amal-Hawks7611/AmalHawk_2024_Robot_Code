package frc.robot.commands.intakeCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;

public class outtake extends Command {

    IntakeSubsystem subsystem;
    double speed;

    public outtake(IntakeSubsystem subsystem) {

        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        System.out.println("intake basladi");
    }

    @Override
    public void execute() {
        subsystem.intake(0.6);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("intake bitti");
        subsystem.intake(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
