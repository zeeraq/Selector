public class BattingPerformance extends Performance {
  int fours, sixes;
  boolean dismissed;
  int batting_position;
  float getStrikeRate() {
    if(balls == 0) return -1.0f;
      return runs / balls * 100;
  }
}
