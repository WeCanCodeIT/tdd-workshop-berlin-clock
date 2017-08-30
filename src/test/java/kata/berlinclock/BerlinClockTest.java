package kata.berlinclock;

import static java.util.Arrays.asList;
import static kata.berlinclock.LampState.OFF;
import static kata.berlinclock.LampState.RED;
import static kata.berlinclock.LampState.YELLOW;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class BerlinClockTest {

	BerlinClock underTest = new BerlinClock();

	@Test
	public void shouldBeYellowForZeroSeconds() {

		String seconds = underTest.secondsLamp();

		assertThat(seconds, is(YELLOW));
	}

	@Test
	public void shouldBeOffForOneSecond() {

		tickMany(1);

		String seconds = underTest.secondsLamp();

		assertThat(seconds, is(OFF));
	}

	private void tickMany(int numberOfTimes) {
		for (int count = 1; count <= numberOfTimes; count++) {
			underTest.tick();
		}
	}

	@Test
	public void shouldBeYellowForTwoSeconds() {
		tickMany(2);

		String seconds = underTest.secondsLamp();

		assertThat(seconds, is(YELLOW));
	}

	@Test
	public void shouldReturnTopHoursLampsAsOffForZero() {
		String[] topHoursLamps = underTest.topHoursLamps();

		assertThat(topHoursLamps, is(arrayContaining(OFF, OFF, OFF, OFF)));
		// the old hard way
		assertArrayEquals(new String[] { OFF, OFF, OFF, OFF }, topHoursLamps);
	}

	private void tickHours(int hours) {
		tickMany(hours * 60 * 60);
	}

	@Test
	public void shouldReturnTopHoursLampsForElevenHours() {
		tickHours(11);

		String[] topHoursLamps = underTest.topHoursLamps();

		assertThat(topHoursLamps, is(arrayContaining(RED, RED, OFF, OFF)));
	}

	@Test
	public void shouldReturnTopHoursLampsForTwentyOneHours() {
		tickHours(21);

		String[] topHoursLamps = underTest.topHoursLamps();

		assertThat(topHoursLamps, is(arrayContaining(RED, RED, RED, RED)));
	}

	@Test
	public void shouldReturnBottomHoursLampsAsOffForZero() {
		String[] bottomHoursLamps = underTest.bottomHoursLamps();

		assertThat(bottomHoursLamps, is(arrayContaining(OFF, OFF, OFF, OFF)));
	}

	@Test
	public void shouldReturnBottomHoursLampsForTwelveHours() {
		tickHours(12);

		String[] bottomHoursLamps = underTest.bottomHoursLamps();

		assertThat(bottomHoursLamps, is(arrayContaining(RED, RED, OFF, OFF)));
	}

	@Test
	public void shouldReturnBottomMinutesLampsAsOffForZero() {
		String[] lamps = underTest.bottomMinutesLamps();

		assertThat(lamps, is(arrayContaining(OFF, OFF, OFF, OFF)));
	}

	@Test
	public void shouldReturnBottomMinutesLampsForThreeMinutes() {
		tickMinutes(3);

		String[] lamps = underTest.bottomMinutesLamps();

		assertThat(lamps, is(arrayContaining(YELLOW, YELLOW, YELLOW, OFF)));
	}

	private void tickMinutes(int minutes) {
		tickMany(minutes * 60);
	}

	@Test
	public void shouldReturnTopMinutesLampsAsOffForZero() {
		String[] lamps = underTest.topMinutesLamps();

		assertThat(lamps.length, is(11));
		assertThat(asList(lamps), everyItem(is(OFF)));
	}

	@Test
	public void shouldReturnTopMinutesLampsForElevenMinutes() {
		tickMinutes(11);

		String[] lamps = underTest.topMinutesLamps();

		assertThat(asList(lamps), contains(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF));
	}
	
	@Test
	public void shouldReturnTopMinutesLampsFor16Minutes() {
		tickMinutes(16);

		String[] lamps = underTest.topMinutesLamps();

		assertThat(asList(lamps), contains(YELLOW, YELLOW, RED, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF));
	}
}
