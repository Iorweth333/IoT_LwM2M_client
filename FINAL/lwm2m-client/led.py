import time
import serial
import sys
	
ser = serial.Serial('/dev/ttyS0', 38400)
ser.write(chr(128+16))

if (sys.argv[1]=='1'):
	ser.write(chr(33))
if (sys.argv[1]=='0'):
	ser.write(chr(32))
