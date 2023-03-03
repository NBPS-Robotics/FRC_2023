package frc.robot.commands.autoStuffHere;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.clawSubsystem;

public class MoveClawButBetter extends CommandBase {
    private final clawSubsystem claw;
    private final boolean open;
    private final double TIME = 1.2;
    private final double startTime;

    public MoveClawButBetter(clawSubsystem claw, boolean open) {
        this.claw = claw;
        this.open = open;
        startTime = Timer.getFPGATimestamp();
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        if (open) {
            claw.openClaw();
        } else {
            claw.closeClaw();
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            claw.noPowerClaw();
        } else {
            if (open) {
                claw.closeClaw();
            } else {
                claw.openClaw();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() > startTime + TIME;
    }
}