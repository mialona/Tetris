package business;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import domain.*;

public class TetrominoGenerator {

	private static Queue<TetrominoType> listTetrominoesNames;

	public static void initiate() {
		listTetrominoesNames = new LinkedList<TetrominoType>();
	}

	public static TetrominoType getTetromino() {
		if(!TetrominoGenerator.listTetrominoesNames.isEmpty()) {
			return TetrominoGenerator.listTetrominoesNames.poll();
		}
		else {
			return getRandomTetromino();
		}
	}

	public static void setTetrominoName(TetrominoType tetromino) {
		TetrominoGenerator.listTetrominoesNames.add(tetromino);
	}

	private static TetrominoType getRandomTetromino() {
		Random ran = new Random();
		switch (ran.nextInt(7)) {
			case 0:
				return TetrominoType.I;
			case 1:
				return TetrominoType.O;
			case 2:
				return TetrominoType.T;
			case 3:
				return TetrominoType.L;
			case 4:
				return TetrominoType.J;
			case 5:
				return TetrominoType.S;
			default:
				return TetrominoType.Z;
		}
	}

}
