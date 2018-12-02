/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Servo;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private XboxController XBONE;
  private Relay blinkyrelay;
  private Integer a;
  private Boolean b;
  private Boolean alreadyprinted;
  private Integer currentstate;
  private Servo steeringservo;
  private Servo drivemotor;
  private Talon cim1;
  private Talon cim2;
  private RobotDrive tankz;


  @Override
  public void robotInit() {
    //m_myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
    m_leftStick = new Joystick(0);
    //m_Button = new JoystickButton(joystick, buttonNumber)
    m_rightStick = new Joystick(1);


    // initializing tank drive & motor drives
    cim1 = new Talon(0);
    cim2 = new Talon(1);
    tankz = new RobotDrive(cim1, cim2);


    
    XBONE= new XboxController(0);

    

  }

  @Override
  public void teleopPeriodic() {


    tankz.arcadeDrive(m_rightStick);

    Double leftmotorspd=cim1.getSpeed();
    Double rightmotorspd=cim2.getSpeed();
    String bothspeeds= leftmotorspd.toString()+',' +rightmotorspd.toString();
    System.out.println(bothspeeds);

   

  }

  @Override
  public void disabledInit()
  {
    // set steering back to center
    steeringservo.set(.5);

    // set motor to disabled
    drivemotor.set(0.5);
  }
}
