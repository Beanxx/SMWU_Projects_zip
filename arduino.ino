/*************************************************** 
  This is a library example for the MLX90614 Temp Sensor

  Designed specifically to work with the MLX90614 sensors in the
  adafruit shop
  ----> https://www.adafruit.com/products/1747 3V version
  ----> https://www.adafruit.com/products/1748 5V version

  These sensors use I2C to communicate, 2 pins are required to  
  interface
  Adafruit invests time and resources providing this open source code, 
  please support Adafruit and open-source hardware by purchasing 
  products from Adafruit!

  Written by Limor Fried/Ladyada for Adafruit Industries.  
  BSD license, all text above must be included in any redistribution
 ****************************************************/

#include <Wire.h>
#include <Adafruit_MLX90614.h>

Adafruit_MLX90614 mlx = Adafruit_MLX90614();
float temp;


void setup() {
  Serial.begin(115200);
  pinMode(8, OUTPUT); //G
  pinMode(7, OUTPUT); // Y
  pinMode(6, OUTPUT); // R
  //Serial.println("Adafruit MLX90614 test");  

  mlx.begin();  
}

void loop() {
  //Serial.print("Ambient = "); Serial.print(mlx.readAmbientTempC()); 
  //Serial.print("*C\tObject = "); Serial.print(mlx.readObjectTempC()); Serial.println("*C");
//  Serial.print("Ambient = "); Serial.print(mlx.readAmbientTempF()); 
//  Serial.print("*F\tObject = "); Serial.print(mlx.readObjectTempF()); Serial.println("*F");
  
  delay(100);
  
  temp = mlx.readObjectTempC();
  Serial.print("Temperature_in_Arduino: ");
  Serial.print(temp);
  Serial.println(" C");
  
  delay(300);
  
  // 3 LED
      if((35.0<= temp) && (temp < 40.0)){
          digitalWrite(8, HIGH); // G
          digitalWrite(7, LOW);
          digitalWrite(6, LOW);
          
          delay(1000);
      }
      else if((40.0 <= temp) && (temp < 45.0)){
          digitalWrite(8, LOW);
          digitalWrite(7, HIGH); // Y
          digitalWrite(6, LOW);
          delay(1000);
      }
      else if((45.0 < temp) && (temp < 100)){
          digitalWrite(8, LOW);
          digitalWrite(7, LOW);
          digitalWrite(6, HIGH); // R
          delay(1000);
      }
      else if( (temp < 35) || (100<= temp) ){
          digitalWrite(8, LOW);
          digitalWrite(7, LOW);
          digitalWrite(6, LOW); 
          delay(1000);      
      }

  
     
  
  //Serial.println();
  
}
