#include<math.h>
#include<Wire.h>
#include <Servo.h>
/**********IMU declaration**********/
#include <MPU6050.h>
MPU6050 mpu;

Servo myservo;


/*Motor pins declaration*/
//head motor
int IN1_HEAD = 6; // FOR CLK: HIGH 
int IN2_HEAD = 7; // FOR ANTCLK : HIGH 
int ENA_HEAD = 5; // SPEED ADJUSTMENT

//left motor
int IN1_LEFT = 9;
int IN2_LEFT = 10;
int ENA_LEFT = 8;

//right motor
int IN3_RIGHT = 12;
int IN4_RIGHT = 13;
int ENB_RIGHT = 11;


//
int buzzer1 = 22;
int buzzer2 = 24;

//indication LED
int motion = 30;
int pick = 32;
int place = 28;


// Timers
unsigned long timer = 0,tp=0,reading = 0;
float timestep = 0.01;

// Pitch, Roll and Yaw values
float pitch = 0;
float roll = 0;
float yaw = 0;


/*Bluetooth module declaration*/
char command = 'S'  ;


/*Algorithms function declaration*/
void Clockwise();
void AntiClockwise();
void V1_Clockwise();
void V2_Clockwise();
void V3_Clockwise();
void V1_AntiClockwise();
void V2_AntiClockwise();
void V3_AntiClockwise();

float theta = 0 * M_PI / 180;
float V1, V2, V3;
float Vx, Vy;
float V = 225,VL=0,Mpwm=0;
float error = 0;
float PID = 0;
float P, I, D;
float preverror = 0;
float kp = 118;
float ki = 0.0001;
float kd = 170;
float setpoint = 0.00;
int count=0,count1=0;


void setup() {
  // put your 5setup code here, to run once:s

  /*IMU initialization*/
  Serial.begin(115200);
  Serial1.begin(9600);
  Wire.begin();
  // Initialize MPU6050
  while(!mpu.begin(MPU6050_SCALE_2000DPS, MPU6050_RANGE_2G))
  {
    Serial.println("Could not find a valid MPU6050 sensor, check wiring!");
    delay(500);
  }
  
  // Calibrate gyroscope. The calibration must be at rest.
  // If you don't want calibrate, comment this line.
  mpu.calibrateGyro();

  // Set threshold sensivty. Default 3.
  // If you don't want use threshold, comment this line or set 0.
   mpu.setThreshold(3);

 // servo 
 myservo.attach(44);
 
 //buzzer
 pinMode(buzzer1, OUTPUT);
 pinMode(buzzer2, OUTPUT);


 //led
 pinMode(motion, OUTPUT);
 pinMode(pick, OUTPUT);
 pinMode(place, OUTPUT);

   /***********Wheel pinmode **************/
   //HEAD
  pinMode(IN1_HEAD,OUTPUT);
  pinMode(IN2_HEAD,OUTPUT);
  pinMode(ENA_HEAD,OUTPUT);

  //RIGHT
  pinMode(IN1_LEFT,OUTPUT);
  pinMode(IN2_LEFT,OUTPUT);
  pinMode(ENA_LEFT,OUTPUT);

  //LEFT
  pinMode(IN3_RIGHT,OUTPUT);
  pinMode(IN4_RIGHT,OUTPUT);
  pinMode(ENB_RIGHT,OUTPUT);

}

void loop() {
  // put your main code here, to run repeatedly:

  /*IMU definition*/
  timer = millis();
  // Read normalized values
  Vector norm = mpu.readNormalizeGyro();
  
  // Calculate Yaw
  yaw = yaw + norm.ZAxis * timestep;

  Serial.print(" Yaw = ");
  Serial.println(yaw);

  // Wait to full timeStep period
  delay((timestep*1000) - (millis() - timer));

  // servo intial position
 
  /*Bluetooth module commands*/
  if(Serial1.available()>0)
  {
    command  = Serial1.read();
  }
  Serial.print(" command:  ");
  Serial.print(command);
  
  if (command == 'F')
  {
    theta = 90 * M_PI / 180;
    
  }
  if (command == 'B')
  {
    theta = 270 * M_PI / 180;
    
  }
  if (command == 'L')
  {
    theta = 180 * M_PI / 180;
//    digitalWrite(motion,HIGH);
  }
  if (command == 'R')
  {
    theta = 0 * M_PI / 180;
//    digitalWrite(motion,HIGH);
  }
   if (command == 'G')
  {
    theta = 135 * M_PI / 180;
  }
  if (command == 'I')
  {
    theta = 45 * M_PI / 180;
  }
  if (command == 'H')
  {
    theta = 225 * M_PI / 180;
  }
  if (command == 'J')
  {
    theta = 315 * M_PI / 180;
  }


  if(command == 'W')
  {
    count++;
  }
  if(count ==0)
  {
   setpoint = 0;
  }
  if(count==1)
  {
   setpoint = 60;
  }
  if(count==3)
  {
   setpoint = 0;
  }
  if(count == 2)
  {
   setpoint = 130;
  }
   
  
  
  if(command == 'V')
  {
  
  tone(buzzer1, 1000); // Send 1KHz sound signal...
  delay(300);        // ...for 0.5 sec
  noTone(buzzer1);     // Stop sound...
  delay(300);        // ...for 1sec
  digitalWrite(pick,HIGH);
  delay(500);
  digitalWrite(pick,LOW);
  delay(1000);
  }

  if(command == 'v')
  {
  tone(buzzer2, 1500); // Send 1KHz sound signal...
  delay(100);        // ...for 1 sec
  noTone(buzzer2);     // Stop sound...
  delay(100);        // ...for 1sec
  tone(buzzer2, 1500); // Send 1KHz sound signal...
  delay(100);
  noTone(buzzer2);     // Stop sound...
  delay(100);
  digitalWrite(place,HIGH);
  delay(500);
  digitalWrite(place,LOW);
  delay(1000);       
   
  }

  //
  
   
   
 
  
  //Yaw PID 
  error = yaw - setpoint;
  P = kp * error;
  I = I + (error * ki);
  D = kd * (error - preverror);
  PID = P + I + D;
  preverror = error;
  
  Serial.print(" V1: ");
  Serial.print(V1);
  Serial.print(" V2: ");
  Serial.print(V2);
  Serial.print(" V3: ");
  Serial.print(V3);
  
 
  
  Serial.print("V: ");
  Serial.print(V);
  Vx = V * cos(theta);
  Vy = -V * sin(theta);


  V1 = Vx;
  V2 = -(Vx * 0.5 + Vy * 0.866); //PID ;
  V3 = (-Vx * 0.5) + (Vy * 0.866)  ;// PID;

  V1 = V1 + PID;
   V2 = V2 + PID;
    V3 = V3 + PID;
 
  

 if( command == 'S')
  {
     V1 = 0+PID;
     V2 = 0+PID;
     V3 = 0+PID;
  }


  if (V1 >= 0)
  {
    V1_Clockwise();
  }
  else
  {
    V1_AntiClockwise();
  }
  if (V2 >= 0)
  {
    V2_Clockwise();
  }
  else
  {
    V2_AntiClockwise();
  }
  if (V3 >= 0)
  {
    V3_Clockwise();
  }
  else
  {
    V3_AntiClockwise();
  }

 if(count>3)
  count = 0;
  
  if(count1>2)
  count1 = 0;
}

void V1_Clockwise()
{
  analogWrite(ENA_HEAD, abs(V1));
  digitalWrite(IN1_HEAD, HIGH);
  digitalWrite(IN2_HEAD, LOW);

}
void V1_AntiClockwise()
{
  analogWrite(ENA_HEAD, abs(V1));
  digitalWrite(IN1_HEAD, LOW);
  digitalWrite(IN2_HEAD, HIGH);
}

void V2_Clockwise()
{
  analogWrite(ENA_LEFT, abs(V2));
  digitalWrite(IN1_LEFT, HIGH);
  digitalWrite(IN2_LEFT, LOW);
}
void V2_AntiClockwise()
{

  analogWrite(ENA_LEFT, abs(V2));
  digitalWrite(IN1_LEFT, LOW);
  digitalWrite(IN2_LEFT, HIGH);

}
void V3_Clockwise()
{
  analogWrite(ENB_RIGHT, abs(V3));
  digitalWrite(IN3_RIGHT, HIGH);
  digitalWrite(IN4_RIGHT, LOW);
}
void V3_AntiClockwise()
{  analogWrite(ENB_RIGHT, abs(V3));
  digitalWrite(IN3_RIGHT, LOW);
  digitalWrite(IN4_RIGHT, HIGH);
}
