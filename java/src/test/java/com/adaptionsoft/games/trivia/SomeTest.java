package com.adaptionsoft.games.trivia;

import static org.junit.Assert.*;

import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.approvaltests.Approvals;
import org.approvaltests.namer.NamedEnvironment;
import org.approvaltests.namer.NamerFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SomeTest {
	private static void overrideSystemOut() throws FileNotFoundException {
		PrintStream o = new PrintStream(new File("C:\\Users\\Administrator\\Documents\\GitHub\\trivia\\java\\src\\test\\java\\com\\adaptionsoft\\games\\trivia\\output.txt"));
		PrintStream console = System.out;
		System.setOut(o);
	}


	@Test
	public void testGameGoldenMaster() throws Exception {
		overrideSystemOut();
		List<Integer> testRange = IntStream.rangeClosed(-9, 99).boxed().collect(Collectors.toList());
		for (int i = 0; i < testRange.size(); i++) {
			GameRunner.playGame(new Random(i));
		}

		Scanner myReader = new Scanner(new File("C:\\Users\\Administrator\\Documents\\GitHub\\trivia\\java\\src\\test\\java\\com\\adaptionsoft\\games\\trivia\\output.txt"));
		StringBuilder data = new StringBuilder();
		while (myReader.hasNextLine()) {
			data.append(myReader.nextLine()+"\n");
		}
		myReader.close();
		Approvals.verify(data);
	}
}
