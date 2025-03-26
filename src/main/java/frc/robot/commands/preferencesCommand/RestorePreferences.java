package frc.robot.commands.preferencesCommand;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class RestorePreferences extends Command {
    private final double holdTime;
    private final Timer timer = new Timer();

    public RestorePreferences(double holdTime) {
        this.holdTime = holdTime;
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start(); 
    }

    @Override
    public void execute() {
        if (timer.get() >= holdTime) {
            System.out.println("Veriler Silindi.");
            Preferences.removeAll();
            timer.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        timer.stop(); 
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= holdTime;
    }
}
