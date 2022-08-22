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
int v = 10;
char command;
void setup()
{
  Serial.begin(9600);
  Serial1.begin(115200);
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

void loop()
{

  delay(10);
  while(Serial1.available()) 
  {
  command = "";  
  command = Serial1.read();
  
  Serial.print(command);
  }

 
  if(command == 'F')
  {
    forward();
  }
  
}

void forward()
{
  digitalWrite(ENA_HEAD, HIGH);
  digitalWrite(IN1_HEAD, HIGH);
  digitalWrite(IN2_HEAD, LOW);

  

  digitalWrite(ENA_LEFT,HIGH);
  digitalWrite(IN1_LEFT,LOW);
  digitalWrite(IN2_LEFT,HIGH);
  
  digitalWrite(ENB_RIGHT, HIGH);
  digitalWrite(IN3_RIGHT, HIGH);
  digitalWrite(IN4_RIGHT, LOW);
  
}
