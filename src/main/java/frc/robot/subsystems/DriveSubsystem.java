package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveSubsystem extends SubsystemBase {
   
    public static CANSparkMax m_frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    public static CANSparkMax m_frontRightMotor = new CANSparkMax(2, MotorType.kBrushless);
    public static CANSparkMax m_backLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
    public static CANSparkMax m_backRightMotor = new CANSparkMax(4, MotorType.kBrushless);
    
    MecanumDrive m_robotDrive = new MecanumDrive(m_frontLeftMotor, m_frontRightMotor, m_backLeftMotor, m_backRightMotor);
    


    public DriveSubsystem(){

        m_frontLeftMotor.set(-0.75); //% output -1 -> 1
        m_frontRightMotor.set(-0.75); 
        m_backLeftMotor.set(-0.75); 
        m_backRightMotor.set(-0.75);
        
        m_frontRightMotor.setInverted(true);
        m_backRightMotor.setInverted(true);

    }

    public void drive(double x, double y, double z){

        m_robotDrive.driveCartesian(-y, x, z);

    }




}
