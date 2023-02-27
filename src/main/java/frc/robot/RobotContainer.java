// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;

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
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.StopperSubsystem;
import frc.robot.subsystems.TrajectorySubsystem;

 


public class RobotContainer {
  
  CommandXboxController m_driverController= new CommandXboxController(0); 

  Joystick m_joystick1 = new Joystick(0);
  Joystick m_joystick2 = new Joystick(0);
  
  //Subsystems
  final StopperSubsystem m_stopperSubsystem = new StopperSubsystem();
  final DriveSubsystem m_drive = new DriveSubsystem();
  //Triggers
  Trigger aButton = m_driverController.a();
  Trigger bButton = m_driverController.b();
  

  // //Commands
  private final ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drive, () -> m_joystick2.rightY(),
   () -> m_joystick2.rightX(),
    () -> m_joystick2.leftX());

  final DeployStopperCommand m_deployStopperCommand = new DeployStopperCommand(m_stopperSubsystem);
  final RetractStopperCommand m_retractStopperCommand = new RetractStopperCommand(m_stopperSubsystem);
  
  MecanumControllerCommand mecanumControllerCommand = new MecanumControllerCommand(
    TrajectorySubsystem.exampleTrajectory,
    m_drive::getPose,
    DriveConstants.kFeedforward,
    DriveConstants.kDriveKinematics,
    // Position contollers
    DriveConstants.xController,
    DriveConstants.yController,
    DriveConstants.thetaController,
    // Needed for normalizing wheel speeds
    DriveConstants.kMaxSpeedMetersPerSecond,
    // Velocity PID's
    DriveConstants.driveController,
    DriveConstants.driveController,
    DriveConstants.driveController,
    DriveConstants.driveController,
    m_drive::getCurrentWheelSpeeds,
    m_drive::setDriveMotorControllersVolts, // Consumer for the output motor voltages
    m_drive);
    
    private SendableChooser<Command> driveChooser = new SendableChooser<Command>();
 
    /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveChooser.setDefaultOption("Arcade Drive", m_ArcadeDrive);
    SmartDashboard.putData("Drive Mode", driveChooser);
    configureBindings();
    m_drive.setDefaultCommand(driveChooser.getSelected());
    SmartDashboard.putNumber("NAVX Angle", m_drive.getHeading());
  }

 
  private void configureBindings() { 
    aButton.onTrue(m_deployStopperCommand);
    bButton.onTrue(m_retractStopperCommand);
  }

 
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
