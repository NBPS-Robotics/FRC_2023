package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LiftSubsystem extends SubsystemBase {
    
    Joystick m_stick = new Joystick(0);


    private final TalonSRX m_leftLead; 
    private final TalonSRX m_leftFollow; 
    private final TalonSRX m_rightLead; 
    private final TalonSRX m_rightFollow; 



  public LiftSubsystem() {
        TalonSRXConfiguration config = new TalonSRXConfiguration();
        config.peakCurrentLimit = 133;
        config.peakCurrentDuration = 1500;
        config.continuousCurrentLimit = 120;


        m_leftLead = new TalonSRX(6);
        m_rightLead =  new TalonSRX(8);
        m_leftLead.configAllSettings(config);
        m_rightLead.configAllSettings(config);

        m_leftLead.setInverted(false);
        m_rightLead.setInverted(true);


        m_leftFollow =  new TalonSRX(7); 
        m_rightFollow =  new TalonSRX(9);
        m_leftFollow.configAllSettings(config);
        m_rightFollow.configAllSettings(config);

        m_leftFollow.follow(m_leftLead);
        m_rightFollow.follow(m_rightLead);

        m_leftFollow.setInverted(InvertType.FollowMaster);
        m_rightFollow.setInverted(InvertType.FollowMaster);
  }

  public void liftUp(){
    m_leftLead.set(TalonSRXControlMode.PercentOutput, m_stick.getY() * .5);
    m_rightLead.set(TalonSRXControlMode.PercentOutput, m_stick.getY() * .5);

  }

  public void liftDown(){
    m_leftLead.setInverted(true);
    m_rightLead.setInverted(false);
    m_leftLead.set(TalonSRXControlMode.PercentOutput, m_stick.getX() * .5);
    m_rightLead.set(TalonSRXControlMode.PercentOutput,m_stick.getX() * .5);

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