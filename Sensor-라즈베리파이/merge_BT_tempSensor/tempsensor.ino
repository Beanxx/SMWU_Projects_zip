#include <Wire.h>
#include <Adafruit_MLX90614.h>

// LED
#define Green 8
#define Yellow 7
#define Red 6

// Temperature
#define LTemp 35.0 //lower
#define MTemp 40.0 //midium
#define UTemp 45.0 //upper

Adafruit_MLX90614 mlx = Adafruit_MLX90614();
float temp;
char incomingByte = 0;   // 수신 데이터를 저장하는 변수
const char cmd = 't';

void setup() {
  Serial.begin(115200);
  pinMode(Green, OUTPUT); //G
  pinMode(Yellow,  OUTPUT); // Y
  pinMode(Red, OUTPUT); // R
  //Serial.println("Adafruit MLX90614 test");

  mlx.begin();
}

void loop()
{
  //Serial.print("Ambient = "); Serial.print(mlx.readAmbientTempC());
  //Serial.print("*C\tObject = "); Serial.print(mlx.readObjectTempC()); Serial.println("*C");

  //delay(100);
  if(Serial.available() >0)
  {
     // 읽어온 것을 변수에 저장
    incomingByte = Serial.read();

    // 읽어온 것을 다시 시리얼을 통해 송신
    Serial.print(incomingByte);

    if(incomingByte == 't')
    {
      Serial.println("1515");
      //Serial.println(incomingByte);
      delay(100);
      while(1){
          //temp = mlx.readObjectTempC();
          //Serial.println(temp);
          if( 0< temp && temp <1000 )
          {
              //Serial.println("come first");
              delay(1000);
              temp = mlx.readObjectTempC();
              //Serial.print("Arduino valid temp: ");
              Serial.println(temp);
              //Serial.println(" C");
              delay(100);
              
               // 3 LED
              if((LTemp<= temp) && (temp < MTemp))
              {
                      digitalWrite(Green, HIGH); // G
                      digitalWrite(Yellow, LOW);
                      digitalWrite(Red, LOW);
    
                      delay(500);
              }
              else if((MTemp <= temp) && (temp < UTemp))
              {
                      digitalWrite(Green, LOW);
                      digitalWrite(Yellow, HIGH); // Y
                      digitalWrite(Red, LOW);
                      delay(500);
              }
              else if((UTemp < temp) && (temp < 100))
              {
                      digitalWrite(Green, LOW);
                      digitalWrite(Yellow, LOW);
                      digitalWrite(Red, HIGH); // R
                      delay(500);
              }
              else if( (temp < LTemp) || (100<= temp) )
              {
                      digitalWrite(Green, LOW);
                      digitalWrite(Yellow, LOW);
                      digitalWrite(Red, LOW);
                      delay(500);
              }
    
                digitalWrite(Green, LOW);
                digitalWrite(Yellow, LOW);
                digitalWrite(Red, LOW);
                //delay(1000);
    
              //Serial.println();

            incomingByte = 0;
            break;
              

              
          }


         


      }


      //if((LTemp <= temp) && (temp < 100)){
        //Serial.print("Temperature_in_Arduino: ");

          //delay(300);
        //}

    }



  }



}
