package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.Constants; 

public class DriveSubsystem extends SubsystemBase {
  private VictorSP rightMotor;
  private VictorSP leftMotor;
  private DifferentialDrive drive;

  private static DriveSubsystem instance;

  public DriveSubsystem () {
      rightMotor = new VictorSP(Constants.DrivebaseConstants.kRightChannel);
      leftMotor = new VictorSP(Constants.DrivebaseConstants.kLeftChannel);
      drive = new DifferentialDrive(leftMotor, rightMotor);
  }

  public static DriveSubsystem getInstance() {
    return DriveSubsystem.instance;
  }

  public void arcadeDrive(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation);
  }

  @Override
  public void periodic() {
  }

}
