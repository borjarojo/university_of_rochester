const byte ANTENNA = 9;


void setup()
{
// set up Timer 1
  TCCR1A = _BV(COM1A0);

/*
 * Setting a High bit value to the COM1A0 bit in order
 * to toggle OC1A to Compare Match.
 *  The Comparator is used from the microcontroller it will
 *  toggle the output of the OC1A port which goes to the antenna
 */

  TCCR1B = _BV(WGM12) | _BV(CS10);
 //CTC-clear timer on compare, no prescaler

  OCR1A = 9;
 /*
  * Compare register A value to 10, this sets the frequency at which
  * the transmission will be operated. The table below shows infromation
  * on OCR1A and transmission frequency. Compare input of the antenna
  * to the register.
  */

}

void loop()
{
//The message being ransmitted can be prgrammed here

  pinMode(ANTENNA, OUTPUT);
  delay(500);
  pinMode(ANTENNA, INPUT);
  delay(300);
}
