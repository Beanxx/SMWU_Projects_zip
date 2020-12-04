#-*- coding:utf-8 -*-
import RPi.GPIO as GPIO
import time


GPIO.setmode(GPIO.BCM)

LCD_RS=23
LCD_RW=24
LCD_E=26
LCD_D4=17
LCD_D5=18
LCD_D6=27
LCD_D7=22

LCD_WIDTH=16
LCD_CHR=True
LCD_CMD=False

LCD_LINE_1=0x80
LCD_LINE_2=0xC0

E_PULSE=0.0005
E_DELAY=0.0005

gpio_pin=13
scale = [261, 294, 329, 349, 392, 440, 493, 523]
GPIO.setup(gpio_pin, GPIO.OUT)
list = [0,0,4,4,5,5,4,3,3,2,2,1,1,0]

data = 39
dataa = str(data)

def main():
    GPIO.setwarnings(False)
    GPIO.setup(LCD_E, GPIO.OUT)
    GPIO.setup(LCD_RS, GPIO.OUT)
    GPIO.setup(LCD_D4, GPIO.OUT)
    GPIO.setup(LCD_D5, GPIO.OUT)
    GPIO.setup(LCD_D6, GPIO.OUT)
    GPIO.setup(LCD_D7, GPIO.OUT)

    #lcd_init()

    while True:
        lcd_string("temp : " + dataa + " C", LCD_LINE_1)
    
        if data<37:
	        lcd_string("Normal temp!", LCD_LINE_2)
        if 37<=data and data<37.5:
			lcd_string("Slight fever!", LCD_LINE_2)
        if data>37.5:
			lcd_string("High fever!!", LCD_LINE_2)
			p=GPIO.PWM(gpio_pin, 100)
			p.start(100)
			p.ChangeDutyCycle(90)
			
			for i in range(len(list)):
				p.ChangeFrequency(scale[list[i]])
				if (i+1)%7 == 0:
					time.sleep(0.4)
				else:
					time.sleep(0.2)
			p.stop()

def lcd_init():
    lcd_byte(0x33,LCD_CMD)
    lcd_byte(0x32,LCD_CMD) # 00110010 Initialise
    lcd_byte(0x06,LCD_CMD) # 00000110 Cursor move direction
    lcd_byte(0x0C,LCD_CMD) # 00001100 Display On,Cursor Off, Blink Off
    lcd_byte(0x28,LCD_CMD) # 00101000 Data length, number of lines, font size
    lcd_byte(0x01,LCD_CMD) # 00000001 Clear display
    time.sleep(E_DELAY)


def lcd_byte(bits, mode):
# Send byte to data pins
# bits = data
# mode = True for character
# False for command
    GPIO.output(LCD_RS, mode) # RS
    
# High bits
    GPIO.output(LCD_D4, False)
    GPIO.output(LCD_D5, False)
    GPIO.output(LCD_D6, False)
    GPIO.output(LCD_D7, False)  
    if bits&0x10==0x10:
        GPIO.output(LCD_D4, True)
    if bits&0x20==0x20:
        GPIO.output(LCD_D5, True)
    if bits&0x40==0x40:
        GPIO.output(LCD_D6, True)
    if bits&0x80==0x80:
        GPIO.output(LCD_D7, True)
# Toggle 'Enable' pin
    lcd_toggle_enable()

# Low bits
    GPIO.output(LCD_D4, False)
    GPIO.output(LCD_D5, False)
    GPIO.output(LCD_D6, False)
    GPIO.output(LCD_D7, False)
    if bits&0x01==0x01:
        GPIO.output(LCD_D4, True)
    if bits&0x02==0x02:
        GPIO.output(LCD_D5, True)
    if bits&0x04==0x04:
        GPIO.output(LCD_D6, True)
    if bits&0x08==0x08:
        GPIO.output(LCD_D7, True)
    # Toggle 'Enable' pin
    lcd_toggle_enable()



def lcd_toggle_enable():
    time.sleep(E_DELAY)
    GPIO.output(LCD_E, True)
    time.sleep(E_PULSE)
    GPIO.output(LCD_E, False)
    time.sleep(E_DELAY)

def lcd_string(message, line):
# Send string to display
    message = message.ljust(LCD_WIDTH," ")
    lcd_byte(line, LCD_CMD)
    for i in range(LCD_WIDTH):
        lcd_byte(ord(message[i]),LCD_CHR)

if __name__ == '__main__':
    try:
		main()
    except KeyboardInterrupt:
        pass
    finally:
        GPIO.cleanup()
