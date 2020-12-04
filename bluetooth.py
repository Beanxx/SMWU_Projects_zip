#-*- coding:utf-8 -*-
from bluetooth import *

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
    
try:
    while True:
        byte_data = client.recv(1024)
        data = byte_data.decode().strip()
        print("studentID, name : ", data)
        f = open("test.txt", 'a')
        f.write(data + '\n')
        f.close()
        
        client.send(data.encode())

except KeyboardInterrupt:
    print("terminate")
    
client.close()
server.close()

