import time
import serial

ser = serial.Serial('/dev/ttyS0', 38400)
ser.write(chr(128+64+2))

cc = ser.read(1)
temp = ord(cc)/2+10

b = 32+16+8+4+2+1         
c = temp & b        # 12 = 0000 1100

print(c)
f= open("temp.txt","w+")
f.write(str(c))
		