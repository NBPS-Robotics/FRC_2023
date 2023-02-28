package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class WristSubsystem extends SubsystemBase {
    
  private final TalonSRX talon; 
  double wristEncoderDistance; 

  public WristSubsystem(){
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = 133;
    config.peakCurrentDuration = 1500;
    config.continuousCurrentLimit = 120;
     
    talon =  new TalonSRX(12);
    talon.set(TalonSRXControlMode.Position, 0);
    talon.getSelectedSensorPosition();
  }

  public double getWristEncoderDistance(){
    wristEncoderDistance = talon.getSelectedSensorPosition();
    SmartDashboard.putNumber("WristDistance", wristEncoderDistance);
    return wristEncoderDistance; 
  }



 

}
