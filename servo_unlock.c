#include <stdio.h>
#include <wiringPi.h>
#include <softPwm.h>

#define SERVO_PIN 1

int main() {
	if(wiringPiSetup() == -1)
		return 1;
	
	pinMode(SERVO_PIN, PWM_OUTPUT);
	pwmSetMode(PWM_MODE_MS);
	pwmSetClock(384);
	pwmSetRange(1000);
	pwmWrite(SERVO_PIN, 34);
	printf("0");
}
