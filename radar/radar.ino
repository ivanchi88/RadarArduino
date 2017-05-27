
#include <Servo.h>
#include <NewPing.h>

#define TRIGGER_PIN  12  // Arduino pin tied to trigger pin on the ultrasonic sensor.
#define ECHO_PIN     11  // Arduino pin tied to echo pin on the ultrasonic sensor.
#define MAX_DISTANCE 80 // Maximum distance we want to ping for (in centimeters). Maximum sensor distance is rated at 400-500cm.

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE); // NewPing setup of pins and maximum distance.



Servo s1;//create servo object to control a servo
#define servoPin 10

int ang = 85; //0-85-180
int aum = 5;
int dist;

void setup()
{
  s1.attach(servoPin);//attachs the servo on pin 10 to servo object
  s1.write(ang);//back to "90" degrees


  Serial.begin(9600); 
  int s = 0;
  while (s != 1)
  {
    Serial.write(1);
    s = Serial.read();
  }
  
  delay(1000);//wait for a second
}
/*************************************************/
void loop()
{ 
  moverServo();
  delay(80);
  medirDistancia();
  enviarDatos();
  
}
/**************************************************/

void moverServo()
{
  if (ang <= 0){
    aum = - aum;
  }
  else {
    if (ang >= 170)
    {
      aum = - aum;
    }
  }
  ang += aum;
  s1.write(ang);
}

void medirDistancia()
{
    dist = sonar.ping()  / US_ROUNDTRIP_CM;
  
}

void enviarDatos()
{
Serial.println(ang);
Serial.println(dist);  
}

