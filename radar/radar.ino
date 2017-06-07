
#include <Servo.h>
#include <NewPing.h>

#define TRIGGER_PIN  12  
#define ECHO_PIN     11  
#define MAX_DISTANCE 80 // Maxima distancia del sensor de proximidad

NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE); 


Servo s1;
#define servoPin 10

int ang = 85; //0-85-180
int aum = 5;
int dist;

void setup()
{
  s1.attach(servoPin);
  s1.write(ang); //vuelta a 85 grados

  Serial.begin(9600); 
  int s = 0; //Especie de "handshaking" para comprobar la correcta conexion con java.
  while (s != 1)
  {
    Serial.write(1);
    s = Serial.read();
  } 
  delay(1000); 
}
// bucle principal
void loop()
{ 
  moverServo();
  delay(80);
  medirDistancia();
  enviarDatos();  
}
// funciones auxiliares
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
    dist = sonar.ping()  / US_ROUNDTRIP_CM; // mide la distancia en cm
}

void enviarDatos()
{
  Serial.println(ang);  //se envian tanto el angulo como la distancia
  Serial.println(dist);  
}

