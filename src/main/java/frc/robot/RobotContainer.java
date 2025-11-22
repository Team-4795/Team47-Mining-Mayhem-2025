package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ArmSubsystem armSubsystem = new ArmSubsystem();

  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final CommandXboxController m_operatorController =
    new CommandXboxController(OperatorConstants.kControllerPort);

  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    // Make drive subsystem default bindings
    driveSubsystem.setDefaultCommand(driveSubsystem.runOnce(
      () -> driveSubsystem.arcadeDrive(
        -m_driverController.getLeftY(), 
        m_driverController.getRightX()
      )
    ));

    intakeSubsystem.setDefaultCommand(intakeSubsystem.runOnce(
      () -> intakeSubsystem.spin(m_operatorController.getRightX())
    ));

    armSubsystem.setDefaultCommand(armSubsystem.runOnce(
      () -> armSubsystem.spin(m_operatorController.getLeftX())
    ));
  }
  
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exitTeamZone();
  }
    
}
