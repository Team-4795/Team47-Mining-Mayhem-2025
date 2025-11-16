package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import frc.robot.Constants; 

public class IntakeSubsystem extends SubsystemBase {
  
  private SparkMax motor;

  private static IntakeSubsystem instance;

  public IntakeSubsystem () {
      motor = new SparkMax(Constants.IntakeConstants.kMotorChannel, MotorType.kBrushless);
      IntakeSubsystem.instance = this;
  }

  public static IntakeSubsystem getInstance() {
    return instance;
  }

  /**
  Returns a command to intake at given speed
  @param speed speed to intake at 
   **/
  public Command spin(double speed) {
    return Commands.parallel(
        // TODO: change which motor is negated based on design 
        // this.runOnce(() -> rightMotor.set(-speed)),
        this.runOnce(() -> motor.set(speed))
    );
  }

  /**
   * Intakes the game piece
   * @return a command
   */
  public Command intake() {
    return this.spin(Constants.IntakeConstants.kIntakeSpeed)
      .andThen(Commands.waitSeconds(Constants.IntakeConstants.kIntakeWaitTime))
      .andThen(this.stop());
  }

  /**
   * Outtakes the game piece
   * @return a command
   */
  public Command outtake() {
    return this.spin(Constants.IntakeConstants.kOuttakeSpeed)
      .andThen(Commands.waitSeconds(Constants.IntakeConstants.kOuttakeWaitTime))
      .andThen(this.stop());
  }
  /**
   * Stops the intake motors 
   * @return a command
   */
  public Command stop() {
    return this.spin(0);
  }
}