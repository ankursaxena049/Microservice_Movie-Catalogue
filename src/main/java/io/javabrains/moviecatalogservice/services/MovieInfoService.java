package io.javabrains.moviecatalogservice.services;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.javabrains.moviecatalogservice.model.CatalogItem;
import io.javabrains.moviecatalogservice.model.MovieInfoResource;
import io.javabrains.moviecatalogservice.model.RatingInfoResource;

@Service
public class MovieInfoService {

	@LoadBalanced
	private RestTemplate restTemplate = new RestTemplate();

	/*
	 * @Autowired WebClient.Builder webClientbuilder;
	 */

	@HystrixCommand(fallbackMethod = "getFallbackMovieInfo", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value= "6"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value= "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value= "5000")
	})
	public CatalogItem getMovieInfo(RatingInfoResource rating) {

		// Using RestTemplate to call other microservice and it get Deprecated
		MovieInfoResource movieInfoResource = restTemplate
				.getForObject("http://localhost:8082/movieInfo/" + rating.getMovieId(), MovieInfoResource.class);

		System.out.println("movieInfoResource--->" + movieInfoResource.toString());

		/*
		 * Using Webclient to call other microservice to achieve Reactive programming
		 * MovieInfoResource movieInfoResource = webClientbuilder.build() .get()
		 * .uri("http://movie-info-service/movieInfo/" + rating.getMovieId())
		 * .retrieve() .bodyToMono(MovieInfoResource.class) .block();
		 */

		return new CatalogItem(movieInfoResource.getName(), movieInfoResource.getDesc(), rating.getRating());
	}

	public CatalogItem getFallbackMovieInfo(RatingInfoResource rating) {
		return new CatalogItem("No Movie Found", "", rating.getRating());
	}
}