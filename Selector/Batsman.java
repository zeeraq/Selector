import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Batsman extends Player {

  @Override
  public float getAverage(Selector.Filter filter) {
      int s_runs = 0, s_dismissals = 0;
      for(int i = 0; i < battings.size(); i++) {
          switch (filter.type) {
              case VENUE: if(battings.get(i).venue != filter.valString) continue;
                break;
              case OPPOSITION: if(battings.get(i).opposition != filter.valString) continue;
                break;
          }
          s_runs += battings.get(i).runs;
          if(battings.get(i).dismissed) s_dismissals++;
      }
      return s_runs / s_dismissals;
  }

  @Override
  public float getStrikeRate(Selector.Filter filter) {
    int s_runs = 0;
    int s_balls = 0;
    for(int i = 0; i < battings.size(); i++) {
        switch (filter.type) {
            case VENUE: if(battings.get(i).venue != filter.valString) continue;
                break;
            case OPPOSITION: if(battings.get(i).opposition != filter.valString) continue;
                break;
        }
        s_runs += battings.get(i).runs;
        s_balls += battings.get(i).balls;
    }
    return s_runs / s_balls * 100;
  }

  float getRating(float sr_weight, float average_weight, Selector.Filter filter) {
    float average = getAverage(filter);
    float strikeRate = getStrikeRate(filter);
    return sr_weight * strikeRate + average_weight * average;
  }

  public static ArrayList<Batsman> getBatsmenLeaderboard(ArrayList<Batsman> batsmen, float avg_weight, float sr_weight, Selector.Filter filter) {
      for(int i = 0; i < batsmen.size(); i++) {
          batsmen.get(i).batting_rating = batsmen.get(i).getRating(sr_weight, avg_weight, filter);
      }
      Collections.sort(batsmen, new BatsmanComparator());
      return batsmen;
  }

  static class BatsmanComparator implements Comparator<Batsman> {

      @Override
      public int compare(Batsman o1, Batsman o2) {
          return (o1.batting_rating < o2.batting_rating) ? -1 : (o1.batting_rating == o2.batting_rating ? 0 : 1);
      }
  }
}
