package business;

import java.util.Random;

import domain.TetrominoType;

public class TetrominoGenerator {

	public static TetrominoType getRandomTetromino() {
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
