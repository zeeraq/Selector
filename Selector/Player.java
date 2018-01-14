import java.util.ArrayList;
import java.util.Comparator;

public class Player {
  String name;
  String dob;
  String team;
  String id;
  float average;
  float strike_rate;
  ArrayList<BattingPerformance> battings;
  ArrayList<BowlingPerformance> bowlings;
  float batting_rating;
  float bowling_rating;

  public float getAverage(Selector.Filter filter){
    return 0.0f;
  }
  public float getStrikeRate(Selector.Filter filter){
    return 0.0f;
  }
  public float getRating(Selector.Filter filter){
    return 0.0f;
  }
}
