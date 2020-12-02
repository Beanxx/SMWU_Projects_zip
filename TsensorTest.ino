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
float meanTemp;
#define cnt 10;


void setup() {
  Serial.begin(9600);

  Serial.println("Adafruit MLX90614 test");  

  mlx.begin();  
}

void loop() {
  //int i;
  //Serial.println("0");
  for(int i=0; i<10; i++){
    //Serial.println("1");
    float obTemp = mlx.readObjectTempC();
    if((33 < obTemp) && (obTemp < 40)){
      //Serial.println("2");
      
      //Serial.print("obTemp = ");Serial.println(obTemp);
      meanTemp = meanTemp + obTemp;
      //Serial.print("meanTemp = ");Serial.println(meanTemp);
    }
    else{
      Serial.println("i = "); Serial.println(i);
      i--;
    }
    
  }
  
  temp = meanTemp/10.00;
  Serial.print("meanTemp = "); Serial.println(temp);
  
  //temp = mlx.readObjectTempC();
  if((33 > temp) && (temp > 40)){
    //Serial.print("Ambient = "); Serial.print(mlx.readAmbientTempC()); 
    //Serial.print("Object = "); Serial.print(temp); Serial.println("*C");
    Serial.print("Nothing = "); Serial.println(mlx.readObjectTempC()); 
  }
  //Serial.print("Ambient = "); Serial.print(mlx.readAmbientTempF()); 
  //Serial.print("*F\tObject = "); Serial.print(mlx.readObjectTempF()); Serial.println("*F");

  meanTemp = 0;
  
  delay(1000);
}
