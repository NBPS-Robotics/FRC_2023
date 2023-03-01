package frc.robot.subsystems;

import java.lang.annotation.Target;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class shoulderpid extends SubsystemBase {
    
    private final TalonSRX m_leftLead; 
    private final TalonSRX m_leftFollow; 
    private final TalonSRX m_rightFollow2; 
    private final TalonSRX m_rightFollow;
    Encoder encoder;
    double currentShoulderDistance; 
    PIDController m_pidController; 
    double kP = 0.001; 
    double kI = 0.00; 
    double kD = 0.0001; 
    double setpoint; 

  public shoulderpid(){
      TalonSRXConfiguration config = new TalonSRXConfiguration();
      config.peakCurrentLimit = 133;
      config.peakCurrentDuration = 1500;
      config.continuousCurrentLimit = 120;
      
      m_leftLead = new TalonSRX(7);
      m_leftLead.configAllSettings(config);
      m_leftLead.setInverted(false);
     
      m_rightFollow2 =  new TalonSRX(8);
      m_leftFollow =  new TalonSRX(4); 
      m_rightFollow =  new TalonSRX(3);
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
      encoder.reset();
      m_pidController = new PIDController(kP, kI, kD);
      m_pidController.setTolerance(5, 10);
      m_pidController.setSetpoint(0);
  }

  public void getDistance(){
    currentShoulderDistance = encoder.getDistance();
    SmartDashboard.putNumber("ShoulderDistance", currentShoulderDistance);
  }
   
  public void resetEncoder(){
    encoder.reset();
  }

  public void motorZero(){
    m_leftLead.set(TalonSRXControlMode.PercentOutput, 0);
  }

  public void moveArm(double pose) {
    m_pidController.setSetpoint(pose);
    setpoint = pose;
}
   public void calculate(){
    if(setpoint > encoder.getDistance()) //less than?
        m_leftLead.set(TalonSRXControlMode.PercentOutput, MathUtil.clamp(m_pidController.calculate(encoder.getDistance(), setpoint), -0.5, 0.5));
    else
        m_leftLead.set(TalonSRXControlMode.PercentOutput, ((MathUtil.clamp(m_pidController.calculate(encoder.getDistance(), setpoint), -0.5, 0.5)) * 0.5));
    }

   @Override
   public void periodic(){
    calculate();
   }



}
