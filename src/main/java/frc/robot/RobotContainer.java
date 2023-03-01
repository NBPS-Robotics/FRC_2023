// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
import frc.robot.commands.setMotorCompact;
import frc.robot.commands.setMotorMiddlePID;
import frc.robot.commands.setMotorRetrieval;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.StopperSubsystem;
import frc.robot.subsystems.TrajectorySubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.shoulderpid;

 
public class RobotContainer {
  
  CommandXboxController m_driverController= new CommandXboxController(0); 

  Joystick m_joystick1 = new Joystick(0);
  Joystick m_joystick2 = new Joystick(0);
  
  //Subsystems
  final StopperSubsystem m_stopperSubsystem = new StopperSubsystem();
  final DriveSubsystem m_drive = new DriveSubsystem();
  // final ShoulderSubsystemPID m_ShoulderSubsystemPID = new ShoulderSubsystemPID(); 
  final WristSubsystem m_wristSubsystem = new WristSubsystem();
  final shoulderpid m_shoulderpid = new shoulderpid();

  //Triggers
  Trigger aButton = m_driverController.a();
  Trigger bButton = m_driverController.b();
  Trigger xButton = m_driverController.x();
  Trigger yButton = m_driverController.y();
  Trigger leftBumper = m_driverController.leftBumper(); 
  Trigger rightBumper = m_driverController.rightBumper(); 

  // //Commands
  private final ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drive, () -> m_joystick2.rightY(),
   () -> m_joystick2.rightX(),
    () -> m_joystick2.leftX());

  final DeployStopperCommand m_deployStopperCommand = new DeployStopperCommand(m_stopperSubsystem);
  final RetractStopperCommand m_retractStopperCommand = new RetractStopperCommand(m_stopperSubsystem);
  final setMotorMiddlePID m_SetMotorMiddlePID = new setMotorMiddlePID(m_shoulderpid); 
  final setMotorCompact m_resetEncoder = new setMotorCompact(m_shoulderpid); 
  final setMotorRetrieval m_setMotoreRetrieval = new setMotorRetrieval(m_shoulderpid); 
  
  MecanumControllerCommand mecanumControllerCommand = new MecanumControllerCommand(
    TrajectorySubsystem.exampleTrajectory,
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
  }

  private void configureBindings() { 
    aButton.onTrue(m_setMotoreRetrieval);
    bButton.onTrue(m_retractStopperCommand);
    xButton.onTrue(m_SetMotorMiddlePID);
    yButton.onTrue(m_resetEncoder);
    leftBumper.onTrue(m_deployStopperCommand);
    rightBumper.onTrue(m_retractStopperCommand);
  }

  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
