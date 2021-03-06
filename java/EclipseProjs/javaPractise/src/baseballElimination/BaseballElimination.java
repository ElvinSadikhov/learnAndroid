package baseballElimination;

import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Stack;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class BaseballElimination {

	private String[] tNames;
	private int[] tWins;
	private int[] tLosses;
	private int[] tRemain;
	private int[][] tRemainOthers;
	private int cTeams;
	private int toTCapacity = 0;

	private Queue<String> queue;

	public BaseballElimination(String filename) {

		In in = new In(filename);
		cTeams = Integer.parseInt(in.readLine());
		tNames = new String[cTeams];
		tWins = new int[cTeams];
		tLosses = new int[cTeams];
		tRemain = new int[cTeams];
		tRemainOthers = new int[cTeams][cTeams];
		queue = new Queue<String>();
		for (int iTeam = 0; iTeam < cTeams; iTeam++) {
			String line = in.readLine();
			Scanner lineScanner = new Scanner(line);
			tNames[iTeam] = lineScanner.next();
			tWins[iTeam] = lineScanner.nextInt();
			tLosses[iTeam] = lineScanner.nextInt();
			tRemain[iTeam] = lineScanner.nextInt();
			for (int iAgainst = 0; iAgainst < cTeams; iAgainst++) {
				tRemainOthers[iTeam][iAgainst] = lineScanner.nextInt();
			}
		}

	}

	// number of teams
	public int numberOfTeams() {
		return cTeams;
	}

	// all teams
	public Iterable<String> teams() {
		Stack<String> teams = new Stack<>();
		for (String s : tNames) {
			teams.push(s);
		}
		return teams;
	}

	// number of wins for given team
	public int wins(String team) {
		for (int i = 0; i < cTeams; i++) {
			if (team.equals(tNames[i]))
				return tWins[i];
		}
		throw new java.lang.IllegalArgumentException();
	}

	// number of losses for given team
	public int losses(String team) {
		for (int i = 0; i < cTeams; i++) {
			if (team.equals(tNames[i]))
				return tLosses[i];
		}
		throw new java.lang.IllegalArgumentException();
	}

	// number of remaining games for given team
	public int remaining(String team) {
		for (int i = 0; i < cTeams; i++) {
			if (team.equals(tNames[i]))
				return tRemain[i];
		}
		throw new java.lang.IllegalArgumentException();
	}

	// number of remaining games between team1 and team2
	public int against(String team1, String team2) {
		int index1 = -1, index2 = -1;
		for (int iTeam = 0; iTeam < cTeams; iTeam++) {
			if (tNames[iTeam].equals(team1)) {
				index1 = iTeam;
			} else if (tNames[iTeam].equals(team2)) {
				index2 = iTeam;
			}
		}
		if (index1 < 0 || index2 < 0) {
			throw new IllegalArgumentException();
		}
		return tRemainOthers[index1][index2];
	}

	// is given team eliminated?
	public boolean isEliminated(String team) {
		int teamID = -1;
		for (int iTeam = 0; iTeam < cTeams; iTeam++) {
			if (tNames[iTeam].equals(team))
				teamID = iTeam;
		}
		if (teamID < 0)
			throw new IllegalArgumentException();

		if (trivialSolution(teamID))
			return true;

		FordFulkerson G = nontrivialSolution(teamID);
		// if maximum flow in graph is not equal to total num of games this team should
		// be eliminated
		if (toTCapacity != G.value())
			return true;
		return false;
	}

	private boolean trivialSolution(int team) {
		boolean eliminated = false;
		for (int iTeam = 0; iTeam < cTeams; iTeam++) {
			if (iTeam != team && tWins[iTeam] > tWins[team] + tRemain[team]) {
				eliminated = true;
				queue.enqueue(tNames[iTeam]);
				break;
			}
		}
		return eliminated;
	}

	private FordFulkerson nontrivialSolution(int team) {
		int games = ((cTeams - 1) * cTeams) / 2; // the number of playing pairs(no repetitions)
		FlowNetwork G = new FlowNetwork(games + cTeams + 2);
		int s = games + cTeams;
		int t = games + cTeams + 1;
		int vertex = 0; // game ID (goes from 0 to games-1)
		toTCapacity = 0;
		for (int i = 0; i < cTeams; i++) {
			// edges between teams and t (giving them maximumcapacity, which they cannot
			// over reach)
			G.addEdge(new FlowEdge(games + i, t, tRemain[team] + tWins[team] - tWins[i]));
			for (int j = i + 1; j < cTeams; j++) {
				G.addEdge(new FlowEdge(s, vertex, tRemainOthers[i][j])); // from s to game vertex giving capacity of
																			// remaining games
				// edges between game vertex and both iTeam and jTeam (without repeatitions)
				G.addEdge(new FlowEdge(vertex, games + i, Double.POSITIVE_INFINITY));
				G.addEdge(new FlowEdge(vertex, games + j, Double.POSITIVE_INFINITY));
				vertex++;
				toTCapacity += tRemainOthers[i][j];
			}
		}
		int index = 0;
		FordFulkerson ff = new FordFulkerson(G, s, t);
		for (int i = games; i < games + cTeams; i++) {
			if (ff.inCut(i)) // see javadocs
				queue.enqueue(tNames[index]);
			index++;
		}
		return new FordFulkerson(G, s, t);

	}

	// subset R of teams that eliminates given team; null if not eliminated
	public Iterable<String> certificateOfElimination(String team) {
		queue = new Queue<>();

		int teamID = -1;
		for (int iTeam = 0; iTeam < cTeams; iTeam++) {
			if (tNames[iTeam].equals(team))
				teamID = iTeam;
		}
		if (teamID < 0)
			throw new IllegalArgumentException();

		if (trivialSolution(teamID))
			return queue;

		FordFulkerson G = nontrivialSolution(teamID);
		if (queue.size() == 0)
			return null;
		return queue;
	}

}
