/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotState;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private Relay blinkyrelay;
  private Integer a;
  private Boolean b;

  @Override
  public void robotInit() {
    //m_myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
    m_leftStick = new Joystick(0);
    //m_Button = new JoystickButton(joystick, buttonNumber)
    m_rightStick = new Joystick(1);
    blinkyrelay = new Relay(0);
    a = new Integer(0);
    b = false;
    //blinkyrelay.set(Value.kReverse);
  }

  @Override
  public void teleopPeriodic() {
   // m_myRobot.tankDrive(m_leftStick.getY(), m_rightStick.getY());
   double stickval = m_leftStick.getY(); 
   

   if(Math.abs(stickval) > 0.50 && !b)
   {
     b = true;
     System.out.println("RELAY ON!!!");
     blinkyrelay.set(Value.kReverse);
   }
   else if (Math.abs(stickval) <= .50 && b)
   {
     b = false;
     System.out.println("RELAY OFF!!!");
    blinkyrelay.set(Value.kOff);
   }
   else
   {
     //System.out.println("Doing Nothing!");
   }
  }
}
