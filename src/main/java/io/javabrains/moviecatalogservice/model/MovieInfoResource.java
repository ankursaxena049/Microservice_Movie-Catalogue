package io.javabrains.moviecatalogservice.model;

public class MovieInfoResource {

	String movieId;
	String name;
	String desc;

	public MovieInfoResource() {
		
	}	
	public MovieInfoResource(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "MovieInfoResource [movieId=" + movieId + ", name=" + name + ", desc=" + desc + "]";
	}

	
}
