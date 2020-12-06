
import time
import serial
from bluetooth import *

"""
#bluetooth setting
server = BluetoothSocket(RFCOMM)
server.bind(("", PORT_ANY))
server.listen(3)

print("start server...")

try:
    client, info = server.accept()
    print("client mac:", info[0], ", port:", info[1])

except KeyboardInterrupt:
    print("abort")
    server.close()
    exit()
"""

# arduino port info
port = "/dev/ttyUSB0"
seri = serial.Serial(port, baudrate=115200, timeout=None)

cmd = 't'


# main
try:


    while True:
        """
        byte_data = client.recv(1024)
        data = byte_data.decode().strip()
        """
        
        data = 222
        if(data):
            seri.write(cmd.encode())
            print(cmd);
##            time.sleep(1)
            # if seri.in_waiting != 0:
                # receive temperature from Arduino
            #print("before while")
            while True:
                #print("seri.inWaiting(): ", seri.inWaiting())
                #print("seri.inWaiting: ",seri.inWaiting())
                if seri.inWaiting() != 0:
                    line = seri.readline()
                    #arr = line.split()
                    #if len(arr) < 3:
                    #    continue

                    #temp = float(arr[1])
                    temp = float(line)
                    #print("Temperature: %.1f C" % temp)

                    #time.sleep(0.01)

                    if 0< temp and temp < 100 :
                        print("valid temp: ", temp)
                        break

            #print("after while")

            # temperature processing algorithm


            #print("good")
            print("studentID, name : ", data)
            print("student temperature: ", temp)
            info = data + str(temp)
            f = open("test.txt", 'a')
            f.write(info + '\n')
            f.close()

        """client.send(data.encode())"""
except KeyboardInterrupt:
    print("terminate")
"""
client.close()
server.close()
"""
