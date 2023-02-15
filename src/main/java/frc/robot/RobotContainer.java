// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.LiftDownCommand; 
import frc.robot.commands.LiftUpCommand;
// import frc.robot.commands.Autos;
import frc.robot.subsystems.LiftSubsystem;

public class RobotContainer {
  
  //subsystems
  private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
  private final LiftUpCommand m_liftUpCommand = new LiftUpCommand(m_liftSubsystem);
  private final LiftDownCommand m_liftDownCommand = new LiftDownCommand(m_liftSubsystem);
  private final XboxController m_joystick = new XboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

 


  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new JoystickButton(m_joystick, Button.kRightBumper.value)
      .onTrue(m_liftUpCommand);
  }

 
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   // return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
