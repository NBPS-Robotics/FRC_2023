// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants;

import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  
  //public final static CANSparkMax m_frontLeft = new CANSparkMax(1, MotorType.kBrushless);
  //public final static CANSparkMax m_frontRight = new CANSparkMax(2, MotorType.kBrushless);
  //public final static CANSparkMax m_backLeft = new CANSparkMax(3, MotorType.kBrushless);
  //public final static CANSparkMax m_backRight = new CANSparkMax(4, MotorType.kBrushless);

  //MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeft, m_frontRight, m_backLeft, m_backRight);
  Joystick m_stick = new Joystick(0);

  private RobotContainer m_robotContainer;

  
  private TalonSRX motor1;
  private TalonSRX motor2;
  private TalonSRX motor3;
  private TalonSRX motor4;
  

  @Override
  public void robotInit() {
    // Configure motor
    motor1 = new TalonSRX(6);
    motor2 = new TalonSRX(7);
    motor3 = new TalonSRX(8);
    motor4 = new TalonSRX(9);
    
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = 133; // the peak current, in amps
    config.peakCurrentDuration = 1500; // the time at the peak current before the limit triggers, in ms
    config.continuousCurrentLimit = 120; // the current to maintain if the peak limit is triggered
    motor1.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    motor2.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    motor3.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    motor4.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
    
    //m_frontRight.setInverted(true);
    //m_backRight.setInverted(true);

  }

 
  @Override
  public void robotPeriodic() {
    
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

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
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //m_robotDrive.driveCartesian(-m_stick.getY(), m_stick.getX(), m_stick.getZ());

    motor1.set(TalonSRXControlMode.PercentOutput, m_stick.getY()*0.8); // runs the motor at 80% power of the stick y
    motor2.set(TalonSRXControlMode.PercentOutput, m_stick.getY()*0.8); // runs the motor at 80% power of the stick y
    motor3.set(TalonSRXControlMode.PercentOutput, m_stick.getY()*0.8); // runs the motor at 80% power of the stick y
    motor4.set(TalonSRXControlMode.PercentOutput, m_stick.getY()*0.8); // runs the motor at 80% power of the stick y

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
