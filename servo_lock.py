import RPi.GPIO as GPIO
import time, requests, json


prev_count = 0
while True:
	url= 'http://xproject2.dothome.co.kr/test.php'
	data={'outer':{'inner':'value'}}
	response = requests.get(url, data=json.dumps(data))
	response.status_code
	print(response.text)
	res = json.loads(response.text);
	if(prev_count == res["count"]-1):
		print "UNLOCK"
		GPIO.setmode(GPIO.BCM)
		GPIO.setup(18, GPIO.OUT)
		p = GPIO.PWM(18, 50)
		p.start(1)
		print("2.5")
		p.ChangeDutyCycle(6.5)
		time.sleep(2)
		print "GPIO.cleenup()"
		GPIO.cleanup()
			
	if(prev_count == res["count"]+1 ):
		print "LOCK"
		GPIO.setmode(GPIO.BCM)
		GPIO.setup(18, GPIO.OUT)
		p = GPIO.PWM(18, 50)
		p.start(1)
		print(12.0)
		p.ChangeDutyCycle(12.0)
		time.sleep(2)
			
		
	prev_count = res["count"]

