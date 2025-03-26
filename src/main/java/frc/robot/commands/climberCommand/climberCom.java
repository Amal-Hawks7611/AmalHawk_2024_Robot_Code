package frc.robot.commands.climberCommand;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class climberCom extends Command {
    private ArmSubsystem subsystem;
    private double power;

    public climberCom(ArmSubsystem subsystem, double power) {
        this.subsystem = subsystem;
        this.power = power;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        System.out.println("intake basladi");
    }

    @Override
    public void execute() {
        subsystem.Climbfunct(power);

    }

    @Override
    public void end(boolean interrupted) {
        subsystem.Climbfunct(0);

        System.out.println("intake bitti");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
