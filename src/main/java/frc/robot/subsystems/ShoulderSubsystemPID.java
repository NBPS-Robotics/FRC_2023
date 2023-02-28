package frc.robot.subsystems;

import java.lang.annotation.Target;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShoulderSubsystemPID extends SubsystemBase {
    
    private final TalonSRX m_leftLead; 
    private final TalonSRX m_leftFollow; 
    private final TalonSRX m_rightFollow2; 
    private final TalonSRX m_rightFollow;

    Encoder encoder;

    double currentShoulderDistance; 
    int targetPosition = 8000; 
    private final double maxSpeed = 0.25;
    private final double minSpeed = 0.1;
    private final double kP = 0.1;


  public ShoulderSubsystemPID(){

      TalonSRXConfiguration config = new TalonSRXConfiguration();
      config.peakCurrentLimit = 133;
      config.peakCurrentDuration = 1500;
      config.continuousCurrentLimit = 120;
      
      m_leftLead = new TalonSRX(3);
      m_leftLead.configAllSettings(config);
      m_leftLead.setInverted(false);
     
      m_rightFollow2 =  new TalonSRX(8);
      m_leftFollow =  new TalonSRX(4); 
      m_rightFollow =  new TalonSRX(7);
      m_leftFollow.configAllSettings(config);
      m_rightFollow.configAllSettings(config);
      m_rightFollow2.configAllSettings(config);
      m_leftFollow.follow(m_leftLead);
      m_rightFollow.follow(m_leftLead);
      m_rightFollow2.follow(m_leftLead);
      m_leftFollow.setInverted(InvertType.FollowMaster);
      m_rightFollow.setInverted(true);
      m_rightFollow2.setInverted(true);

      encoder = new Encoder(0, 1, true, Encoder.EncodingType.k2X);
      encoder.setDistancePerPulse(1.0);
      encoder.setSamplesToAverage(10);
      encoder.reset();

      double kP = 0.1;
      double kI = 0.0;
      double kD = 0.0;
      double kF = 0.0;


  }

  public void getDistance(){
    currentShoulderDistance = encoder.getDistance();
    SmartDashboard.putNumber("ShoulderDistance", currentShoulderDistance);
  }

  public void resetEncoder(){
    encoder.reset();
  }

  public void calculate(){
    int error = targetPosition - encoder.get();
    double motorOutput = kP * error;

    if(motorOutput > maxSpeed){
      motorOutput = maxSpeed;
    }
    else if(motorOutput < -maxSpeed){
      motorOutput = -maxSpeed;
    }
    else if(motorOutput > 0 && motorOutput < minSpeed){
      motorOutput = minSpeed; 
    }
    else if(motorOutput < 0 && motorOutput > -minSpeed){
      motorOutput = -minSpeed;
    }
    m_leftLead.set(TalonSRXControlMode.PercentOutput, motorOutput);
  }








}
