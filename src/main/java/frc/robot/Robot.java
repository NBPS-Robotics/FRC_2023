// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.StopperSubsystem;


public class Robot extends TimedRobot {
  
  private Command m_autonomousCommand;
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  //private final StopperSubsystem m_stopperSub = new StopperSubsystem();

 private RobotContainer m_robotContainer;
  

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_robotContainer.m_drive.resetNavx();
    m_robotContainer.m_drive.motorBrake();
  }

  @Override
  public void robotPeriodic() {
    
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    // m_robotContainer.m_drive.motorCoast();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
   // m_stopperSub.deployStop();
   // m_stopperSub.retractStop();
    m_robotContainer.m_drive.motorBrake();
   
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("NAVX Angle", m_robotContainer.m_drive.getHeading());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {


  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
