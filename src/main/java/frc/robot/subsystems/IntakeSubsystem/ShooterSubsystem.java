package frc.robot.subsystems.IntakeSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.TimedRobot;

public class ShooterSubsystem extends SubsystemBase {
    private final CANSparkMax ShooterMotor1;
    private final CANSparkMax ShooterMotor2;
    private final IntakeSubsystem intakeSubsystem; 
    private final RelativeEncoder Encoder1;
    private final RelativeEncoder Encoder2;
    private double old2Pos = 0;
    private double RPM2 = old2Pos;

    public final Timer timer = new Timer();
    public boolean isIntaking = false;
    public boolean isExpelling = false;

    public ShooterSubsystem(IntakeSubsystem intakeSystem) {
        intakeSubsystem = intakeSystem;
        ShooterMotor1 = new CANSparkMax(14, MotorType.kBrushless);
        ShooterMotor2 = new CANSparkMax(13, MotorType.kBrushless);
        Encoder1 = ShooterMotor1.getEncoder();
        Encoder2 = ShooterMotor2.getEncoder();
        isIntaking = false;
        isExpelling=false;
    }

    public void shootv(double velocity) {
        ShooterMotor1.set(velocity);
        ShooterMotor2.set(velocity);
    }

    public void shootp(double power) {
        ShooterMotor1.set(power);
        ShooterMotor2.set(-power);
    }

    public void startExpel() {
        shootp(0.569);
    }

    public void stopExpel() {
        timer.stop();
        isExpelling = false;
        shootp(0);
        intakeSubsystem.intake(0);
        System.out.println("Expel işlemi durduruldu.");
    }

    @Override
    public void periodic() {
        RPM2 = (Math.abs(Encoder2.getPosition()) - old2Pos) * (60 / TimedRobot.kDefaultPeriod) * (1 / 0.3);
        SmartDashboard.putNumber("Expel1 Velocity", Encoder1.getVelocity());
        SmartDashboard.putNumber("Expel2 Velocity", Encoder2.getVelocity());
        SmartDashboard.putNumber("Expel1 Position", Encoder1.getPosition());
        SmartDashboard.putNumber("Expel2 Position", Encoder2.getPosition());
        SmartDashboard.putNumber("Expel2 Rotation", RPM2);
        old2Pos = Math.abs(Encoder2.getPosition());
        if (isExpelling && RPM2 <= 3100 && RPM2 >=2900 && !isIntaking) {
            System.out.println("RPM2 3000’i geçti! Intake başlatılıyor...");
            intakeSubsystem.intake(-0.5);
            isIntaking = true;
            timer.reset();
            timer.start();
        }
        if (isIntaking && timer.hasElapsed(2)) {
            System.out.println("Durduruldu Boru");
            isExpelling =false;
            stopExpel();
        }
    }

    public double getRPM2() {
        return RPM2;
    }
}
