/* How to use the DHT-22 sensor with Arduino uno
   Temperature and humidity sensor
*/

//Libraries
//#include <DHT.h>; 

//Constants
//#define DHTPIN 8     // what pin we're connected to
//#define DHTTYPE DHT22   // DHT 22  (AM2302)
//DHT dht(DHTPIN, DHTTYPE); //// Initialize DHT sensor for normal 16mhz Arduino


//Variables
//int chk;
//float hum;  //Stores humidity value
float temp; //Stores temperature value

void setup()
{
    Serial.begin(115200);
    pinMode(8, OUTPUT);
    //dht.begin();
}

void loop()
{
   
        
      delay(100);
      //Read data and store it to variables hum and temp
      //hum = dht.readHumidity();
      //temp = dht.readTemperature();
      temp = 36.5;
  
      //Print temp and humidity values to serial monitor
      /*Serial.print("Humidity: ");
      Serial.print(hum);
      Serial.print(" %, Temp: ");
      Serial.print(temp);
      Serial.println(" Celsius");
      */
      
     /* Serial.print("Humidity_in_Arduino: ");
      Serial.print(hum);
      Serial.println(" %");*/
      Serial.print("Temperature_in_Arduino: ");
      Serial.print(temp);
      Serial.println(" C");
  
      // 3 LED
      if(temp >= 36.5){
          digitalWrite(8, HIGH);
          delay(1000);
      }
      else{
          digitalWrite(8, LOW);
          delay(1000);
      }
  
      delay(300); //Delay 2 sec.
    
}
