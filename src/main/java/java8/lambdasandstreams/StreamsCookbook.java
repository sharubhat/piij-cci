package java8.lambdasandstreams;

import java.time.Duration;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.*;

/**
 * Created by sharath on 2/17/17.
 */
public class StreamsCookbook {
    private int year;
    private String nationality;
    private String name;
    private String team;
    private int lengthKm;
    private Duration winningTime;
    private int stageWins;
    private int daysInYellow;

    public StreamsCookbook(int year, String nationality, String name, String team, int lengthKm, Duration winningTime, int daysInYellow) {
        this.year = year;
        this.nationality = nationality;
        this.name = name;
        this.team = team;
        this.lengthKm = lengthKm;
        this.winningTime = winningTime;
        this.daysInYellow = daysInYellow;
    }

    public static final List<StreamsCookbook> tdfStreamsCookbooks = Collections.unmodifiableList(Arrays.asList(
            new StreamsCookbook(2006, "Spain", "Óscar Pereiro", "Caisse d'Epargne–Illes Balears", 3657, Duration.parse("PT89H40M27S"), 8),
            new StreamsCookbook(2007, "Spain", "Alberto Contador", "Discovery Channel", 3570, Duration.parse("PT91H00M26S"), 4),
            new StreamsCookbook(2008, "Spain", "Carlos Sastre", "Team CSC", 3559, Duration.parse("PT87H52M52S"), 5),
            new StreamsCookbook(2009, "Spain", "Alberto Contador", "Astana", 3459, Duration.parse("PT85H48M35S"), 7),
            new StreamsCookbook(2010, "Luxembourg", "Andy Schleck", "Team Saxo Bank", 3642, Duration.parse("PT91H59M27S"), 12),
            new StreamsCookbook(2011, "Australia", "Cadel Evans", "BMC Racing Team", 3430, Duration.parse("PT86H12M22S"), 2),
            new StreamsCookbook(2012, "Great Britain", "Bradley Wiggins", "Team Sky", 3496, Duration.parse("PT87H34M47S"), 14),
            new StreamsCookbook(2013, "Great Britain", "Chris Froome", "Team Sky", 3404, Duration.parse("PT83H56M20S"), 14),
            new StreamsCookbook(2014, "Italy", "Vincenzo Nibali", "Astana", 3661, Duration.parse("PT89H59M06S"), 19),
            new StreamsCookbook(2015, "Great Britain", "Chris Froome", "Team Sky", 3360, Duration.parse("PT84H46M14S"), 16),
            new StreamsCookbook(2016, "Great Britain", "Chris Froome", "Team Sky", 3529, Duration.parse("PT89H04M48S"), 14)));

    public static void main(String args[]) {
        // Filter and Map -
        List<String> StreamsCookbooksOfToursLessThan3500km = tdfStreamsCookbooks
                .stream()
                .filter(d -> d.getLengthKm() < 3500) // Separate out Tours less than 3500km
                .map(StreamsCookbook::getName) // Get names of StreamsCookbooks
                .collect(toList()); // Return a list
        // StreamsCookbooks of Tours Less than 3500km - [Alberto Contador, Cadel Evans, Bradley Wiggins, Chris Froome, Chris Froome]
        System.out.println("StreamsCookbooks of Tours Less than 3500km - " + StreamsCookbooksOfToursLessThan3500km);
        List<String> StreamsCookbooksOfToursGreaterThan3500km = tdfStreamsCookbooks
                .stream()
                .filter(d -> d.getLengthKm() >= 3500)
                .map(StreamsCookbook::getName)
                .collect(toList());
        // StreamsCookbooks of Tours Greater than 3500km - [Óscar Pereiro, Alberto Contador, Carlos Sastre, Andy Schleck, Vincenzo Nibali, Chris Froome]
        System.out.println("StreamsCookbooks of Tours Greater than 3500km - " + StreamsCookbooksOfToursGreaterThan3500km);
        // limit -
        List<StreamsCookbook> StreamsCookbookObjectsOfToursLessThan3500kmLimit2 = tdfStreamsCookbooks
                .stream()
                .filter(d -> d.getLengthKm() < 3500)
                .limit(2)
                .collect(toList());
        // StreamsCookbookObjectsOfToursLessThan3500kmLimit2 [Alberto Contador, Cadel Evans]
        System.out.println("StreamsCookbookObjectsOfToursLessThan3500kmLimit2 " + StreamsCookbookObjectsOfToursLessThan3500kmLimit2);
        List<String> firstTwoStreamsCookbooksOfToursLessThan3500km = tdfStreamsCookbooks
                .stream()
                .filter(d -> d.getLengthKm() < 3500)
                .map(StreamsCookbook::getName)
                .limit(2)
                .collect(toList());
        // firstTwoStreamsCookbooksOfToursLessThan3500km - [Alberto Contador, Cadel Evans]
        System.out.println("firstTwoStreamsCookbooksOfToursLessThan3500km - " + firstTwoStreamsCookbooksOfToursLessThan3500km);
        // filter by distinct
        List<String> distinctTDFStreamsCookbooks = tdfStreamsCookbooks
                .stream()
                .map(StreamsCookbook::getName)
                .distinct()
                .collect(toList());
        System.out.println("distinctTDFStreamsCookbooks - " + distinctTDFStreamsCookbooks);
        long numberOfDistinceStreamsCookbooks = tdfStreamsCookbooks
                .stream()
                .map(StreamsCookbook::getName)
                .distinct()
                .count();
        // numberOfDistinceStreamsCookbooks - 8
        System.out.println("numberOfDistinceStreamsCookbooks - " + numberOfDistinceStreamsCookbooks);
        // skip records
        List<StreamsCookbook> skipEveryOtherTDFStreamsCookbook = tdfStreamsCookbooks
                .stream()
                .skip(2)
                .collect(toList());
        // skipEveryOtherTDFStreamsCookbook - [Carlos Sastre, Alberto Contador, Andy Schleck, Cadel Evans, Bradley Wiggins, Chris Froome, Vincenzo Nibali, Chris Froome, Chris Froome]
        System.out.println("skipEveryOtherTDFStreamsCookbook - " + skipEveryOtherTDFStreamsCookbook);
        List<String> mapStreamsCookbookYearNamesToList = tdfStreamsCookbooks
                .stream()
                .map(w -> w.getYear() + " - " + w.getName())
                .collect(toList());
        // mapStreamsCookbookYearNamesToList [2006 - Óscar Pereiro, 2007 - Alberto Contador, 2008 - Carlos Sastre, 2009 - Alberto Contador, 2010 - Andy Schleck, 2011 - Cadel Evans, 2012 - Bradley Wiggins, 2013 - Chris Froome, 2014 - Vincenzo Nibali, 2015 - Chris Froome, 2016 - Chris Froome]
        System.out.println("mapStreamsCookbookYearNamesToList " + mapStreamsCookbookYearNamesToList);
        List<Integer> mapStreamsCookbookNameLengthToList = tdfStreamsCookbooks
                .stream()
                .map(StreamsCookbook::getName)
                .map(String::length)
                .collect(toList());
        // mapStreamsCookbookNameLengthToList [13, 16, 13, 16, 12, 11, 15, 12, 15, 12, 12]
        System.out.println("mapStreamsCookbookNameLengthToList " + mapStreamsCookbookNameLengthToList);
        // matching - allMatch, noneMatch
        Optional<StreamsCookbook> StreamsCookbook2012 = tdfStreamsCookbooks.stream().filter(w -> w.getName().contains("Wiggins")).findAny();
        // StreamsCookbook2012 - Bradley Wiggins
        System.out.println("StreamsCookbook2012 - " + StreamsCookbook2012.get());
        Optional<Integer> StreamsCookbookYear2014 = tdfStreamsCookbooks.stream().map(StreamsCookbook::getYear).filter(x -> x == 2014).findFirst();
        // StreamsCookbookYear2014 - 2014
        System.out.println("StreamsCookbookYear2014 - " + StreamsCookbookYear2014.get());
        // reducing - 0 --> initial value
        int totalDistance = tdfStreamsCookbooks.stream().map(StreamsCookbook::getLengthKm).reduce(0, Integer::sum);
        // totalDistance - 38767
        System.out.println("totalDistance - " + totalDistance);
        Optional<Integer> shortestYear = tdfStreamsCookbooks.stream().map(StreamsCookbook::getLengthKm).reduce(Integer::min);
        // shortestYear - 3360
        System.out.println("shortestYear - " + shortestYear.get());
        Optional<Integer> longestYear = tdfStreamsCookbooks.stream().map(StreamsCookbook::getLengthKm).reduce(Integer::max);
        // longestYear - 3661
        System.out.println("longestYear - " + longestYear.get());
        Optional<StreamsCookbook> fastestStreamsCookbook = tdfStreamsCookbooks.stream().min(Comparator.comparingDouble(StreamsCookbook::getAveSpeed));
        System.out.println("fastestTDF - " + fastestStreamsCookbook.get());
        // shorthand
        OptionalDouble fastestTDF = tdfStreamsCookbooks.stream().mapToDouble(StreamsCookbook::getAveSpeed).min();
        // fastestTDF - 39.0
        System.out.println("fastestTDF - " + fastestTDF.getAsDouble());
        // groupingby - make a map whose keys are names
        Map<String, List<StreamsCookbook>> namesVsStreamsCookbook = tdfStreamsCookbooks.stream().collect(groupingBy(StreamsCookbook::getName));
        // namesVsStreamsCookbook - {Bradley Wiggins=[Bradley Wiggins], Carlos Sastre=[Carlos Sastre], Cadel Evans=[Cadel Evans], Óscar Pereiro=[Óscar Pereiro], Chris Froome=[Chris Froome, Chris Froome, Chris Froome], Andy Schleck=[Andy Schleck], Alberto Contador=[Alberto Contador, Alberto Contador], Vincenzo Nibali=[Vincenzo Nibali]}
        System.out.println("namesVsStreamsCookbook - " + namesVsStreamsCookbook);
        // join strings
        String allTDFStreamsCookbooksTeamsCSV = tdfStreamsCookbooks.stream().map(StreamsCookbook::getTeam).collect(joining(", "));
        // allTDFStreamsCookbooksTeams Caisse d'Epargne–Illes Balears, Discovery Channel, Team CSC, Astana, Team Saxo Bank, BMC Racing Team, Team Sky, Team Sky, Astana, Team Sky, Team Sky
        System.out.println("allTDFStreamsCookbooksTeams " + allTDFStreamsCookbooksTeamsCSV);
        // grouping
        Map<String, List<StreamsCookbook>> StreamsCookbooksByNationality = tdfStreamsCookbooks.stream().collect(groupingBy(StreamsCookbook::getNationality));
        // StreamsCookbooksByNationality - {Great Britain=[Bradley Wiggins, Chris Froome, Chris Froome, Chris Froome], Luxembourg=[Andy Schleck], Italy=[Vincenzo Nibali], Australia=[Cadel Evans], Spain=[Óscar Pereiro, Alberto Contador, Carlos Sastre, Alberto Contador]}
        System.out.println("StreamsCookbooksByNationality - " + StreamsCookbooksByNationality);
        Map<String, Long> winsByNationalityCounting = tdfStreamsCookbooks.stream().collect(groupingBy(StreamsCookbook::getNationality, counting()));
        // winsByNationalityCounting - {Great Britain=4, Luxembourg=1, Italy=1, Australia=1, Spain=4}
        System.out.println("winsByNationalityCounting - " + winsByNationalityCounting);
    }

    public double getAveSpeed() {
        return ((double)getLengthKm() / ((double)(getWinningTime().getSeconds()) / 3600));
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getLengthKm() {
        return lengthKm;
    }

    public void setLengthKm(int lengthKm) {
        this.lengthKm = lengthKm;
    }

    public Duration getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(Duration winningTime) {
        this.winningTime = winningTime;
    }

    public int getStageWins() {
        return stageWins;
    }

    public void setStageWins(int stageWins) {
        this.stageWins = stageWins;
    }

    public int getDaysInYellow() {
        return daysInYellow;
    }

    public void setDaysInYellow(int daysInYellow) {
        this.daysInYellow = daysInYellow;
    }

    @Override
    public String toString() {
        return name;
    }
}