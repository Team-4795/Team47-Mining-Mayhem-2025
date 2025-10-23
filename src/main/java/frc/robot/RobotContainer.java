package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Make drive subsystem default bindings
    driveSubsystem.setDefaultCommand(driveSubsystem.runOnce(
      () -> driveSubsystem.arcadeDrive(
        m_driverController.getLeftX(), 
        m_driverController.getRightX()
      )
    ));

    // Add bindings to intake and outtake
    m_driverController.leftBumper().whileTrue(intakeSubsystem.intake());
    m_driverController.rightBumper().whileTrue(intakeSubsystem.outtake());
  }
}
