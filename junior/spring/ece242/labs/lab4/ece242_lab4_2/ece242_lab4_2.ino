#define INPUT_PIN 0 // ADC input pint
#define TIMER_PIN 3 // Pulse Wave Modulation output pin, OC2B
#define DEBUG_PIN 2 // to measure the sampling frequency
#define LED_PIN 13 // displays input overdrive

#define SHIFT_BY 3 // 2...7 input attentuator
#define TIMER_TOP 20 // determines the carrier frequency
#define A_MAX TIMER_TOP // 4

void setup()
{
  pinMode(DEBUG_PIN, OUTPUT);
  pinMode(TIMER_PIN, OUTPUT);
  pinMode(LED_PIN, OUTPUT);

  // set ADC prescaler to 16 to decrease conversion time
  ADCSRA = (ADCSRA | _BV( ADPS2 ) ) & ~( _BV( ADPS1 ) | _BV( ADPS0 ) );

  // non inverting; fast PWM with TOP; no prescaling
  TCCR2A = 0b10100011 ; // COM2A1 COM2A0 COM2B1 COM2B0 WGM21 WGM20
  TCCR2B = 0b00001001 ; // FOC2A FOC2B WGM22 CS22 CS21 CS20

  // 16E6 / (OCR2A + 1) = 762 kHz @ TIMER_TOP = 20

  OCR2A = TIMER_TOP;
  OCR2B = TIMER_TOP / 2;
}

void loop() {
  digitalWrite( DEBUG_PIN, HIGH);
  int8_t value = (analogRead(INPUT_PIN) >> SHIFT_BY) - (1<<(9-SHIFT_BY));
  digitalWrite(DEBUG_PIN, LOW);

  if(value<-A_MAX)
  {
    value = -A_MAX;
    digitalWrite( LED_PIN, HIGH);
  }
  else if( value > A_MAX )
  {
    value = A_MAX;
    digitalWrite( LED_PIN, HIGH);
  }
  else
  {
    digitalWrite(LED_PIN, LOW);
  }

  OCR2B = A_MAX + value;
}
