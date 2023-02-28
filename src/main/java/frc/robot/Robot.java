// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
// import frc.robot.subsystems.ShoulderSubsystem;


public class Robot extends TimedRobot {
  
  private Command m_autonomousCommand;

 private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    m_robotContainer.m_drive.resetNavx();
    m_robotContainer.m_drive.motorBrake();
  }

  @Override
  public void robotPeriodic() {
  m_robotContainer.m_ShoulderSubsystemPID.getDistance();
  m_robotContainer.m_wristSubsystem.getWristEncoderDistance();
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    m_robotContainer.m_drive.motorBrake();
    // m_robotContainer.m_shoulderSubsystem.resetEncoder();
    m_robotContainer.m_ShoulderSubsystemPID.resetEncoder();

   
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("NAVX Angle", m_robotContainer.m_drive.getHeading());
  }

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
