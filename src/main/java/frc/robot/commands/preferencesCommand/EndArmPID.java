package frc.robot.commands.preferencesCommand;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;

public class EndArmPID extends Command {

    public EndArmPID() {
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        Preferences.setDouble("HelloKittyMahmut",999.99);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Emergency verildi.");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
