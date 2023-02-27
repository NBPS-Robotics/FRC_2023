package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTestSub extends SubsystemBase {

 
    public static CANSparkMax m_frontLeftMotor= new CANSparkMax(1, MotorType.kBrushless);
    public static CANSparkMax m_frontRightMotor = new CANSparkMax(9, MotorType.kBrushless);
    public static CANSparkMax m_backLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    public static CANSparkMax m_backRightMotor = new CANSparkMax(10, MotorType.kBrushless);

    Joystick m_leftJoystick = new Joystick(0); 
    Joystick m_rightJoystick = new Joystick(1); 

    MecanumDrive mecanumDrive  =  new MecanumDrive(m_frontLeftMotor, m_backLeftMotor, m_frontRightMotor, m_backRightMotor);

    // public void DriveTestSub(){
    //     m_frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    //     m_frontRightMotor = new CANSparkMax(9, MotorType.kBrushless);
    //     m_backLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    //     m_backRightMotor = new CANSparkMax(10, MotorType.kBrushless);
       
    //     mecanumDrive =  new MecanumDrive(m_frontLeftMotor, m_backLeftMotor, m_frontRightMotor, m_backRightMotor);
    // }
        

    public void arcadeDrive(){
        double speed = m_leftJoystick.getRawAxis(1); 
        double zRotation = -m_rightJoystick.getRawAxis(0); 
        double rotation = -m_leftJoystick.getRawAxis(4); 
    
        mecanumDrive.driveCartesian(speed * 0.5, rotation * 0.5, zRotation * 0.5); // Drive the robot using mecanum drive
    }





}
