package kata.berlinclock;

import static kata.berlinclock.LampState.OFF;
import static kata.berlinclock.LampState.RED;
import static kata.berlinclock.LampState.YELLOW;

import java.util.Arrays;

public class BerlinClock {

	int seconds = 0;

	public String secondsLamp() {
		return evenSeconds() ? YELLOW : OFF;
	}

	private boolean evenSeconds() {
		return seconds % 2 == 0;
	}

	public void tick() {
		seconds++;
	}

	public String[] topHoursLamps() {
		int fiveHourSegments = hours() / 5;
		return lightSegments(fiveHourSegments, 4, RED);
	}

	private String[] lightSegments(int segmentsToLight, int numberOfSegments, String color) {
		String[] lampStates = new String[numberOfSegments];
		Arrays.fill(lampStates, "OFF");
		for (int idx = 0; idx < segmentsToLight; idx++) {
			lampStates[idx] = color;
		}
		return lampStates;
	}

	private int hours() {
		return minutes() / 60;
	}

	public String[] bottomHoursLamps() {
		int singleHourSegments = hours() % 5;
		return lightSegments(singleHourSegments, 4, RED);
	}

	public String[] bottomMinutesLamps() {
		return lightSegments(minutes() % 5, 4, YELLOW);
	}

	private int minutes() {
		return seconds / 60;
	}

	public String[] topMinutesLamps() {
		String[] lampStates = lightSegments(minutes() / 5, 11, YELLOW);
		replaceEveryThirdYellowWithRed(lampStates);
		return lampStates;
	}

	private void replaceEveryThirdYellowWithRed(String[] lampStates) {
		for (int idx = 2; idx < lampStates.length; idx += 3) {
			if (lampStates[idx].equals(YELLOW)) {
				lampStates[idx] = RED;
			}
		}
	}

}
