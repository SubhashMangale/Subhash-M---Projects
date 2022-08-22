#include <Wire.h>
#include <MPU6050.h>

MPU6050 mpu;

//head motor 
int IN1_HEAD = 6;
int IN2_HEAD = 7;
int ENA_HEAD = 5;

//RIGHT MOTOR
int IN1_RIGHT = 8;
int IN2_RIGHT = 9;
int ENA_RIGHT = 4;

//LEFT MOTOR
int IN3_LEFT = 10;
int IN4_LEFT = 11;
int ENB_LEFT = 3;

char command;

// Timers
unsigned long timer = 0;
float timeStep = 0.01;

// Pitch, Roll and Yaw values
float pitch = 0;
float roll = 0;
float yaw = 0;


/***WHEELS VARIABLE******/
float DA = 0;
float Mpwm = 0;
float w1 = 0, w2 = 0, w3 = 0;
float a = 0, b = 0, c = 0, setpos = 0, crrpos = 0;


/*****Yaw  PID********/
short int setpoint = 0;
float timer = 0, i = 0, u = 0, timeStep = 0, yaw = 0;
float PrErr = 0, Err = 0, DiErr = 0, IErr = 0;
float Ykp = 6.9, Yki = 0.00001, Ykd = 90; //kd=80,kp=8,ki=0.0001
float YawPID = 0;


void setup()
{
  Serial.begin(115200);

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
  
  //HEAD
  pinMode(IN1_HEAD,OUTPUT);
  pinMode(IN2_HEAD,OUTPUT);
  pinMode(ENA_HEAD,OUTPUT);

  //RIGHT
  pinMode(IN1_RIGHT,OUTPUT);
  pinMode(IN2_RIGHT,OUTPUT);
  pinMode(ENA_RIGHT,OUTPUT);

  //LEFT
  pinMode(IN3_LEFT,OUTPUT);
  pinMode(IN4_LEFT,OUTPUT);
  pinMode(ENB_LEFT,OUTPUT);
  
}

void loop()
{
  timer = millis();

  // Read normalized values
  Vector norm = mpu.readNormalizeGyro();

  // Calculate Pitch, Roll and Yaw
  pitch = pitch + norm.YAxis * timeStep;
  roll = roll + norm.XAxis * timeStep;
  yaw = yaw + norm.ZAxis * timeStep;

  // Output raw
//  Serial.print(" Pitch = ");
//  Serial.print(pitch);
//  Serial.print(" Roll = ");
//  Serial.print(roll);  
  Serial.print(" Yaw = ");
  Serial.println(yaw);

  // Wait to full timeStep period
  delay((timeStep*1000) - (millis() - timer));

      Mpwm = 128;
      w1 = Mpwm * cos((11 * M_PI / 6) + (DA + setpoint * M_PI / 180)); //left
      w2 = Mpwm * cos((7 * M_PI / 6) + (DA + setpoint * M_PI / 180)); // right
      w3 = Mpwm * cos((M_PI / 2) + (DA + setpoint * M_PI / 180)); //head
  
    
         /************ YAW PID *******/
    Err = setpoint - yaw;
    DiErr = Err - PrErr;
    IErr += Yki * Err;
    YawPID = Err *  Ykp + IErr +  Ykd * DiErr;
    PrErr = Err;

    /************ YAW CONSTAINTS *************/
    YawPID = constrain( YawPID, -30, 30 );


    a = w1 + YawPID ; // left
    b = w2 + YawPID ; //right
    c = w3 + YawPID ; // head

  delay(10);
  while(Serial.available()) 
  {
  command = "";  
  command = Serial.read();
  
  Serial.print(command);
  }

  if(command == 'F')
  {
    forward(float a,float b,float c );
  }
  else if(command == 'B'){
    backward(float a,float b,float c);
  }
  else if(command == 'L'){
    left(float a,float b,float c);
  }
  else if(command == 'R'){
    right(float a,float b,float c);
  }
  else if(command == 'S') {
    Stop(float a,float b,float c);
 }

 
  
}

void forward(float a,float b,float c)
{
  digitalWrite(IN1_HEAD,LOW);
  digitalWrite(IN2_HEAD,LOW);
  analogWrite(ENA_HEAD,c);

  digitalWrite(IN1_RIGHT,LOW);
  digitalWrite(IN2_RIGHT,HIGH);
  digitalWrite(ENA_RIGHT,b);
  
  digitalWrite(IN3_LEFT,HIGH);
  digitalWrite(IN4_LEFT,LOW);
  digitalWrite(ENB_LEFT,a);
}

void backward(float a,float b,float c)
{
  digitalWrite(IN1_HEAD,LOW);
  digitalWrite(IN2_HEAD,LOW);
  digitalWrite(ENA_HEAD,c);

  digitalWrite(IN1_RIGHT,HIGH);
  digitalWrite(IN2_RIGHT,LOW);
  digitalWrite(ENA_RIGHT,b);
  
  digitalWrite(IN3_LEFT,LOW);
  digitalWrite(IN4_LEFT,HIGH);
  digitalWrite(ENB_LEFT,a);
}

void left(float a,float b,float c)
{
   digitalWrite(IN1_HEAD,LOW);
  digitalWrite(IN2_HEAD,HIGH);
  digitalWrite(ENA_HEAD,c); // for left head: anticlock

  digitalWrite(IN1_RIGHT,LOW);
  digitalWrite(IN2_RIGHT,LOW);
  digitalWrite(ENA_RIGHT,b);// for right motor:  stop
  
  digitalWrite(IN3_LEFT,HIGH);
  digitalWrite(IN4_LEFT,LOW);
  digitalWrite(ENB_LEFT,a); // for left motor: clockwise
  
}


void right(float a,float b,float c)
{
  digitalWrite(IN1_HEAD,HIGH);
  digitalWrite(IN2_HEAD,LOW);
  digitalWrite(ENA_HEAD,c);// head: clock

  digitalWrite(IN1_RIGHT,LOW);
  digitalWrite(IN2_RIGHT,HIGH);
  digitalWrite(ENA_RIGHT,b);// right: ANTICLK
  
  digitalWrite(IN3_LEFT,LOW);
  digitalWrite(IN4_LEFT,LOW);
  digitalWrite(ENB_LEFT,a);// left: clkwise
  
}
void Stop(float a,float b,float c)
{
  digitalWrite(IN1_HEAD,LOW);
  digitalWrite(IN2_HEAD,LOW);
  digitalWrite(ENA_HEAD,c);

  digitalWrite(IN1_RIGHT,LOW);
  digitalWrite(IN2_RIGHT,LOW);
  digitalWrite(ENA_RIGHT,b);
  
  digitalWrite(IN3_LEFT,LOW);
  digitalWrite(IN4_LEFT,LOW);
  digitalWrite(ENB_LEFT,a);
}
