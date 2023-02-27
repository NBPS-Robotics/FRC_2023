package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class StopperSubsystem extends SubsystemBase{

  //DoubleSolenoid stopper = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  DoubleSolenoid stopper;
 
  public StopperSubsystem(){
    stopper = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0,1);
    // stopper.set(Value.kOff);
  }

  public void deployStop(){
    stopper.set(Value.kForward);
    // stopper.set(true); 
  }

  public void retractStop(){
    stopper.set(Value.kReverse);
    // stopper.set(false); 
  }

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
