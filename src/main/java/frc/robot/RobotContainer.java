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
    m_driverController.leftBumper()
      .onTrue(intakeSubsystem.intake());

    m_driverController.rightBumper().
      onTrue(intakeSubsystem.outtake());
    
    // add bindings for da arm
    m_driverController.leftTrigger()
      .onTrue(armSubsystem.up());

    m_driverController.rightTrigger()
      .onTrue(armSubsystem.down());
  }
   public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Commands.sequence(
      Autos.scoreCart(),
      Commands.waitSeconds(Constants.AutoConstants.kCartTeamWaitTime),
      Autos.exitTeamZone()
    );
  }
    
}
