package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ShoulderSubsystem extends SubsystemBase {
    
    // Joystick m_stick = new Joystick(0);
    private final XboxController m_driverController = new XboxController(0);

    private final TalonSRX m_leftLead; 
    private final TalonSRX m_leftFollow; 
    private final TalonSRX m_rightFollow2; 
    private final TalonSRX m_rightFollow; 
    Encoder encoder; 
    double shoulderDistance;
 

  public ShoulderSubsystem(){
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = 133;
        config.peakCurrentDuration = 1500;
        config.continuousCurrentLimit = 120;
        m_leftLead = new TalonSRX(3);
        m_leftLead.configAllSettings(config);
        m_leftLead.setInverted(false);
        m_rightFollow2 =  new TalonSRX(8);
        m_leftFollow =  new TalonSRX(7); 
        m_rightFollow =  new TalonSRX(4);
        m_leftFollow.configAllSettings(config);
        m_rightFollow.configAllSettings(config);
        m_rightFollow2.configAllSettings(config);
        m_leftFollow.follow(m_leftLead);
        m_rightFollow.follow(m_leftLead);
        m_rightFollow2.follow(m_leftLead);
        m_leftFollow.setInverted(InvertType.FollowMaster);
        m_rightFollow.setInverted(InvertType.FollowMaster);
        m_rightFollow2.setInverted(InvertType.FollowMaster);

        encoder = new Encoder(0, 1, false, Encoder.EncodingType.k2X);
        getDistance();
        SmartDashboard.putNumber("Shoulder Distance", shoulderDistance);
  }
  
  public double getDistance(){
    shoulderDistance = encoder.getDistance();
    return shoulderDistance; 
  }
 
 
  



  public CommandBase liftCommand() {
    
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}