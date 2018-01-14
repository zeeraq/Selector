import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Bowler extends Player {
  @Override
  public float getAverage(Selector.Filter filter) {
      int s_runs = 0, s_wickets = 0;
      for(int i = 0; i < bowlings.size(); i++) {

          switch (filter.type) {
              case VENUE: if(battings.get(i).venue != filter.valString) continue;
                  break;
              case OPPOSITION: if(battings.get(i).opposition != filter.valString) continue;
                  break;
          }

          s_runs += bowlings.get(i).runs;
          s_wickets += bowlings.get(i).wickets;
      }
      return s_runs / s_wickets;
  }

  @Override
  public float getStrikeRate(Selector.Filter filter) {
    int s_wickets = 0;
    int s_balls = 0;
    for(int i = 0; i < bowlings.size(); i++) {

        switch (filter.type) {
            case VENUE: if(battings.get(i).venue != filter.valString) continue;
                break;
            case OPPOSITION: if(battings.get(i).opposition != filter.valString) continue;
                break;
        }
        s_wickets += bowlings.get(i).wickets;
        s_balls += bowlings.get(i).balls;
    }
    return s_balls / s_wickets;
  }

  float getRating(float sr_weight, float average_weight, Selector.Filter filter) {
    float average = getAverage(filter);
    float strikeRate = getStrikeRate(filter);
    return sr_weight * strikeRate + average_weight * average;
  }

  public static ArrayList<Bowler> getBowlerLeaderboard(ArrayList<Bowler> bowlers, float avg_weight, float sr_weight, Selector.Filter filter) {
    for(int i = 0; i < bowlers.size(); i++) {
        bowlers.get(i).batting_rating = bowlers.get(i).getRating(sr_weight, avg_weight, filter);
    }
    Collections.sort(bowlers, new BowlerComparater());
    return bowlers;
  }

  static class BowlerComparater implements Comparator<Bowler> {
    @Override
    public int compare(Bowler o1, Bowler o2) {
        return (o1.bowling_rating < o2.bowling_rating) ? -1 : (o1.bowling_rating == o2.bowling_rating ? 0 : 1);
    }
  }
}
