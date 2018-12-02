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
    blinkyrelay = new Relay(0);

    // initializing tank drive & motor drives
    cim1 = new Talon(0);
    cim2 = new Talon(1);
    tankz = new RobotDrive(cim1, cim2);


    a = new Integer(0);
    b = false;
    steeringservo=new Servo(0);
    drivemotor = new Servo(1);
    XBONE= new XboxController(0);
    for (int i=-100;i<100;i++)
    {
      drivemotor.set(i/100);
      for (int j=0;j<1000;j++)
      {}
    }
    alreadyprinted = false;
    currentstate = 0;
    
    //blinkyrelay.set(Value.kReverse);
  }

  @Override
  public void teleopPeriodic() {
   // m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
   double xstickval = m_leftStick.getX(); 
   steeringservo.set(.5-(xstickval)/4);

   double rightthrottlevel = XBONE.getTriggerAxis(Hand.kRight);
   double leftthrottlevel = XBONE.getTriggerAxis(Hand.kLeft);

   leftthrottlevel = Math.abs(leftthrottlevel);
   rightthrottlevel = Math.abs(rightthrottlevel);

  //  String out = "";

  //  out += "Right = " + rightthrottlevel;
  //  out += " Left = " + leftthrottlevel;

  //  System.out.println(out);

   //System.out.println(rawthrottleval);
   if (Math.abs(rightthrottlevel) < .1 && Math.abs(leftthrottlevel) < .1)
   {
     if(currentstate != 1)
     {
      System.out.println("DON'T MOVE");
     }
     currentstate = 1;
     // do nothing
    
    drivemotor.set(0.5);
   }
   else if (Math.abs(rightthrottlevel) >= .1 && Math.abs(leftthrottlevel) >= .1)
   {
    if(currentstate != 2)
    {
     System.out.println("STOP");
    }
    currentstate = 2;
     // do nothing
    drivemotor.set(0.5);
   }
   else if(Math.abs(rightthrottlevel) >= .1 && Math.abs(leftthrottlevel) < .1)
   {
     // drive forward
     if(currentstate != 3)
     {
      System.out.println("FORWARDS");
     }
     drivemotor.set(.5 + (Math.abs(rightthrottlevel)/2) );
     currentstate = 3;
   }
   else if(Math.abs(leftthrottlevel) >= .1 && Math.abs(rightthrottlevel) < .1)
   {
    if(currentstate != 4)
    {
     System.out.println("BACKWARDS");
    }
     // drive backwards
     currentstate = 4;
     //drivemotor.set(.5 - (Math.abs(leftthrottlevel)) );
   }
   else
   {
     // if you got here... you're an idiot
     System.out.println("You're an idiot");
     drivemotor.set(0.5);

   }
   
  //  if(Math.abs(stickval) > 0.50 && !b)
  //  {
  //    b = true;
  //    System.out.println("RELAY ON!!!");
  //    blinkyrelay.set(Value.kReverse);
  //  }
  //  else if (Math.abs(stickval) <= .50 && b)
  //  {
  //    b = false;
  //    System.out.println("RELAY OFF!!!");
  //   blinkyrelay.set(Value.kOff);
  //  }
  //  else
  //  {
  //    //System.out.println("Doing Nothing!");
  //  }
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
