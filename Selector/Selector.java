import java.util.ArrayList;

public class Selector {
  ArrayList<Match> matches = new ArrayList<>();
  ArrayList<Player> players = new ArrayList<>();
  public static final float BOWLING_AVG_WEIGHT = 2.5F;
  public static final float BOWLING_SR_WEIGHT = 0.5F;
  public static final float BATTING_AVG_WEIGHT = 2.5F;
  public static final float BATTING_SR_WEIGHT = 0.2F;
  Filter filter;

  public static void main(String[] args) {
      Selector selector = new Selector();
      selector.readMatches();
      selector.readPlayers();

      /* We can use filters here for selection by various criteria */
      //selector.setFilter(new Filter(Filter.Type.VENUE, "CHINNASWAMY"));
      // selector.setFilter(new Filter(Filter.Type.OPPOSITION, "RCB"));


      ArrayList<Player> team = selector.selectEleven(6, 5, "SRH");
      selector.announceTeam(team);
  }

  public void setFilter(Filter filter) {
      this.filter = filter;
  }
  public void readMatches() {
    //we will load matches from text file/ db here
    /*although there is a lot of available ball by ball data,
    formatting it for our application was a huge task, hence leaving it for now */
  }

  public  void readPlayers() {
    //we will load players from text file/ db here

  }

  public void announceTeam(ArrayList<Player> players) {
    /* list all players here */
  }

  public ArrayList<Player> selectEleven(int numBatsmen, int numBowlers, String team) {
    ArrayList<Player> playingEleven = new ArrayList<>();
    ArrayList<Player> allTeamPlayers = getTeamPlayers(team);
    ArrayList<Player> topBatsmen = getTopBatsmen(allTeamPlayers, numBatsmen);
    ArrayList<Player> topBowlers = getTopBowlers(allTeamPlayers, numBowlers);
    playingEleven.addAll(topBatsmen);
    playingEleven.addAll(topBowlers);
    return  playingEleven;
  }

  private ArrayList<Player> getTeamPlayers(String team){
    ArrayList<Player> teamPlayers = new ArrayList<>();
    for(int i = 0; i < players.size(); i++) {
      Player player = players.get(i);
      if(player.team == team) {
        teamPlayers.add(player);
      }
    }
    return teamPlayers;
  }


  private ArrayList<Player> getTopBatsmen(ArrayList<Player> batsmen, int num) {

     ArrayList<Batsman> batsmen1 = new ArrayList<>();
     for(int i = 0; i < batsmen.size(); i++) {
       Batsman batsman = (Batsman) batsmen.get(i);
       batsmen1.add(batsman);
     }

     batsmen1 = Batsman.getBatsmenLeaderboard(batsmen1, BATTING_AVG_WEIGHT, BATTING_SR_WEIGHT, filter);
     ArrayList<Player> result = new ArrayList<>();
     for(int i = 0; i < num; i++) {
       result.add((Player) batsmen1.get(i));
     }
     return result;

  }

  private ArrayList<Player> getTopBowlers(ArrayList<Player> bowlers, int num) {

    ArrayList<Bowler> bowlers1 = new ArrayList<>();
    for(int i = 0; i < players.size(); i++) {
      Bowler bowler = (Bowler) players.get(i);
      bowlers1.add(bowler);
    }

    bowlers1 = Bowler.getBowlerLeaderboard(bowlers1, BOWLING_AVG_WEIGHT, BOWLING_SR_WEIGHT, filter);
    ArrayList<Player> result = new ArrayList<>();
    for(int i = 0; i < num; i++) {
      result.add((Player) bowlers1.get(i));
    }
    return result;
  }

  static class Filter {
      /* more filter types can be added here */
      enum Type {
          VENUE,
          OPPOSITION
      };
      Type type;
      String valString;
      Filter(Type type, String valString) {
          this.type = type;
          this.valString = valString;
      }
  }

}
