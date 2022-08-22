 #include<math.h>
#include <Wire.h>


#include <MPU6050.h>
MPU6050 mpu;

int DR_F1 = 6;
int DR_F2 = 7;
int PWM_F = 5;

int PWM_L = 3;
int DR_L1 = 10;
int DR_L2 = 11;

int PWM_R = 4;
int DR_R1 = 8;
int DR_R2 = 9;


int VL = 0;


char SER_IP = 'S';
// specific I2C address may be passed here



bool blinkState = false;
// Timers
unsigned long timer = 0,tp=0,reading = 0;
float timestep = 0.01;

// Pitch, Roll and Yaw values
float yaw = 0;

void V1_Clockwise();
void V2_Clockwise();
void V3_Clockwise();
void V1_AntiClockwise();
void V2_AntiClockwise();
void V3_AntiClockwise();

float theta = 0 * M_PI / 180;
float V1, V2, V3;
float Vx, Vy;
float V = 100;
float timeStep = 0.01;
float error = 0;
float PID = 0;
float P, I, D;
float preverror = 0;
float kp = 13;
float ki = 0.0001;
float kd = 171 ;
float setpoint = 0;
int count;
void setup()
{
  pinMode(PWM_F, OUTPUT);
  pinMode(DR_F1, OUTPUT);
  pinMode(DR_F2, OUTPUT);
  pinMode(PWM_L, OUTPUT);
  pinMode(DR_L1, OUTPUT);
  pinMode(DR_L2, OUTPUT);
  pinMode(PWM_R, OUTPUT);
  pinMode(DR_R1, OUTPUT);
  pinMode(DR_R1, OUTPUT);


  Wire.begin();
  Serial.begin(115200);
  Serial1.begin(9600);
  
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
//   mpu.setThreshold(3);
}



void loop() {

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

  Serial.println(yaw);
  if (SER_IP == 'X')
  {
    setpoint = (setpoint + 10);
  }

  if (Serial1.available() > 0)
  {
    SER_IP = Serial1.read();
  }
  if (SER_IP == 'F')
  {
    theta = 90 * M_PI / 180;
  }
  if (SER_IP == 'B')
  {
    theta = 270 * M_PI / 180;
  }
  if (SER_IP == 'L')
  {
    theta = 180 * M_PI / 180;
  }
  if (SER_IP == 'R')
  {
    theta = 0 * M_PI / 180;
  }
  if (SER_IP == 'G')
  {
    theta = 135 * M_PI / 180;
  }
  if (SER_IP == 'I')
  {
    theta = 45 * M_PI / 180;
  }
  if (SER_IP == 'H')
  {
    theta = 225 * M_PI / 180;
  }
  if (SER_IP == 'J')
  {
    theta = 315 * M_PI / 180;
  }
  if (SER_IP == 'q')
  {
    V = 100;
  }

  if (SER_IP >= '1' && SER_IP <= '9')
  {
    VL = SER_IP ;
  }
  
  V = map(VL , 49, 58, 0, 255);
  if (V == -1388)
  {
    V = 0;
  }
  Serial.println(V);
  error = yaw - setpoint;
  P = kp * error;
  I = I + (error * ki);
  D = kd * (error - preverror);
  PID = P + I + D;
  preverror = error;
  

  if (SER_IP == 'S')
  {
    V = 0;
  }
  
  Vx = V * cos(theta);
  Vy = - V * sin(theta);

  V1 = Vx + PID;
  V2 = -(Vx * 0.5 + Vy * 0.866) + PID ;
  V3 = -Vx * 0.5 + Vy * 0.866 + PID;

  Serial.println(V1);
    Serial.println(V2);
      Serial.println(V3);
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
  //  digitalWrite(Green, HIGH);
  //    digitalWrite(Red, HIGH);
  //   digitalWrite(Blue, HIGH);
}

void V1_Clockwise()
{
  analogWrite(PWM_F, abs(V1));
  digitalWrite(DR_F1, HIGH);
  digitalWrite(DR_F2, LOW);

}
void V2_Clockwise()
{

  analogWrite(PWM_L, abs(V2));
  digitalWrite(DR_L1, HIGH);
  digitalWrite(DR_L2, LOW);


}
void V3_Clockwise()
{

  analogWrite(PWM_R, abs(V3));
  digitalWrite(DR_R1, HIGH);
  digitalWrite(DR_R2, LOW);

}
void V1_AntiClockwise()
{
  analogWrite(PWM_F, abs(V1));
  digitalWrite(DR_F1, LOW);
  digitalWrite(DR_F2, HIGH);
}
void V2_AntiClockwise()
{

  analogWrite(PWM_L, abs(V2));
  digitalWrite(DR_L1, LOW);
  digitalWrite(DR_L2, HIGH);

}
void V3_AntiClockwise()
{
  analogWrite(PWM_R, abs(V3));
  digitalWrite(DR_R1, LOW);
  digitalWrite(DR_R2, HIGH);
}
//void AntiClockwise()
//{
//  analogWrite(PWM_F, abs(V1) );
//  digitalWrite(DR_F1, LOW);
//  digitalWrite(DR_F2, HIGH);
//  analogWrite(PWM_L, abs(V2));
//  digitalWrite(DR_L1, LOW);
//  digitalWrite(DR_L2, HIGH);
//  analogWrite(PWM_R, abs(V3) );
//  digitalWrite(DR_R1, LOW);
//  digitalWrite(DR_R2, HIGH);
//}
