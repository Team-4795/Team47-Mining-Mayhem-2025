// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  public static Command scoreCart() {
    return Commands.sequence(
      IntakeSubsystem.getInstance().outtake()
        .andThen(Commands.waitSeconds(Constants.IntakeConstants.kIntakeWaitTime))
        .andThen(IntakeSubsystem.getInstance().stop()),
      Commands.waitSeconds(0.1),
      ArmSubsystem.getInstance().up()
        .andThen(Commands.waitSeconds(Constants.ArmConstants.kUpWaitTime))
        .andThen(ArmSubsystem.getInstance().stop())
    );
  }

  public static Command exitTeamZone() {
    return Commands.sequence(
      Commands.run(() -> DriveSubsystem.getInstance().arcadeDrive(-0.5, 0.25)),
      Commands.waitSeconds(Constants.AutoConstants.kTurnWaitTime),
      Commands.runOnce(() -> DriveSubsystem.getInstance().arcadeDrive(-0.5, 0)),
      Commands.waitSeconds(Constants.AutoConstants.kReverseWaitTime),
      Commands.runOnce(() -> DriveSubsystem.getInstance().arcadeDrive(0, 0))
    );
  }


   public static Command grabBall(DriveSubsystem drive) {
    return Commands.sequence(
       Commands.run(() -> drive.arcadeDrive(1, 0)),
       ArmSubsystem.getInstance().down(),
       IntakeSubsystem.getInstance().intake(),
       ArmSubsystem.getInstance().up(),
       Commands.run(() -> drive.arcadeDrive(-0.5, 0)),
       Commands.waitSeconds(0.5),
       Commands.run(() -> drive.arcadeDrive(  0.3, -0.5)),
       Commands.run(() -> drive.arcadeDrive(0.5, 0)),
      ArmSubsystem.getInstance().up(),
      IntakeSubsystem.getInstance().outtake()
    );
   }
}