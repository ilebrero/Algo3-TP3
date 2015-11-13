package utils;

public class Color implements Comparable<Color> {
	int color;
	int id;
	
	public Color(int color, int id) {
		this.color = color;
		this.id    = id;
	}
	
	public int getColor() {
		return color;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public int compareTo(Color c) {
		if (c.getId() == this.id) return 0;
		
		if (this.id < c.id) {
			return -1;
		} else {
			return 1;
		}
	}
}
