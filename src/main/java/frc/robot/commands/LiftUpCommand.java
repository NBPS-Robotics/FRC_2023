package frc.robot.commands;

import frc.robot.subsystems.LiftSubsystem;


import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class LiftUpCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LiftSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public LiftUpCommand(LiftSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    m_subsystem.liftUp();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}