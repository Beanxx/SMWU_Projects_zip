""" [INFO]

Input: 
  1. Arduino:
   - temperature value from Arduino temperature sensor
  2. Raspberry Pi:
   - user information by bluetooth
   - temperature value from Arduino by serial communication

Ouput:
   1. Arduino:
    - pass temperature value to Rasspberry Pi
    - turn on 3 LED according to temperature
   2. Raspberry Pi:
    - text on LCD according to temperature
    - make a piezo sound according to the temperature
    - Store user information and temperature in text file
    
    
"""
import time
import serial

# arduino port info
port = "/dev/ttyUSB0"
seri = serial.Serial(port, baudrate=115200, timeout=None)



# main
def main():
    
    
    while True:
        if seri.in_waiting != 0:
            # receive temperature from Arduino
            line = seri.readline()
            arr = line.split()
            if len(arr) < 3:
                continue
                
            data = float(arr[1])

            print("Temperature: %.1f C" % data)

            time.sleep(0.01)
            
            # temperature processing algorithm
            # 여기에 알고리즘 추가
            if(data >= 36.5):
                print("good")
           
        

if __name__ == "__main__":
    main()
