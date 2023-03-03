// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoStuffHere;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.clawSubsystem;
import frc.robot.subsystems.shoulderpid;

public final class AutoHolder {
    // TODO: work on drivetrain during auto (ramsete?)
    private final DriveSubsystem drive;
    private final shoulderpid shoulder;
    private final clawSubsystem claw;

    public AutoHolder(DriveSubsystem drive, shoulderpid shoulder,
            clawSubsystem claw) {
        this.drive = drive;
        this.shoulder = shoulder;
        this.claw = claw;
    }

    public SequentialCommandGroup getAuto1() {
        return new SequentialCommandGroup(
                new MoveShoulderButBetter(shoulder, 100),
                new MoveClawButBetter(claw, false));
    }

    public SequentialCommandGroup getAuto2() {
        return new SequentialCommandGroup(
                new MoveShoulderButBetter(shoulder, 500),
                new MoveClawButBetter(claw, false));
    }
    // TODO: Add more auto commands here

}
