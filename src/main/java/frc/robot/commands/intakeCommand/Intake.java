package frc.robot.commands.intakeCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;

public class Intake extends Command {
    private IntakeSubsystem subsystem;

    public Intake(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        System.out.println("intake basladi");
    }

    @Override
    public void execute() {
        subsystem.intake(-0.50);
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.intake(0);
        System.out.println("intake bitti");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
