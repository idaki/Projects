Smart Home Control System (IoT)

Description: This repository contains code for an Arduino-based smart home control system that integrates temperature monitoring, WiFi connectivity, and control mechanisms for heating and battery status.

Features

Temperature Monitoring: Measures temperature using an LM35 sensor and displays it on a TM1637 7-segment display.
Threshold-Based Control: Activates a heating pump and indicator LEDs based on temperature readings (e.g., pump on above 50°C, low-temperature LED on below 40°C).
Battery Monitoring: Monitors battery status with a digital input to check if it’s above 98%.
WiFi Connectivity: Uses an ESP8266 module to send data to a remote server and handle responses.
Hardware Components

Arduino Board
TM1637 Display
LM35 Temperature Sensor
ESP8266 WiFi Module
Relay and LEDs
Setup and Usage

Hardware Setup: Connect the components as per the pin definitions in the code.
Configuration: Update WiFi credentials and server settings in the code.
Upload Code: Upload the code to the Arduino using the Arduino IDE.
Monitor & Control: View temperature readings on the display, control heating, and check battery status.
