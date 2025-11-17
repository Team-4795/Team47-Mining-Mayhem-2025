// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

import static edu.wpi.first.units.Units.Second;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
  public static Command scoreCart() {
    return Commands.sequence(
      IntakeSubsystem.getInstance().intake(),
      Commands.waitSeconds(0.1),
      ArmSubsystem.getInstance().down(),
      Commands.waitSeconds(0.1),
      IntakeSubsystem.getInstance().outtake(),
      Commands.waitSeconds(0.1),
      ArmSubsystem.getInstance().up()
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