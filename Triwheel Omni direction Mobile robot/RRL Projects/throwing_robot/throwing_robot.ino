#include <HardwareSerial.h>
#include <SoftwareSerial.h>
#include <ODriveArduino.h>
#include <usbhid.h>
#include <hiduniversal.h>
#include <usbhub.h>
#include <SPI.h>
#include "hidjoystickrptparser.h"

USB Usb;
USBHub Hub(&Usb);
HIDUniversal Hid(&Usb);
JoystickEvents JoyEvents;
JoystickReportParser Joy(&JoyEvents);

float x, y, theta_1, theta_2, p1, p2, Z1, Z2, V1, V2, V3, V4, X1, Y1, X2, Y2, pie = 3.1415, A, c, V_1L, V_2L, V_3L, V_4L, V_1R, V_2R, V_3R, V_4R, T;
int counter_2 = 0, counter_3 = 0,counter_1 = -1, counter  = -1, counter_4 = 0, flag_4 = 0, motor1 = 0 , motor2 = 0, gat = 0, mat = 0, flag = 0, flag_1 = 0 , flag_2 = 0, sat = 0;
float m1 = 14, m2 =14;

template<class T> inline Print& operator <<(Print &obj,     T arg) {
  obj.print(arg);
  return obj;
}
template<>        inline Print& operator <<(Print &obj, float arg) {
  obj.print(arg, 4);
  return obj;
}
HardwareSerial& odrive_serial = Serial1 ;  //SoftwareSerial odrive_serial(9, 8);
ODriveArduino odrive(odrive_serial);

#include <SoftwareSerial.h>  //Sabertooth//
#include <Sabertooth.h>
SoftwareSerial SW_1Serial(NOT_A_PIN, 10);
SoftwareSerial SW_2Serial(NOT_A_PIN, 11);
Sabertooth ST_1(128, SW_1Serial);
Sabertooth ST_2(128, SW_2Serial);
#include <Wire.h>  //PID//
#include <MPU6050.h>
MPU6050 mpu;
unsigned long timer = 0;
float timeStep = 0.1;
float yaw;
float kp = 0.9 ;// 0.41 0.4   0.2 //0.50 //last time = 0.9
float ki = 0.0;  // 0
float kd = 5;  //  2.41 0.08, 0.1 //last time = 2.5
float setpoint = 0;
float previous_error;
float error;
float PID_p, PID_i, PID_d, PID_total;

void setup() {
  pinMode(2, OUTPUT);
  pinMode(6, OUTPUT);
  pinMode(4, OUTPUT);

  odrive_serial.begin(115200);
  Serial.begin(115200);

  SW_1Serial.begin(115200);
  SW_2Serial.begin(115200);

  ST_1.autobaud();
  ST_2.autobaud();

#if !defined(MIPSEL)
  while (!Serial);
#endif
  Serial.println("Start");

  while (Usb.Init() == -1)
    Serial.println("OSC did not start.");
  delay(200);

  if (!Hid.SetReportParser(0, &Joy))
    ErrorMessage<uint8_t > (PSTR("SetReportParser"), 1);

  while (!mpu.begin(MPU6050_SCALE_2000DPS, MPU6050_RANGE_2G)) {
    Serial.println("Could not find a valid MPU6050 sensor, check wiring!");
    ST_1.motor(1, 0);
    ST_1.motor(2, 0); 
    ST_2.motor(1, 0); 
    ST_2.motor(2, 0);
  }
  mpu.calibrateGyro();
  mpu.setThreshold(1);
}

void loop() {
  Usb.Task();

  float Buttons = JoyEvents.bu;
  float lxa =  JoyEvents.lx;        
  float lya = JoyEvents.ly;        
  float rxa = JoyEvents.rx;         
  float rya = JoyEvents.ry;          
  float blue = Joy.blue;
  float green = Joy.green;
  float red = Joy.red;
  float yellow = Joy.yellow;
  float L1 = Joy.lb;
  float R1 = Joy.rb;
  float gpad = JoyEvents.ht;
  float L2 = Joy.lt;
  float R2 = Joy.rt;
  float back = Joy.bk;
  float start = Joy.st;
  float leftjoy = Joy.jl;
  float rightjoy = Joy.jr;
  lxa = 128 - lxa;
  lya = 127 - lya;
  rxa = 128- rxa;
  rya = 127 - rya;

  if (( rxa > - 35 && rxa < 35 ) && ( rya > -35 && rya < 35) )   //Bandwidth0//
  {
    rxa  = 0;
    rya = 0;
  }
  if (( lxa > -35 && lxa < 35 ) && ( lya > -35 && lya < 35) )
  {
    lxa  = 0;
    lya = 0;
  }

  
  if (lya >= 35 && lya <= 130) {    //After bandwidth mapping//
    lya = map(lya, 35, 130, 1, 130);
  }
  if (lya <= -35 && lya >= -130) {
    lya = map(lya, -35, -130, -1, -130);
  }
  if (lxa >= 35 && lxa <= 130) {
    lxa = map(lxa, 35, 130, 1, 130);
  }
  if (lxa <= -35 && lxa >= -130) {
    lxa = map(lxa, -35, -130, -1, -130);
  }
 
  if (rya >= 35 && rya <= 130) {  //  for right throttle
    rya = map(rya, 35, 130, 1, 130);
  }
  if (rya <= -35 && rya >= -130) {
    rya = map(rya, -35, -130, -1, -130);
  }
  if (rxa >= 35 && rxa <= 130) {
    rxa = map(rxa, 35, 130, 1, 130);
  }
  if (rxa <= -35 && rxa >= -130) {
    rxa = map(rxa, -35, -130, -1, -130);
  }

  x = lxa / sqrt(sq(lxa) + sq(lya));   //quadrant//
  if (lxa == 0 && lya == 0) // For theta = 0;
  {
    x = 1;
  } 
//  Serial.print("  x = ");
//  Serial.print(x);
//  Serial.print("  lya = ");
//  Serial.print(lya);


  theta_1 = acos(x);
  theta_1 = (theta_1 * 57.2974);
//  Serial.print("\ntheta_1  = ");
//  Serial.print(theta_1);

  if (lxa < 0 && (lya) <= 0) {
    theta_1 = 360 - theta_1;
  }
  else if (lxa >= 0 && (lya) < 0) {
    theta_1 = 360 - theta_1;
  }

  y = rxa / sqrt(sq(rxa) + sq(rya));
  if (rxa == 0 && rya == 0)
  {
    y = 1;
  }
  
  theta_2 = acos(y);
  theta_2 = theta_2 * 57.2974;

  if (rxa < 0 && (rya) <= 0) {
    theta_2 = 360 - theta_2;
  }
  else if (rxa >= 0 && (rya) < 0) {
    theta_2 = 360 - theta_2;
  }

  p1 = sqrt(sq(lxa) + sq  (lya));    //p1 resultant and p2 reesultant//  // RPM from logitech            600 total RPM
  p1 = constrain(p1, -70, 70);

  p2 = sqrt(sq(rxa) + sq  (rya));  // RPM from logitech
  p2 = constrain(p2, -126, 126);

  Y1 = sin(theta_1 * pie / 180); // Left side     //Angle//
  X1 = cos(theta_1 * pie / 180);
  Y2 = sin(theta_2 * pie / 180); // Right side
  X2 = cos(theta_2 * pie / 180);

  if (lxa >= 0 || lxa < 0) // For Left Button      //Vector eqn//
  {
    V_2L =    p1 * Y1;
    V_1L =    p1 * Y1;
    V_3L =    p1 * X1;
    V_4L =    p1 * X1;
  }
  if (rxa >= 0 || rxa < 0) // For Right Button
  {
    V_2R =    p2 * Y2;
    V_1R =    p2 * Y2;
    V_3R =    p2 * X2;
    V_4R =    p2 * X2;
  }

  V1 = V_1L + V_1R;
  V2 = V_2L + V_2R;
  V3 = V_3L + V_3R;
  V4 = V_4L + V_4R;
  
  timer = millis();    //IMU//
  Vector norm = mpu.readNormalizeGyro();
  yaw = yaw + norm.ZAxis * timeStep;
  error = setpoint - yaw;
 
  if (Joy.rt == 1 && flag_1 == 0) {   //Robot angle Right side//
    flag_1  = 1;
    counter += 2;
    gat = 0;
  }
  else if (Joy.rt == 0 && flag_1 == 1) {
    flag_1 = 0;
  }
  if (Joy.lt == 1 && flag_2 == 0) {   // Robot angle Left side//
    flag_2  = 1;
    counter_1 += 2;
    sat = 0;
  }
  else if (Joy.lt == 0 && flag_2 == 1) {
    flag_2 = 0;
  }
  
  if (counter % 2 == 1 && gat == 0) {  //Setpoint//
   m1 = m1+0.2;
   m2 = m2+0.2;
   gat = 1;
  }
  
  if (counter_1 % 2 == 1 && sat == 0) {
   m1 = m1-0.2;
   m2 = m2-0.2;
   sat = 1;    
  }
  
  PID_p = kp * error;   //PID//
  PID_i += error;
  PID_d = kd * (error - previous_error);
  previous_error  = error;
  PID_total = PID_p + ki * PID_i + PID_d;
//  Serial.print("\nPID total = ");
//  Serial.print(PID_total);
  PID_total = constrain(PID_total , -35, 35);  //Mapping ************
  V1 = V1 - PID_total;
  V4 = V4 + PID_total; 
  V3 = V3 - PID_total;
  V2 = V2 + PID_total;

  if (Joy.red == 1) {           // mechanism                // Cylinder
    digitalWrite(2 , HIGH);
//    Serial.print("\npressed ");
  }
  else {
    digitalWrite(2 , LOW);
//    Serial.print("\nreleased ");
  }

  if (Joy.green == 1) {                       //ball throw
    digitalWrite(6 , HIGH);
    //    Serial.print("  6_pressed ");
  }
  else {
    digitalWrite(6 , LOW);
    //    Serial.print(" 6_released ");
  }

  if (Joy.blue == 1 && flag_4 == 0) {
    flag_4  = 1;
    counter_4 += 1 ;
  }
  else if (Joy.blue == 0 && flag_4 == 1) {
    flag_4 = 0;
  }

  if (counter_4 % 2 == 1) {                //cyliner up and down    
    digitalWrite(4, HIGH);
  }
  if (counter_4 % 2 == 0) {
    digitalWrite(4, LOW);
  }

  V1 = constrain( V1 , -125, 125);    //Wheels are constrained for a limit//
  V2 = constrain( V2 , -125, 125);
  V3 = constrain( V3 , -125, 125);
  V4 = constrain( V4 , -125, 125);
//  ST_1.motor(1,-V4);
//  ST_1.motor(2,-V1);  //  -
//  ST_2.motor(1,V3);  //  -
//  ST_2.motor(2,-V2);
    
    odrive.SetVelocity(0, m1);
    odrive.SetVelocity(1, -m2);
    Serial.print("\nm1 = ");
    Serial.print(m1);
    Serial.print("  m2 = ");
    Serial.print(m2);

}
