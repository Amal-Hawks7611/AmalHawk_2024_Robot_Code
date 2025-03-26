package frc.robot.subsystems.IntakeSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax IntakeMotor;

    public IntakeSubsystem() {
        IntakeMotor = new CANSparkMax(15, MotorType.kBrushless);
    }

    public void intake(double power) {

        IntakeMotor.set(power);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("amperage intake", IntakeMotor.getOutputCurrent());

    }
}
