// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveConstants;
import frc.robot.commands.ArcadeDrive;
// import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DeployStopperCommand;
import frc.robot.commands.RetractStopperCommand;
import frc.robot.subsystems.DriveSubsystem;
// import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.StopperSubsystem;
import frc.robot.subsystems.TrajectorySubsystem;

 


public class RobotContainer {
  
  final XboxController m_driverController = new XboxController(0);
  Joystick m_joystick1 = new Joystick(1);
  Joystick m_joystick2 = new Joystick(1);

  //Subsystems
  final StopperSubsystem m_stopperSubsystem = new StopperSubsystem();
  final DriveSubsystem m_drive = new DriveSubsystem();
  
  // //Commands
  private final ArcadeDrive m_ArcadeDrive = new ArcadeDrive(m_drive, () -> m_joystick2.rightY(),
   () -> m_joystick2.rightX(),
    () -> m_joystick2.leftX());
  private final DeployStopperCommand m_deployStopperCommand = new DeployStopperCommand(m_stopperSubsystem);
  private final RetractStopperCommand m_retractStopperCommand = new RetractStopperCommand(m_stopperSubsystem);
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
    new JoystickButton(m_driverController, Button.kA.value)
      .onTrue(m_deployStopperCommand);

    new JoystickButton(m_driverController, Button.kB.value)
      .onTrue(m_retractStopperCommand);
  }

 
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
