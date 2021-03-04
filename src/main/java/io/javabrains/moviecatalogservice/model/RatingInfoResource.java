package io.javabrains.moviecatalogservice.model;

public class RatingInfoResource {

	private String movieId;
	private int rating;

	public RatingInfoResource() {
	}
	
	public RatingInfoResource(String movieId, int rating) {
		super();
		this.movieId = movieId;
		this.rating = rating;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "RatingInfoResource [movieId=" + movieId + ", rating=" + rating + "]";
	}
	
	

}
