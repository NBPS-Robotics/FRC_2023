// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DeployStopperCommand;
import frc.robot.commands.RetractStopperCommand;
import frc.robot.commands.closeClaw;
import frc.robot.commands.openClaw;
import frc.robot.commands.setMotorCompact;
import frc.robot.commands.setMotorHigh;
import frc.robot.commands.setMotorMiddle;
import frc.robot.commands.setMotorRetrieval;
import frc.robot.commands.speedButton;
import frc.robot.commands.autoStuffHere.AutoHolder;
import frc.robot.commands.autoStuffHere.Trajectories;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.StopperSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.clawSubsystem;
import frc.robot.subsystems.shoulderpid;

public class RobotContainer {

  CommandXboxController m_driverController = new CommandXboxController(0);
  CommandXboxController m_coDriverController = new CommandXboxController(1);

  Joystick m_joystick1 = new Joystick(0);
  Joystick m_joystick2 = new Joystick(0);

  // Subsystems
  final StopperSubsystem m_stopperSubsystem = new StopperSubsystem();
  final DriveSubsystem m_drive = new DriveSubsystem();
  final WristSubsystem m_wristSubsystem = new WristSubsystem();
  final shoulderpid m_shoulderpid = new shoulderpid();
  final clawSubsystem m_clawSubsystem = new clawSubsystem();
  final SendableChooser<Command> autoChooser = new SendableChooser<>();

  // Triggers
  Trigger aButton = m_coDriverController.a();
  Trigger bButton = m_coDriverController.b();
  Trigger xButton = m_coDriverController.x();
  Trigger yButton = m_coDriverController.y();
  Trigger rightBumperCo = m_coDriverController.rightBumper();
  Trigger leftBumperCo = m_coDriverController.leftBumper();

  Trigger dPadDown = m_driverController.povDown();
  Trigger dPadUp = m_driverController.povUp();
  Trigger rightBumper = m_driverController.rightBumper();
  Trigger leftBumper = m_driverController.leftBumper();

  // //Commands
  private final ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drive, () -> m_joystick2.leftY() * 0.75,
      () -> m_joystick2.leftX() * 0.75,
      () -> m_joystick2.rightX() * 0.75);

  final DeployStopperCommand m_deployStopperCommand = new DeployStopperCommand(m_stopperSubsystem);
  final RetractStopperCommand m_retractStopperCommand = new RetractStopperCommand(m_stopperSubsystem);
  final setMotorMiddle m_SetMotorMiddle = new setMotorMiddle(m_shoulderpid, m_wristSubsystem);
  final setMotorHigh m_SetMotorHigh = new setMotorHigh(m_shoulderpid, m_wristSubsystem);
  final setMotorCompact m_motorCompact = new setMotorCompact(m_shoulderpid, m_wristSubsystem);
  final setMotorRetrieval m_setMotoreRetrieval = new setMotorRetrieval(m_shoulderpid, m_wristSubsystem);
  final speedButton m_SpeedButtonBoost = new speedButton(1.0);
  final speedButton m_SpeedButtonNoBoost = new speedButton(0.3);
  final openClaw m_openClaw = new openClaw(m_clawSubsystem);
  final closeClaw m_closeClaw = new closeClaw(m_clawSubsystem);

  MecanumControllerCommand mecanumControllerCommand = new MecanumControllerCommand(
      Trajectories.exampleTrajectory,
      m_drive::getPose,
      DriveConstants.kFeedforward,
      DriveConstants.kDriveKinematics,
      DriveConstants.xController,
      DriveConstants.yController,
      DriveConstants.thetaController,
      DriveConstants.kMaxSpeedMetersPerSecond,
      DriveConstants.driveController,
      DriveConstants.driveController,
      DriveConstants.driveController,
      DriveConstants.driveController,
      m_drive::getCurrentWheelSpeeds,
      m_drive::setDriveMotorControllersVolts,
      m_drive);

  private SendableChooser<Command> driveChooser = new SendableChooser<Command>();

  public RobotContainer() {
    driveChooser.setDefaultOption("Arcade Drive", m_ArcadeDrive);
    SmartDashboard.putData("Drive Mode", driveChooser);
    configureBindings();
    m_drive.setDefaultCommand(driveChooser.getSelected());
    SmartDashboard.putNumber("NAVX Angle", m_drive.getHeading());
    setAutoCommands();
  }

  private void configureBindings() {
    aButton.onTrue(m_motorCompact);
    xButton.onTrue(m_setMotoreRetrieval);
    yButton.onTrue(m_SetMotorMiddle);
    bButton.onTrue(m_SetMotorHigh);

    leftBumperCo.onTrue(m_closeClaw);
    rightBumperCo.onTrue(m_openClaw);

    dPadUp.onTrue(m_deployStopperCommand);
    dPadDown.onTrue(m_retractStopperCommand);
    rightBumper.whileTrue(m_SpeedButtonBoost);
    leftBumper.whileTrue(m_SpeedButtonNoBoost);

  }

  private void setAutoCommands() {
    AutoHolder autos = new AutoHolder(m_drive, m_shoulderpid, m_clawSubsystem);
    autoChooser.addOption("auto1", autos.getAuto1());
    autoChooser.addOption("auto2", autos.getAuto2());
    SmartDashboard.putData(autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}
