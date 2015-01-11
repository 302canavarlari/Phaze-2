package Domain;

public class TimeLokum extends Lokum {
	int additional_time=5000;

	public TimeLokum(int lokumType, int r, int c) {
		super(lokumType, r, c);
		type=lokumType;
	}

}
