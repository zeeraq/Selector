import java.util.ArrayList;

public class Match {
  String match_id;
  String date;
  String venue;
  String[] teams;
  Scorecard scorecard;
  String tournament;
  String mom_id;

  Match(String date, String venue, String[] teams, String tournament){
    this.date = date;
    this.venue = venue;
    this.teams = teams;
    this.tournament = tournament;
    this.scorecard = null;
    this.mom_id = null;
  }

  public void updateScorecard(Scorecard scorecard) {
    this.scorecard = scorecard;
  }

  public void setMomId(String mom) {
    this.mom_id = mom;
  }

  class Scorecard {
    String match_id;
    Innings firstInnings;
    Innings secondInnings;
    class Innings {
      ArrayList<BattingPerformance> battingPerformances;
      ArrayList<BowlingPerformance> bowlingPerformances;
      Integer[] fall_of_wickets = new Integer[10];
    }
  }
}
