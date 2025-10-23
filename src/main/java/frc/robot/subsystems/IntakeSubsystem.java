package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.Constants; 

public class IntakeSubsystem extends SubsystemBase {
  private VictorSP rightMotor;
  private VictorSP leftMotor;

  public IntakeSubsystem () {
      rightMotor = new VictorSP(Constants.IntakeConstants.kRightChannel);
      leftMotor = new VictorSP(Constants.IntakeConstants.kLeftChannel);
  }

  /**
  Returns a command to intake at given speed
  @param speed speed to intake at 
   **/
  public Command spin(double speed) {
    return Commands.parallel(
        // TODO: change which motor is negated based on design 
        this.runOnce(() -> rightMotor.set(-speed)),
        this.runOnce(() -> leftMotor.set(speed))
    );
  }

  /**
   * Intakes the game piece
   * @return a command
   */
  public Command intake() {
    return this.spin(Constants.IntakeConstants.kIntakeSpeed);
  }

  /**
   * Outtakes the game piece
   * @return a command
   */
  public Command outtake() {
    return this.spin(Constants.IntakeConstants.kOuttakeSpeed);
  }
}