package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants; 

public class ArmSubsystem extends SubsystemBase {
  private SparkMax motor;
  private static ArmSubsystem instance;

  public ArmSubsystem() {
    motor = new SparkMax(Constants.ArmConstants.kMotorChannel, MotorType.kBrushless); 
    ArmSubsystem.instance = this;
  }

  public static ArmSubsystem getInstance() {
    return ArmSubsystem.instance;
  }

  public Command spin(double speed) {
    return this.runOnce(() -> motor.set(speed));
  }

  public Command up() {
    return spin(Constants.ArmConstants.kUpSpeed);
  }
  public Command down() {
    return spin(Constants.ArmConstants.kDownSpeed);
  }

  public Command stop() {
    return spin(0);
  }
  
}
