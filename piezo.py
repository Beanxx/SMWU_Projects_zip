#-*- coding:utf-8 -*-
import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)

gpio_pin=13
scale = [261, 294, 329, 349, 392, 440, 493, 523]
GPIO.setup(gpio_pin, GPIO.OUT)
list = [0,0,4,4,5,5,4,3,3,2,2,1,1,0]

data=40

try:
	if( data>=37.5):
		
		p = GPIO.PWM(gpio_pin, 100)
		p.start(100)
		p.ChangeDutyCycle(90)

		for i in range(len(list)): #길이 추출
			p.ChangeFrequency(scale[list[i]]) #주파수 변경
			if (i+1)%7 == 0: #7번째 음 박자 변경
				time.sleep(0.4)
			else :
				time.sleep(0.2)
		p.stop()

except KeyboardInterrupt:
    GPIO.cleanup()
