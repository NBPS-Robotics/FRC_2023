package frc.robot.subsystems;

import frc.robot.Constants;

import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class DriveSubsystem extends SubsystemBase {
    
   
    public static CANSparkMax m_frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    public static CANSparkMax m_frontRightMotor = new CANSparkMax(2, MotorType.kBrushless);
    public static CANSparkMax m_backLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
    public static CANSparkMax m_backRightMotor = new CANSparkMax(4, MotorType.kBrushless);
    


    public DriveSubsystem(){

        m_frontLeftMotor.set(-0.75); //% output -1 -> 1
        m_frontRightMotor.set(-0.75); 
        m_backLeftMotor.set(-0.75); 
        m_backRightMotor.set(-0.75); 

    }

    public void drive(double x, double y){


    }




}
