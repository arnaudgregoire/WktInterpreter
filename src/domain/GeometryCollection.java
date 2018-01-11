package domain;

import java.util.List;

public class GeometryCollection extends Geometry{
	private List<Geometry> geoms;

	public GeometryCollection(List<Geometry> geoms) {
		super();
		this.geoms = geoms;
	}

	@Override
	public String toString() {
		return "GeometryCollection [" + geoms + "]";
	}
	
}
