package io.javabrains.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.javabrains.moviecatalogservice.model.RatingInfoResource;
import io.javabrains.moviecatalogservice.model.UserRatings;

@Service
public class RatingDataService {

	@LoadBalanced
	public RestTemplate restTemplate = new RestTemplate();

	/*
	 * @Autowired WebClient.Builder webClientbuilder;
	 */

	@HystrixCommand(fallbackMethod = "getFallbackUserRatings", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "5000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value= "6"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value= "50"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value= "5000")
	})
	public UserRatings getUserRatings(String userId) {

		/*
		 * UserRatings userRating = webClientbuilder.build().get()
		 * .uri("http://ratings-data-service/ratings/user/" +
		 * userId).retrieve().bodyToMono(UserRatings.class) .block();
		 */

		return restTemplate.getForObject("http://localhost:8083/ratings/user/" + userId, UserRatings.class);
	}

	public UserRatings getFallbackUserRatings(String userId) {
		UserRatings userRatings = new UserRatings();
		userRatings.setRatingInfo(Arrays.asList(new RatingInfoResource("0", 0)));
		return userRatings;
	}
}
