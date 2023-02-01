// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.JoystickConstants;
// import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class RobotContainer {
  
  //subsystems
  private final DriveSubsystem m_DriveSubsystem = new DriveSubsystem();


  Joystick m_joystick1 = new Joystick(JoystickConstants.kJoystick1Port);
  Joystick m_joystick2 = new Joystick(JoystickConstants.kJoystick2Port);


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

 


  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
  }

 
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
