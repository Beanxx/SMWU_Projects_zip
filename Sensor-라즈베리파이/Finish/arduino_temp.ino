

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
      if((25<= temp) && (temp < 30.0)){
          digitalWrite(8, HIGH); // G
          digitalWrite(7, LOW);
          digitalWrite(6, LOW);
          
          delay(1000);
      }
      else if((30.0 <= temp) && (temp < 37.0)){
          digitalWrite(8, LOW);
          digitalWrite(7, HIGH); // Y
          digitalWrite(6, LOW);
          delay(1000);
      }
      else if((37.0 < temp) && (temp < 50)){
          digitalWrite(8, LOW);
          digitalWrite(7, LOW);
          digitalWrite(6, HIGH); // R
          delay(1000);
      }
      else if((temp < 25) || (temp>1000)){
          digitalWrite(8, LOW);
          digitalWrite(7, LOW);
          digitalWrite(6, LOW); 
          delay(1000);      
      }

  
     
  
  //Serial.println();
  
}
