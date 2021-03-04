package io.javabrains.moviecatalogservice.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.moviecatalogservice.model.UserCatalog;
import io.javabrains.moviecatalogservice.model.UserRatings;
import io.javabrains.moviecatalogservice.services.MovieInfoService;
import io.javabrains.moviecatalogservice.services.RatingDataService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

	@Autowired
	private MovieInfoService movieInfoService;

	@Autowired
	private RatingDataService ratingDataService;

	@RequestMapping("/{userId}")
	public UserCatalog getCatalog(@PathVariable("userId") String userId) {

		UserCatalog userCatalog = new UserCatalog();

		UserRatings userRating = ratingDataService.getUserRatings(userId);

		System.out.println("userRating--->" + userRating.toString());

		userCatalog.setCatalogItem(userRating.getRatingInfo().stream()
				.map(rating -> movieInfoService.getMovieInfo(rating)).collect(Collectors.toList()));

		return userCatalog;
	}
}