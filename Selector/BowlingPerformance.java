public class BowlingPerformance extends Performance {
  int wickets;
  int maidens;

  float getStrikeRate() {
    if(wickets == 0) return -1.0f;
    return (float) (balls/ wickets);
  }

  float getEconomyRate() {
    if(balls == 0) return -1.0f;
    return (runs / balls) * 6;
  }

}
